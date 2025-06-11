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

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Resource
 * <p>
 * Data Resource.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrictionlessResource<S extends FrictionlessSource, L extends FrictionlessLicense> implements Serializable {

  private final static long serialVersionUID = -7790507710447205789L;

  /**
   * Profile
   * <p>
   * The profile of this descriptor.
   */
  private String profile = "data-resource";

  /**
   * Name
   * <p>
   * An identifier string. Lower case characters with `.`, `_`, `-` and `/` are allowed.
   */
  private CharSequence name;

  /**
   * Path
   * <p>
   * A reference to the data for this resource, as either a path as a string, or an array of paths as strings. of valid URIs.
   */
  private Object path;

  /**
   * Data
   * <p>
   * Inline data for this resource.
   */
  private Object data;

  /**
   * Schema
   * <p>
   * A schema for this resource.
   */
  private String schema;

  /**
   * Title
   * <p>
   * A human-readable title.
   */
  private String title;

  /**
   * Description
   * <p>
   * A text description. Markdown is encouraged.
   */
  private String description;

  /**
   * Home Page
   * <p>
   * The home on the web that is related to this data package.
   */
  private URI homepage;

  /**
   * Sources
   * <p>
   * The raw sources for this resource.
   */
  private List<S> sources = new ArrayList<>();

  /**
   * Licenses
   * <p>
   * The license(s) under which the resource is published.
   */
  private List<L> licenses = new ArrayList<>();

  /**
   * Format
   * <p>
   * The file format of this resource.
   */
  private String format;

  /**
   * Media Type
   * <p>
   * The media type of this resource. Can be any valid media type listed with <a href="https://www.iana.org/assignments/media-types/media-types.xhtml">IANA</a>.
   */
  private String mediatype;

  /**
   * Encoding
   * <p>
   * The file encoding of this resource.
   */
  private String encoding = "utf-8";

  /**
   * Bytes
   * <p>
   * The size of this resource in bytes.
   */
  private Integer bytes;

  /**
   * Hash
   * <p>
   * The MD5 hash of this resource. Indicate other hashing algorithms with the {algorithm}:{hash} format.
   */
  private String hash;

  @SuppressWarnings("FieldMayBeFinal")
  @JsonIgnore
  @JsonAnyGetter
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}

