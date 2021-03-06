/*
 * Created on Aug 11, 2008
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

import org.testng.annotations.Test;

import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.timing.Condition;
import org.fest.swing.timing.Pause;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link GuiTask}</code>.
 *
 * @author Alex Ruiz
 */
@Test public class GuiTaskTest {

  @Test(expectedExceptions = ActionFailedException.class)
  public void shouldExecuteInEDTWhenNotCalledInEDT() {
    new GuiTaskInEDT().run();
  }

  public void shouldExecuteInEDTWhenCalledInEDT() {
    final GuiTaskInEDT task = new GuiTaskInEDT();
    execute(task);
    Pause.pause(new Condition("Task is executed") {
      public boolean test() {
        return task.wasExecutedInEDT();
      }
    });
    assertThat(task.executed()).isEqualTo(true);
  }

  private static class GuiTaskInEDT extends GuiTask {
    private boolean executed;

    GuiTaskInEDT() {}

    protected void executeInEDT() {
      executed = true;
    }

    boolean executed() { return executed; }
  }

}
