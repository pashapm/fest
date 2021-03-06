/*
 * Created on Oct 20, 2006
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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import javax.swing.text.JTextComponent;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.JTextComponentDriver;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands simulation of user events on a <code>{@link JTextComponent}</code> and verification of the state of such
 * <code>{@link JTextComponent}</code>.
 *
 * @author Alex Ruiz
 */
public class JTextComponentFixture extends JPopupMenuInvokerFixture<JTextComponent> 
    implements CommonComponentFixture, TextInputFixture {

  private JTextComponentDriver driver;

  /**
   * Creates a new <code>{@link JTextComponentFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JTextComponent</code>.
   * @param target the <code>JTextComponent</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JTextComponentFixture(Robot robot, JTextComponent target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JTextComponentFixture}</code>.
   * @param robot performs simulation of user events on a <code>JTextComponent</code>.
   * @param textComponentName the name of the <code>JTextComponent</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JTextComponent</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JTextComponent</code> is found.
   */
  public JTextComponentFixture(Robot robot, String textComponentName) {
    super(robot, textComponentName, JTextComponent.class);
    createDriver();
  }

  private void createDriver() {
    updateDriver(new JTextComponentDriver(robot));
  }

  void updateDriver(JTextComponentDriver newDriver) {
    driver = newDriver;
  }

  /**
   * Returns the text of this fixture's <code>{@link JTextComponent}</code>.
   * @return the text of this fixture's <code>JTextComponent</code>.
   */
  public String text() {
    return target.getText();
  }

  /**
   * Simulates a user selecting the given text contained in this fixture's <code>{@link JTextComponent}</code>.
   * @param text the text to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @throws IllegalArgumentException if this fixture's <code>JTextComponent</code> does not contain the given text to 
   * select.
   * @throws ActionFailedException if the selecting the text in the given range fails.
   */
  public JTextComponentFixture select(String text) {
    driver.selectText(target, text);
    return this;
  }

  /**
   * Simulates a user selecting a portion of the text contained in this fixture's <code>{@link JTextComponent}</code>.
   * @param start index where selection should start.
   * @param end index where selection should end.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @throws ActionFailedException if the selecting the text in the given range fails.
   */
  public JTextComponentFixture selectText(int start, int end) {
    driver.selectText(target, start, end);
    return this;
  }

  /**
   * Simulates a user selecting all the text contained in this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture selectAll() {
    driver.selectAll(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTextComponent}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTextComponent}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user deleting all the text in this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture deleteText() {
    driver.deleteText(target);
    return this;
  }

  /**
   * Simulates a user entering the given text in this fixture's <code>{@link JTextComponent}</code>.
   * @param text the text to enter.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture enterText(String text) {
    driver.enterText(target, text);
    return this;
  }

  /**
   * Sets the text in this fixture's <code>{@link JTextComponent}</code>. Unlike 
   * <code>{@link #enterText(String)}</code>, this method bypasses the event system and allows immediate updating on the 
   * underlying document model.
   * <p>
   * Primarily desired for speeding up tests when precise user event fidelity isn't necessary.
   * </p>
   * @param text the text to set.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture setText(String text) {
    driver.setText(target, text);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JTextComponent}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   */
  public JTextComponentFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JTextComponent}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JTextComponentFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys in this fixture's <code>{@link JTextComponent}</code>. This
   * method does not affect the current focus.
   * @param keyCodes the codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTextComponentFixture pressAndReleaseKeys(int...keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JTextComponent}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTextComponentFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JTextComponent}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTextComponent</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTextComponentFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JTextComponent}</code> is equal to the specified
   * <code>String</code>.
   * @param expected the text to match.
   * @return this fixture.
   * @throws AssertionError if the text of this fixture's <code>JTextComponent</code> is not equal to the given one.
   */
  public JTextComponentFixture requireText(String expected) {
    driver.requireText(target, expected);
    return this;
  }

  /**
   * Asserts that the target text component does not contain any text.
   * @return this fixture.
   * @throws AssertionError if the target text component is not empty.
   */
  public JTextComponentFixture requireEmpty() {
    driver.requireEmpty(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> does not have input focus.
   */
  public JTextComponentFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }
  
  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is disabled.
   */
  public JTextComponentFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JTextComponent</code> is never enabled.
   */
  public JTextComponentFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is enabled.
   */
  public JTextComponentFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is not visible.
   */
  public JTextComponentFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is visible.
   */
  public JTextComponentFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
  
  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is editable.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is not editable.
   * @return this fixture. 
   */
  public JTextComponentFixture requireEditable() {
    driver.requireEditable(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTextComponent}</code> is not editable.
   * @throws AssertionError if this fixture's <code>JTextComponent</code> is editable.
   * @return this fixture. 
   */
  public JTextComponentFixture requireNotEditable() {
    driver.requireNotEditable(target);
    return this;
  }
}
