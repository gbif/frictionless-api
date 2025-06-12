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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Source
 * <p>
 * A source file.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrictionlessSource implements Serializable {

  private final static long serialVersionUID = -3013088705460436883L;

  /**
   * Title
   * <p>
   * A human-readable title.
   * (Required)
   */
  @JsonProperty("title")
  @NotNull(message = "validation.input.required", groups = BasicMetadata.class)
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
  private String email;

  @SuppressWarnings("FieldMayBeFinal")
  @JsonIgnore
  @JsonAnyGetter
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
