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
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Rectangle;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.fest.swing.core.GuiQuery;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that converts the location in the model of a
 * <code>{@link JTextComponent}</code> to a place in the view coordinate system.
 * 
 * @author Alex Ruiz
 */
class JTextComponentModelToViewQuery extends GuiQuery<Rectangle> {

  private final JTextComponent textBox;
  private final int pos;

  static Rectangle modelToView(JTextComponent textBox, int pos) {
    return execute(new JTextComponentModelToViewQuery(textBox, pos));
  }
  
  private JTextComponentModelToViewQuery(JTextComponent textBox, int pos) {
    this.textBox = textBox;
    this.pos = pos;
  }
  
  protected Rectangle executeInEDT() throws BadLocationException {
    return textBox.modelToView(pos);
  }
}
