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

import java.util.LinkedHashMap;
import java.util.Map;

import io.rockscript.ScriptService;
import io.rockscript.netty.router.*;

public class EndActionCommand implements Command {

  String scriptExecutionId;
  String executionId;
  Object result;

  /** constructor for json deserialization */
  public EndActionCommand() {
  }

  public EndActionCommand(String scriptExecutionId, String executionId) {
    this.scriptExecutionId = scriptExecutionId;
    this.executionId = executionId;
  }

  public EndActionCommand result(Object result) {
    this.result = result;
    return this;
  }

  public EndActionCommand resultProperty(String propertyName, Object propertyValue) {
    if (result==null) {
      result = new LinkedHashMap<String,Object>();
    }
    if (!(result instanceof Map)) {
      throw new RuntimeException("resultProperty can only be used with maps / script objects");
    }
    @SuppressWarnings("unchecked")
    Map<String, Object> resultMap = (Map<String, Object>) this.result;
    resultMap.put(propertyName, propertyValue);
    return this;
  }

  @Override
  public void execute(Request request, Response response, Context context) {
    context
      .get(ScriptService.class)
      .endWaitingAction(scriptExecutionId, executionId, result);

    response.statusOk();
    response.send();
  }
}
