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

/**
 * Generalization for data package metadata. Can be frictionless metadata, ColDP or anything else.
 */
public interface DataPackageMetadata {

  String getTitle();

  void setTitle(String title);

  String getVersion();

  void setVersion(String version);

  String getDescription();

  void setDescription(String description);

  String getImage();
}
