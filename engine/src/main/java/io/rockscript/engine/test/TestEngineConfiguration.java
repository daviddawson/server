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
package io.rockscript.engine.test;

import java.util.concurrent.Executor;

import io.rockscript.ScriptService;
import io.rockscript.TestScriptService;
import io.rockscript.engine.*;

public class TestEngineConfiguration extends EngineConfiguration {

  public TestEngineConfiguration() {
    this.importResolver = createImportResolver();
    this.executor = createExecutor();
  }

  protected Executor createExecutor() {
    // In test, commands are executed in the thread of the caller
    // when they commands are being added to the executor.
    // This makes testing predictable and hence easier.
    return new Executor() {
      @Override
      public void execute(Runnable command) {
        command.run();
      }
    };
  }

  protected ImportResolver createImportResolver() {
    return new ImportResolver(this);
  }

  @Override
  public ScriptService createEngine() {
    return new TestScriptService(this);
  }
}
