/*
 * Created on Dec 1, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.remote.core;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.remote.core.PingRequest.pingRequest;

import org.fest.mocks.EasyMockTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link PingRequestHandler}</code>.
 *
 * @author Alex Ruiz
 */
public class PingRequestHandlerTest {

  private TestServer testServer;
  private PingRequestHandler handler;

  @BeforeMethod public void setUp() {
    testServer = createMock(TestServer.class);
    handler = new PingRequestHandler(testServer);
  }

  @Test public void shouldReturnSuccessIfServerRunning() {
    final Request request = pingRequest();
    new EasyMockTemplate(testServer) {
      @Override protected void expectations() {
        expect(testServer.isRunning()).andReturn(true);
      }

      @Override protected void codeToTest() {
        Response response = handler.process(request);
        assertThat(response.successful()).isTrue();
      }
    }.run();
  }

  @Test public void shouldReturnFailureIfServerNotRunning() {
    final Request request = pingRequest();
    new EasyMockTemplate(testServer) {
      @Override protected void expectations() {
        expect(testServer.isRunning()).andReturn(false);
      }

      @Override protected void codeToTest() {
        Response response = handler.process(request);
        assertThat(response.successful()).isFalse();
      }
    }.run();
  }
}
