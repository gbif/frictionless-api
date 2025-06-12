/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.frictionless.metadata;

import org.gbif.frictionless.validation.BasicMetadata;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.hibernate.validator.HibernateValidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FrictionlessLicenseTest {

  private final ObjectMapper mapper = new ObjectMapper();
  private static Validator validator;

  @BeforeAll
  public static void beforeAll() {
    try (ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
      validator = validatorFactory.getValidator();
    }
  }

  @Test
  public void testAdditionalProperties() throws Exception {
    FrictionlessLicense license = FrictionlessLicense.builder()
        .name("CC0-1.0")
        .title("Creative Commons License CC0-1.0")
        .path("https://creativecommons.org/public-domain/cc0/")
        .additionalProperties(Map.of("scope", "data", "info", "random"))
        .build();

    String licenseAsJsonString = mapper.writeValueAsString(license);
    JsonNode licenseAsJson = mapper.readTree(licenseAsJsonString);

    assertEquals("CC0-1.0", licenseAsJson.path("name").asText());
    assertEquals("Creative Commons License CC0-1.0", licenseAsJson.path("title").asText());
    assertEquals("https://creativecommons.org/public-domain/cc0/", licenseAsJson.path("path").asText());
    assertEquals("data", licenseAsJson.path("scope").asText());
    assertEquals("random", licenseAsJson.path("info").asText());
    assertNull(licenseAsJson.get("additionalProperties"));

    FrictionlessLicense licenseDeserialized = mapper.readValue(licenseAsJsonString, FrictionlessLicense.class);

    assertEquals(license, licenseDeserialized);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("getInvalidLicenses")
  public void testValidation(String testCaseName, FrictionlessLicense license, String property, String expectedMessage) {
    Set<ConstraintViolation<FrictionlessLicense>> validationResult = validator.validate(license, BasicMetadata.class);

    assertFalse(validationResult.isEmpty(), "Validation must have reported issues");

    ConstraintViolation<FrictionlessLicense> singleValidationResult = validationResult.iterator().next();
    assertEquals(property,  singleValidationResult.getPropertyPath().toString());
    assertEquals(expectedMessage, singleValidationResult.getMessage());
  }

  public static Stream<Arguments> getInvalidLicenses() {
    return Stream.of(
        Arguments.of(
            "Name must not be null",
            FrictionlessLicense.builder()
                .name(null)
                .title("Creative Commons License CC0-1.0")
                .path("https://creativecommons.org/public-domain/cc0/")
                .build(),
            "name",
            "validation.datapackage.metadata.license.name.required"
        ),
        Arguments.of(
            "Name must match the pattern",
            FrictionlessLicense.builder()
                .name("$CC0-1.0$")
                .title("Creative Commons License CC0-1.0")
                .path("https://creativecommons.org/public-domain/cc0/")
                .build(),
            "name",
            "must match \"^([-a-zA-Z0-9._])+$\""
        ),
        Arguments.of(
            "Path must be a valid URI",
            FrictionlessLicense.builder()
                .name("CC0-1.0")
                .title("Creative Commons License CC0-1.0")
                .path("gbif..org")
                .build(),
            "path",
            "must match \"^(?=^[^./~])(^((?!\\.{2}).)*$).*$\""
        )
    );
  }
}
