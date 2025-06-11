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
import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

/**
 * License
 * <p>
 * A license for this descriptor.
 */
@Data
@Builder
public class FrictionlessLicense implements Serializable {

  private final static long serialVersionUID = 5529108333342991396L;

  /**
   * Open Definition license identifier
   * <p>
   * MUST be an Open Definition license identifier, see <a href="http://licenses.opendefinition.org/">...</a>
   */
  private String name;

  /**
   * Path
   * <p>
   * A fully qualified URL, or a POSIX file path.
   */
  private String path;

  /**
   * Title
   * <p>
   * A human-readable title.
   */
  private String title;

  @SuppressWarnings("FieldMayBeFinal")
  private Map<String, Object> additionalProperties = new HashMap<>();

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}
