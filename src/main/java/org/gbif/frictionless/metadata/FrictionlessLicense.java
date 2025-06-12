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
 * License
 * <p>
 * A license for this descriptor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrictionlessLicense implements Serializable {

  private final static long serialVersionUID = 5529108333342991396L;

  /**
   * Open Definition license identifier
   * <p>
   * MUST be an Open Definition license identifier, see <a href="http://licenses.opendefinition.org/">...</a>
   */
  @JsonProperty("name")
  @NotNull(message = "validation.datapackage.metadata.license.name.required", groups = BasicMetadata.class)
  @Pattern(regexp = "^([-a-zA-Z0-9._])+$", groups = BasicMetadata.class)
  private String name;

  /**
   * Path
   * <p>
   * A fully qualified URL, or a POSIX file path.
   */
  @JsonProperty("path")
  @Pattern(regexp = "^(?=^[^./~])(^((?!\\.{2}).)*$).*$", groups = BasicMetadata.class)
  private String path;

  /**
   * Title
   * <p>
   * A human-readable title.
   */
  @JsonProperty("title")
  private String title;

  @JsonIgnore
  @JsonAnyGetter
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
