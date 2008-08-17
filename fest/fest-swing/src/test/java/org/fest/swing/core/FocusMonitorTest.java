/*
 * Created on Apr 1, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.core;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.GuiActionRunner.execute;
import static org.fest.swing.testing.FocusSetter.setFocusOn;
import static org.fest.swing.testing.TestGroups.GUI;

/**
 * Tests for <code>{@link FocusMonitor}</code>.
 *
 * @author Alex Ruiz
 */
@Test(groups = GUI)
public class FocusMonitorTest {

  private FocusMonitor monitor;
  private MyWindow window;
  
  @BeforeMethod public void setUp() {
    window = MyWindow.showNew();
    window.display();
    setFocusOn(window.button);
    monitor = FocusMonitor.addFocusMonitorTo(window.button);
    assertThat(monitor.hasFocus()).isTrue();
  }
  
  @AfterMethod public void tearDown() {
    window.destroy();
  }
  
  public void shouldReturnFalseIfLosesFocus() {
    setFocusOn(window.textBox);
    assertThat(monitor.hasFocus()).isFalse();
  }

  public void shouldNotHaveFocusIsComponentIsNotFocusOwner() {
    setFocusOn(window.textBox);
    monitor = FocusMonitor.addFocusMonitorTo(window.button);
    assertThat(monitor.hasFocus()).isFalse();
  }
  
  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;
    
    final JButton button = new JButton("Click Me");
    final JTextField textBox = new JTextField(20); 
    
    static MyWindow showNew() {
      MyWindow window = execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() { return new MyWindow(); }
      });
      window.display();
      return window;
    }

    MyWindow() {
      super(FocusMonitorTest.class);
      addComponents(button, textBox);
    }
  }
}
