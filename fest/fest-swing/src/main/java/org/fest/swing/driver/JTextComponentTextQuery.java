/*
 * Created on Aug 5, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the text of a 
 * <code>{@link JTextComponent}</code>.
 * @see JTextComponent#getText()
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
final class JTextComponentTextQuery {

  @RunsInEDT
  static String textOf(final JTextComponent textComponent) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return textComponent.getText();
      }
    });
  }

  private JTextComponentTextQuery() {}
}