/*
 * Created on Nov 29, 2008
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
package org.fest.swing.fixture;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.test.swing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.TestGroups.*;

/**
 * Test case for <a href="http://code.google.com/p/fest/issues/detail?id=134">Bug 134</a>.
 *
 * @author Alex Ruiz
 */
@Test(groups = { GUI, BUG })
public class Bug134_ClickNotVisibleButtonTest {

  private FrameFixture fixture;
  private MyWindow window;
  
  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
  
  @BeforeMethod public void setUp() {
    window = MyWindow.createNew();
    fixture = new FrameFixture(window);
    fixture.show();
  }
  
  @AfterMethod public void tearDown() {
    fixture.cleanUp();
  }

  public void shouldThrowErrorWhenClickingButtonOutOfScreen() {
    moveButtonOutOfScreen();
    try {
      fixture.button().click();
      failWhenExpectingException();
    } catch (ActionFailedException e) {
      assertThat(e.getMessage()).isEqualTo("The component to click is out of the boundaries of the screeen");
    }
  }

  private void moveButtonOutOfScreen() {
    Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    int x = screen.width - (window.getWidth() / 3);
    int y = screen.height / 2;
    fixture.moveTo(new Point(x, y));
  }
  
  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;
    
    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }
    
    final JTextField textField = new JTextField(20);
    final JButton button = new JButton("Click Me");
    
    private MyWindow() {
      super(Bug134_ClickNotVisibleButtonTest.class);
      addComponents(textField, button);
    }
  }
}
