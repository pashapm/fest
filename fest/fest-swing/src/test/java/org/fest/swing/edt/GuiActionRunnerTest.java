/*
 * Created on Oct 17, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.edt;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.test.core.MethodInvocations;

import static org.easymock.EasyMock.*;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

/**
 * Tests for <code>{@link GuiActionRunner}</code>.
 *
 * @author Alex Ruiz
 */
@Test public class GuiActionRunnerTest {

  @AfterMethod public void tearDown() {
    GuiActionRunner.executeInEDT(true);
  }
  
  public void shouldExecuteTaskDirectlyIfActionsShouldNotBeExecutedInEDT() {
    TestGuiTask task = new TestGuiTask();
    GuiActionRunner.executeInEDT(false);
    GuiActionRunner.execute(task);
    assertThat(task.wasExecutedInEDT()).isFalse();
    task.requireInvoked("executeInEDT");
  }
  
  public void shouldWrapThrownExceptionWhenExecutingTaskDirectly() {
    final TestGuiTask task = createMock(TestGuiTask.class);
    final RuntimeException error = expectedError();
    new EasyMockTemplate(task) {
      protected void expectations() {
        task.executeInEDT();
        expectLastCall().andThrow(error);
      }

      protected void codeToTest() {
        try {
          GuiActionRunner.executeInEDT(false);
          GuiActionRunner.execute(task);
          failWhenExpectingException();
        } catch (UnexpectedException e) {
          assertThat(e.getCause()).isSameAs(error);
        }
      }
    }.run();
  }
  
  public void shouldExecuteQueryDirectlyIfActionsShouldNotBeExecutedInEDT() {
    String valueToReturnWhenExecuted = "Hello";
    TestGuiQuery query = new TestGuiQuery(valueToReturnWhenExecuted);
    GuiActionRunner.executeInEDT(false);
    String result = GuiActionRunner.execute(query);
    assertThat(result).isSameAs(valueToReturnWhenExecuted);
    assertThat(query.wasExecutedInEDT()).isFalse();
    query.requireInvoked("executeInEDT");
  }

  public void shouldWrapThrownExceptionWhenExecutingQueryDirectly() {
    final TestGuiQuery query = createMock(TestGuiQuery.class);
    final RuntimeException error = expectedError();
    new EasyMockTemplate(query) {
      protected void expectations() {
        expect(query.executeInEDT()).andThrow(error);
      }

      protected void codeToTest() {
        try {
          GuiActionRunner.executeInEDT(false);
          GuiActionRunner.execute(query);
          failWhenExpectingException();
        } catch (UnexpectedException e) {
          assertThat(e.getCause()).isSameAs(error);
        }
      }
    }.run();
  }

  private RuntimeException expectedError() {
    return new RuntimeException("Thrown on purpose");
  }

  private static class TestGuiTask extends GuiTask {
    private final MethodInvocations methodInvocations = new MethodInvocations();
    
    TestGuiTask() {}
    
    protected void executeInEDT() {
      methodInvocations.invoked("executeInEDT");
    }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
  
  private static class TestGuiQuery extends GuiQuery<String> {
    
    private final MethodInvocations methodInvocations = new MethodInvocations();
    private final String valueToReturnWhenExecuted;
    
    TestGuiQuery(String valueToReturnWhenExecuted) {
      this.valueToReturnWhenExecuted = valueToReturnWhenExecuted;
    }
    
    protected String executeInEDT() {
      methodInvocations.invoked("executeInEDT");
      return valueToReturnWhenExecuted;
    }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
