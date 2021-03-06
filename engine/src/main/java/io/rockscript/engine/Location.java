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

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Location {

  private int start;
  private int end;
  //  private int startLine;
  //  private int startColumn;
  //  private int endLine;
  //  private int endColumn;

  public Location(ParserRuleContext parserRuleContext) {
    Token start = parserRuleContext.getStart();
    this.start = start.getStartIndex();
    Token stop = parserRuleContext.getStop();
    end = stop.getStopIndex();
//    startLine = start.getLine();
//    startColumn = start.getCharPositionInLine();
//    endLine = stop.getLine();
//    endColumn = stop.getCharPositionInLine();
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

//  public int getStartLine() {
//    return startLine;
//  }
//
//  public int getStartColumn() {
//    return startColumn;
//  }
//
//  public int getEndLine() {
//    return endLine;
//  }
//
//  public int getEndColumn() {
//    return endColumn;
//  }
}
