/*
 * Created on Aug 8, 2008
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
package org.fest.swing.driver;

import javax.swing.JInternalFrame;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.test.swing.TestMdiWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.BasicRobot.robotWithNewAwtHierarchy;
import static org.fest.swing.test.core.TestGroups.*;

/**
 * Tests for <code>{@link JInternalFrameCloseTask}</code>.
 *
 * @author Yvonne Wang
 */
@Test(groups = { GUI, ACTION })
public class JInternalFrameCloseTaskTest {

  private Robot robot;
  private JInternalFrame internalFrame;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    TestMdiWindow window = TestMdiWindow.createNewWindow(getClass());
    internalFrame = window.internalFrame();
    robot.showWindow(window);
    assertThat(window.internalFrame().isVisible()).isTrue();
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  public void shouldCloseJInternalFrame() {
    JInternalFrameCloseTask.close(internalFrame);
    robot.waitForIdle();
    assertThat(internalFrame.isVisible()).isFalse();
  }
}
