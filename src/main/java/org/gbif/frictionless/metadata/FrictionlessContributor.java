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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contributor
 * <p>
 * A contributor to this descriptor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrictionlessContributor implements Serializable {

  private final static long serialVersionUID = -288140518286006582L;

  /**
   * Title
   * <p>
   * A human-readable title.
   * (Required)
   */
  @JsonProperty("title")
  @NotBlank(message = "validation.input.required", groups = BasicMetadata.class)
  private String title;

  /**
   * Path
   * <p>
   * A fully qualified URL, or a POSIX file path.
   */
  @JsonProperty("path")
  @Pattern(regexp = "^(?=^[^./~])(^((?!\\.{2}).)*$).*$", groups = BasicMetadata.class)
  private String path;

  /**
   * Email
   * <p>
   * An email address.
   */
  @JsonProperty("email")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String email;

  /**
   * Organization
   * <p>
   * An organizational affiliation for this contributor.
   */
  @JsonProperty("organization")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String organization;

  @JsonProperty("role")
  private String role = "contributor";

  @SuppressWarnings("FieldMayBeFinal")
  @JsonIgnore
  @JsonAnyGetter
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
