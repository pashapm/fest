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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.JOptionPaneDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.timing.Timeout;

import static org.fest.swing.fixture.ComponentFixtureValidator.notNullRobot;

/**
 * Understands simulation of user events on a <code>{@link JOptionPane}</code> and verification of the state of such
 * <code>{@link JOptionPane}</code>.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneFixture extends ComponentFixture<JOptionPane> implements CommonComponentFixture {

  private JOptionPaneDriver driver;
  
  /**
   * Creates a new <code>{@link JOptionPaneFixture}</code>.
   * @param robot finds a showing <code>JOptionPane</code>, which will be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a showing <code>JOptionPane</code> could not be found.
   * @throws ComponentLookupException if more than one showing <code>JOptionPane</code> is found.
   */
  public JOptionPaneFixture(Robot robot) {
    this(robot, findShowingOptionPane(robot));
  }

  private static JOptionPane findShowingOptionPane(Robot robot) {
    notNullRobot(robot);
    return robot.finder().findByType(JOptionPane.class, true);
  }

  /**
   * Creates a new <code>{@link JOptionPaneFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JOptionPane</code>.
   * @param target the <code>JOptionPane</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws IllegalArgumentException if <code>target</code> is <code>null</code>.
   */
  public JOptionPaneFixture(Robot robot, JOptionPane target) {
    super(robot, target);
    updateDriver(new JOptionPaneDriver(robot));
  }

  final void updateDriver(JOptionPaneDriver newDriver) {
    driver = newDriver;
  }

  /**
   * Returns a fixture wrapping the "OK" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "OK" button.
   * @throws ComponentLookupException if the a "OK" button cannot be found.
   */
  public JButtonFixture okButton() {
    return new JButtonFixture(robot, driver.okButton(target));
  }

  /**
   * Returns a fixture wrapping the "Cancel" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "Cancel" button.
   * @throws ComponentLookupException if the a "Cancel" button cannot be found.
   */
  public JButtonFixture cancelButton() {
    return new JButtonFixture(robot, driver.cancelButton(target));
  }

  /**
   * Returns a fixture wrapping the "Yes" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "Yes" button.
   * @throws ComponentLookupException if the a "Yes" button cannot be found.
   */
  public JButtonFixture yesButton() {
    return new JButtonFixture(robot, driver.yesButton(target));
  }

  /**
   * Returns a fixture wrapping the "No" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "No" button.
   * @throws ComponentLookupException if the a "No" button cannot be found.
   */
  public JButtonFixture noButton() {
    return new JButtonFixture(robot, driver.noButton(target));
  }

  /**
   * Finds and returns a fixture wrapping a button (this fixture's <code>{@link JOptionPane}</code>) containing the
   * given text.
   * @param text the text of the button to find and return.
   * @return a fixture wrapping a button containing the given text.
   * @throws ComponentLookupException if the a button with the given text cannot be found.
   */
  public JButtonFixture buttonWithText(String text) {
    return new JButtonFixture(robot, driver.buttonWithText(target, text));
  }

  /**
   * Finds the first <code>{@link JButton}</code> in this fixture's <code>{@link JOptionPane}</code>.
   * @return a fixture wrapping the first <code>JButton</code> contained in this fixture's <code>JOptionPane</code>.
   */
  public JButtonFixture button() {
    return new JButtonFixture(robot, driver.button(target));
  }

  /**
   * Returns the <code>{@link JTextComponent}</code> in the given message only if the message is of type input.
   * @return the text component in the given message.
   * @throws ComponentLookupException if the message type is not input and therefore it does not contain a text component.
   */
  public JTextComponentFixture textBox() {
    return new JTextComponentFixture(robot, driver.textBox(target));
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   */
  public JOptionPaneFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @param button the button to click.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   * @return this fixture.
   */
  public JOptionPaneFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   */
  public JOptionPaneFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   */
  public JOptionPaneFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   */
  public JOptionPaneFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JOptionPane</code> is not showing on the screen.
   */
  public JOptionPaneFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying an error message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireErrorMessage() {
    driver.requireErrorMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying an information
   * message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireInformationMessage() {
    driver.requireInformationMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a warning message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireWarningMessage() {
    driver.requireWarningMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a question.
   * @return this fixture.
   */
  public JOptionPaneFixture requireQuestionMessage() {
    driver.requireQuestionMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a plain message.
   * @return this fixture.
   */
  public JOptionPaneFixture requirePlainMessage() {
    driver.requirePlainMessage(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JOptionPane}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JOptionPaneFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys this fixture's <code>{@link JOptionPane}</code>. This method
   * does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JOptionPane}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JOptionPane}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> has the given title.
   * @param title the title to match.
   * @return this fixture.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not have the given title.
   */
  public JOptionPaneFixture requireTitle(String title) {
    driver.requireTitle(target, title);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> shows the given message.
   * @param message the message to verify.
   * @return this fixture.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not show the given message.
   */
  public JOptionPaneFixture requireMessage(Object message) {
    driver.requireMessage(target, message);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> has the given options.
   * @param options the options to verify.
   * @return this fixture.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not have the given options.
   */
  public JOptionPaneFixture requireOptions(Object[] options) {
    driver.requireOptions(target, options);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JOptionPane</code> does not have input focus.
   */
  public JOptionPaneFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JOptionPane</code> is disabled.
   */
  public JOptionPaneFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's <code>JOptionPane</code> is never enabled.
   */
  public JOptionPaneFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JOptionPane</code> is enabled.
   */
  public JOptionPaneFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JOptionPane</code> is not visible.
   */
  public JOptionPaneFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JOptionPane</code> is visible.
   */
  public JOptionPaneFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
}
