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

import java.util.*;
import java.util.function.Function;

import io.rockscript.action.*;

public class JsonObject implements Dereferencable {

  Map<String,Object> properties = new HashMap<>();

  public JsonObject() {
  }

  public JsonObject(Map<String,Object> properties) {
    this.properties = properties;
  }

  public JsonObject put(String propertyName, Object value) {
    properties.put(propertyName, value);
    return this;
  }

  public JsonObject put(String propertyName, Function<ActionInput, ActionOutput> actionFunction) {
    Action action = new Action() {
      @Override
      public ActionOutput invoke(ActionInput input) {
        return actionFunction.apply(input);
      }
    };
    put(propertyName, action);
    return this;
  }

  public Object get(String propertyName) {
    return properties.get(propertyName);
  }

  public Set<String> getPropertyNames() {
    return properties.keySet();
  }
}
