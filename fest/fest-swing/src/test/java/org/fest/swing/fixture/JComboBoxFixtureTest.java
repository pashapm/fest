/*
 * Created on Apr 9, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.easymock.classextension.EasyMock;
import org.testng.annotations.Test;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.driver.JComboBoxDriver;

import static org.easymock.EasyMock.*;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JComboBoxes.comboBox;
import static org.fest.swing.test.builder.JLists.list;
import static org.fest.util.Arrays.array;

/**
 * Tests for <code>{@link JComboBoxFixture}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test public class JComboBoxFixtureTest extends CommonComponentFixtureTestCase<JComboBox> {

  private JComboBoxDriver driver;
  private JComboBox target;
  private JComboBoxFixture fixture;

  void onSetUp() {
    driver = EasyMock.createMock(JComboBoxDriver.class);
    target = comboBox().createNew();
    fixture = new JComboBoxFixture(robot(), target);
    fixture.updateDriver(driver);
  }

  public void shouldCreateFixtureWithGivenComponentName() {
    String name = "comboBox";
    expectLookupByName(name, JComboBox.class);
    verifyLookup(new JComboBoxFixture(robot(), name));
  }

  public void shouldReturnContents() {
    final String[] contents = array("Frodo", "Sam");
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.contentsOf(target)).andReturn(contents);
      }

      protected void codeToTest() {
        Object[] result = fixture.contents();
        assertThat(result).isSameAs(contents);
      }
    }.run();
  }

  public void shouldReplaceText() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.replaceText(target, "Hello");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.replaceText("Hello"));
      }
    }.run();
  }

  public void shouldSelectAllText() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.selectAllText(target);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.selectAllText());
      }
    }.run();
  }

  public void shouldEnterText() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.enterText(target, "Hello");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.enterText("Hello"));
      }
    }.run();
  }

  public void shouldReturnList() {
    final JList list = list().createNew();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.dropDownList()).andReturn(list);
      }

      protected void codeToTest() {
        JList result = fixture.list();
        assertThat(result).isSameAs(list);
      }
    }.run();
  }

  public void shouldSelectItemUnderIndex() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.selectItem(target, 6);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.selectItem(6));
      }
    }.run();
  }

  public void shouldSelectItemWithText() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.selectItem(target, "Frodo");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.selectItem("Frodo"));
      }
    }.run();
  }

  public void shouldReturnValueAtIndex() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.value(target, 8)).andReturn("Sam");
      }

      protected void codeToTest() {
        Object result = fixture.valueAt(8);
        assertThat(result).isEqualTo("Sam");
      }
    }.run();
  }

  public void shouldRequireEditable() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.requireEditable(target);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireEditable());
      }
    }.run();
  }

  public void shouldRequireNotEditable() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.requireNotEditable(target);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireNotEditable());
      }
    }.run();
  }

  public void shouldRequireNoSelection() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.requireNoSelection(target);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireNoSelection());
      }
    }.run();
  }

  public void shouldSetCellReaderInDriver() {
    final JComboBoxCellReader reader = createMock(JComboBoxCellReader.class);
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.cellReader(reader);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.cellReader(reader));
      }
    }.run();
  }

  public void shouldRequireSelection() {
    final String value = "Hello";
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.requireSelection(target, value);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireSelection(value));
      }
    }.run();
  }

  ComponentDriver driver() { return driver; }
  JComboBox target() { return target; }
  JComboBoxFixture fixture() { return fixture; }
}
