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
package io.rockscript.gson;

import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class PolymorphicTypeAdapterFactory implements TypeAdapterFactory {

  Map<TypeToken<?>, String> typeNames = new LinkedHashMap<>();
  PolymorphicTypeNameStrategy typeNameStrategy = PolymorphicTypeNameStrategy.WRAPPER_OBJECT;

  Set<TypeToken<?>> matchingTypes = new HashSet<>();
  PolymorphicTypeAdapter<?> typeAdapter = null;

  public PolymorphicTypeAdapterFactory typeName(Class<?> clazz, String name) {
    return typeName(TypeToken.get(clazz), name);
  }

  public PolymorphicTypeAdapterFactory typeName(TypeToken<?> type, String name) {
    typeNames.put(type, name);

    matchingTypes.add(type);
    Class<?> rawClass = type.getRawType();
    TypeToken<?> rawType = TypeToken.get(rawClass);
    if (!rawType.equals(type)) {
      matchingTypes.add(rawType);
    }

    return this;
  }

  public PolymorphicTypeAdapterFactory typeNameStrategy(PolymorphicTypeNameStrategy typeStrategy) {
    this.typeNameStrategy = typeStrategy;
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    // TODO check if GSON does caching of the created TypeAdapter for the given type

    // extra caching could be added in this layer if there is only one polymorphic type
    // adapter for the whole hierarchy

    // https://google.github.io/gson/apidocs/com/google/gson/TypeAdapterFactory.html
    // If a factory cannot support a given type, it must return null when that type is passed to create(com.google.gson.Gson, com.google.gson.reflect.TypeToken<T>)

    if (matchingTypes.contains(type)) {
      if (typeAdapter==null) {
        typeAdapter = new PolymorphicTypeAdapter<T>(type, this, gson);
      }
      return (TypeAdapter<T>) this.typeAdapter;
    }

    return null;
  }
}
