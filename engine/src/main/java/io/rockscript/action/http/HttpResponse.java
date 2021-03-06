/*
 * Copyright (c) 2017, RockScript.io. All rights reserved.
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
package io.rockscript.action.http;

import io.rockscript.action.http.Http.ContentTypes;
import io.rockscript.action.http.Http.Headers;
import io.rockscript.engine.Dereferencable;

import java.util.List;
import java.util.Map;

public class HttpResponse implements Dereferencable {

  int status;
  Map<String,List<String>> headers;
  Object body;

  public HttpResponse(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getBody() {
    return body;
  }

  public void setBody(Object body) {
    this.body = body;
  }

  public void setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
  }

  public boolean isContentTypeApplicationJson() {
    return headerContains(Headers.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
  }

  public boolean headerContains(String headerName, String headerValue) {
    if (headers==null) {
      return false;
    }
    List<String> headerValues = headers.get(headerName);
    if (headerValues==null) {
      return false;
    }
    for (String actualValue: headerValues) {
      if (actualValue.contains(headerValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Object get(String propertyName) {
    if ("status".equals(propertyName)) {
      return status;
    } else if ("headers".equals(propertyName)) {
      return headers;
    } else if ("body".equals(propertyName)) {
      return body;
    }
    return null;
  }
}
