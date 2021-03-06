/*
 * Copyright ©2017, RockScript.io. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rockscript.engine;

import io.rockscript.action.ImportJsonObject;
import io.rockscript.action.ImportProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class ImportResolver {

  Map<String,JsonObject> importObjects = new HashMap<>();

  public ImportResolver(EngineConfiguration engineConfiguration) {
    loadAllAvailableOnClassPath();
  }

  public ImportResolver add(String url, JsonObject importObject) {
    if (importObject instanceof ImportJsonObject) {
      ((ImportJsonObject)importObject).resolveActionNames(url);
    }
    importObjects.put(url, importObject);
    return this;
  }

  public ImportResolver loadAllAvailableOnClassPath() {
    ServiceLoader<ImportProvider> importProviderLoader = ServiceLoader.load(ImportProvider.class);
    for (ImportProvider importProvider: importProviderLoader) {
      importProvider.provideImport(this);
    }
    return this;
  }

  public JsonObject get(String url) {
    JsonObject jsonObject = importObjects.get(url);
    if (jsonObject==null) {
      jsonObject = new RemoteActionsJsonObject(url);
      importObjects.put(url, jsonObject);
    }
    return jsonObject;
  }
}
