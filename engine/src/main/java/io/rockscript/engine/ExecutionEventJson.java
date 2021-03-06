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

public abstract class ExecutionEventJson<T extends ExecutionEvent> extends EventJson {

  String scriptId;
  Integer elementIndex;
  String scriptExecutionId;
  String executionId;

  public ExecutionEventJson() {
  }

  public ExecutionEventJson(T event) {
    Execution execution = event.getExecution();
    ScriptExecution scriptExecution = execution.getScriptExecution();
    this.scriptId = scriptExecution.getScript().getId();
    this.scriptExecutionId = scriptExecution.getId();
    this.elementIndex = execution.getElement().getIndex();
    this.executionId = execution.getId();
  }

  public String getScriptId() {
    return scriptId;
  }

  public String getScriptExecutionId() {
    return scriptExecutionId;
  }

  public Integer getElementIndex() {
    return elementIndex;
  }

  public String getExecutionId() {
    return executionId;
  }

  public abstract T toEvent(Execution execution);
}
