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
package io.rockscript.command;

import com.google.gson.reflect.TypeToken;
import io.rockscript.gson.PolymorphicTypeAdapterFactory;
import io.rockscript.netty.router.*;

public interface Command {

  public static PolymorphicTypeAdapterFactory createCommandsTypeAdapterFactory() {
    return new PolymorphicTypeAdapterFactory()
      .typeName(new TypeToken<Command>(){}, "command")
      .typeName(new TypeToken<DeployScriptCommand>(){}, "deployScript")
      .typeName(new TypeToken<StartScriptCommand>(){}, "startScript")
      .typeName(new TypeToken<EndActionCommand>(){}, "endAction");
  }

  void execute(Request request, Response response, Context context);
}
