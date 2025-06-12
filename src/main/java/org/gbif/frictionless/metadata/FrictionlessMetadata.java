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
import org.gbif.frictionless.validation.KeywordsMetadata;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Frictionless metadata
 * <p>
 * Data Package is a simple specification for data access and delivery.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrictionlessMetadata<C extends FrictionlessContributor, L extends FrictionlessLicense, S extends FrictionlessSource>
    implements DataPackageMetadata, Serializable {

  private final static long serialVersionUID = 5948080618683312611L;

  /**
   * Title
   * <p>
   * A human-readable title.
   */
  @JsonProperty("title")
  @NotNull(message = "validation.input.required", groups = BasicMetadata.class)
  private String title;

  /**
   * Version
   * <p>
   * A version string identifying the version of the package.
   */
  @JsonProperty("version")
  private String version = "1.0";

  /**
   * Profile
   * <p>
   * The profile of this descriptor.
   */
  @JsonProperty("profile")
  private String profile = "data-package";

  /**
   * Name
   * <p>
   * An identifier string. Lower case characters with `.`, `_`, `-` and `/` are allowed.
   */
  @JsonProperty("name")
  @Pattern(regexp = "^([-a-z0-9._/])+$", groups = BasicMetadata.class)
  private String name;

  /**
   * ID
   * <p>
   * A property reserved for globally unique identifiers. Examples of identifiers that are unique include UUIDs and DOIs.
   */
  @JsonProperty("id")
  private String id;

  /**
   * Description
   * <p>
   * A text description. Markdown is encouraged.
   */
  @JsonProperty("description")
  @NotNull(message = "validation.input.required", groups = BasicMetadata.class)
  private String description;

  /**
   * Home Page
   * <p>
   * The home on the web that is related to this data package.
   */
  @JsonProperty("homepage")
  private URI homepage;

  /**
   * Created
   * <p>
   * The datetime on which this descriptor was created.
   */
  @JsonProperty("created")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private Date created;

  /**
   * Contributors
   * <p>
   * The contributors to this descriptor.
   */
  @JsonProperty("contributors")
  @NotNull(message = "validation.input.required", groups = BasicMetadata.class)
  @Valid
  private List<C> contributors = new ArrayList<>();

  /**
   * Keywords
   * <p>
   * A list of keywords that describe this package.
   */
  @JsonProperty("keywords")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @NotNull(message = "validation.input.notNull", groups = KeywordsMetadata.class)
  @Valid
  private List<String> keywords = new ArrayList<>();

  /**
   * Image
   * <p>
   * An image to represent this package.
   */
  @JsonProperty("image")
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String image;

  /**
   * Licenses
   * <p>
   * The license(s) under which this package is published.
   */
  @JsonProperty("licenses")
  @NotNull(message = "validation.input.required", groups = BasicMetadata.class)
  @Valid
  private List<L> licenses = new ArrayList<>();

  /**
   * Sources
   * <p>
   * The raw sources for this resource.
   */
  @JsonProperty("sources")
  @NotNull(message = "validation.input.notNull", groups = BasicMetadata.class)
  @Valid
  private List<S> sources = new ArrayList<>();

  @SuppressWarnings("FieldMayBeFinal")
  @JsonIgnore
  @JsonAnyGetter
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
