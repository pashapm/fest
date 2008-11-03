/*
 * Created on Jan 10, 2008
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

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.edt.CheckThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link NameAndTypeMatcher}</code>.
 *
 * @author Alex Ruiz
 */
public class NameAndTypeMatcherTest {
  
  private MyTextField textField;
  
  @BeforeClass public void setUpOnce() {
    CheckThreadViolationRepaintManager.install();
  }

  @BeforeMethod public void setUp() {
    textField = MyTextField.createNew("myTextField");
  }
  
  @Test(expectedExceptions = NullPointerException.class) 
  public void shouldThrowErrorIfNameIsNull() {
    new NameAndTypeMatcher(null, JTextComponent.class);
  }
  
  @Test(expectedExceptions = IllegalArgumentException.class) 
  public void shouldThrowErrorIfNameIsEmpty() {
    new NameAndTypeMatcher("", JTextComponent.class);
  }

  @Test(expectedExceptions = NullPointerException.class) 
  public void shouldThrowErrorIfTypeIsNull() {
    new NameAndTypeMatcher("myTextField", null);
  }

  @Test public void shouldFindComponentWithIfNameTypeAndShowingMatch() {
    NameAndTypeMatcher matcher = new NameAndTypeMatcher("myTextField", JTextComponent.class, true);
    textField.setShowing(true);
    assertThat(matcher.matches(textField)).isTrue();
  }  

  @Test public void shouldNotFindShowingComponentIfNameAndTypeMatchButNotShowing() {
    NameAndTypeMatcher matcher = new NameAndTypeMatcher("myTextField", JTextComponent.class, true);
    textField.setShowing(false);
    assertThat(matcher.matches(textField)).isFalse();
  }  

  @Test public void shouldFindShowingComponentIfNameAndTypeMatch() {
    NameAndTypeMatcher matcher = new NameAndTypeMatcher("myTextField", JTextComponent.class);
    textField.setShowing(false);
    assertThat(matcher.matches(textField)).isTrue();
  }  

  private static class MyTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    private boolean showing;

    static MyTextField createNew(final String name) {
      return execute(new GuiQuery<MyTextField>() {
        protected MyTextField executeInEDT() {
          MyTextField textField = new MyTextField();
          textField.setName(name);
          return textField;
        }
      });
    }
    
    private MyTextField() {}
    
    public synchronized void setShowing(boolean showing) {
      this.showing = showing;
    }
    
    @Override public boolean isShowing() {
      return showing;
    }
  }
}
