/*
 * Created on Dec 2, 2007
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.reflect.Reflection.field;
import static org.fest.swing.remote.core.PingRequest.pingRequest;
import static org.fest.swing.remote.core.TestServer.DEFAULT_PORT;
import static org.fest.swing.remote.core.TestServerUtils.waitUntilStarts;
import static org.fest.swing.remote.util.Serialization.deserialize;
import static org.fest.swing.remote.util.Serialization.serialize;

import java.net.ServerSocket;
import java.net.Socket;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link DefaultTestServer}</code>.
 *
 * @author Alex Ruiz
 */
public class DefaultTestServerTest {

  private DefaultTestServer server;

  @BeforeMethod public void setUp() {
    server = new DefaultTestServer();
  }

  @Test public void shouldStartAtDefaultPortAndStop() {
    server.start();
    waitUntilStarts(server);
    assertServerStarted(DEFAULT_PORT);
  }

  @Test(dependsOnMethods = "shouldStartAtDefaultPortAndStop")
  public void shouldStartAtGivenPortAndStop() {
    int expectedPort = 4000;
    server.start(expectedPort);
    waitUntilStarts(server);
    assertServerStarted(expectedPort);
  }

  private void assertServerStarted(int expectedPort) {
    ServerSocketAssert socket = new ServerSocketAssert(serverSocket());
    assertThat(socket).isConnectedTo(expectedPort);
    assertThat(server.isRunning()).isTrue();
    server.stop();
    assertThat(socket).isClosed();
    assertThat(server.isRunning()).isFalse();
  }

  private ServerSocket serverSocket() {
    return field("serverSocket").ofType(ServerSocket.class).in(server).get();
  }

  @Test(dependsOnMethods = "shouldStartAtGivenPortAndStop")
  public void shouldProcessClientRequest() throws Exception {
    server.start();
    waitUntilStarts(server);
    try {
      Socket client = new Socket("localhost", DEFAULT_PORT);
      serialize(pingRequest(), client.getOutputStream());
      Response response = deserialize(client.getInputStream(), Response.class);
      assertThat(response.successful()).isTrue();
      client.close();
    } finally {
      server.stop();
    }
  }
}
