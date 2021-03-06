/*
 * Created on Aug 6, 2008
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

import javax.swing.JComboBox;

import org.testng.annotations.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.MethodInvocations;
import org.fest.swing.test.swing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.BasicRobot.robotWithNewAwtHierarchy;
import static org.fest.swing.driver.JComboBoxSetSelectedIndexTask.setSelectedIndex;
import static org.fest.swing.test.core.TestGroups.*;

/**
 * Tests for <code>{@link JComboBoxSelectedIndexQuery}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test(groups = { GUI, ACTION })
public class JComboBoxSelectedIndexQueryTest {

  private Robot robot;
  private MyComboBox comboBox;

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    MyWindow window = MyWindow.createNew();
    comboBox = window.comboBox;
    robot.showWindow(window);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  @Test(dataProvider = "selectedIndices", groups = { GUI, ACTION })
  public void shouldReturnItemCountOfJComboBox(final int selectedIndex) {
    comboBox.startRecording();
    setSelectedIndex(comboBox, selectedIndex);
    assertThat(JComboBoxSelectedIndexQuery.selectedIndexOf(comboBox)).isEqualTo(selectedIndex);
    comboBox.requireInvoked("getSelectedIndex");
  }

  @DataProvider(name = "selectedIndices") public Object[][] selectedIndices() {
    return new Object[][] { { 0 }, { 1 }, { 2 }, { -1 } };
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final MyComboBox comboBox = new MyComboBox("one", "two", "three");

    @RunsInEDT
    static MyWindow createNew() {
      return GuiActionRunner.execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() throws Throwable {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JComboBoxSelectedIndexQueryTest.class);
      add(comboBox);
    }
  }

  private static class MyComboBox extends JComboBox {
    private static final long serialVersionUID = 1L;

    private boolean recording;
    private final MethodInvocations methodInvocations = new MethodInvocations();

    MyComboBox(Object... items) {
      super(items);
    }

    @Override public int getSelectedIndex() {
      if (recording) methodInvocations.invoked("getSelectedIndex");
      return super.getSelectedIndex();
    }

    void startRecording() { recording = true; }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
