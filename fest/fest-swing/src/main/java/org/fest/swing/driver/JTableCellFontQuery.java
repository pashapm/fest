/*
 * Created on Oct 21, 2008
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

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;

import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.driver.JTableCellRendererQuery.cellRendererIn;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the foreground color of the
 * <code>{@link Component}</code> used by the cell renderer for a particular cell in a <code>{@link JTable}</code>.
 *
 * @author Alex Ruiz 
 * @author Yvonne Wang
 */
final class JTableCellFontQuery {

  static Font cellFont(final JTable table, final int row, final int column) {
    return execute(new GuiQuery<Font>() {
      protected Font executeInEDT() {
        Component c = cellRendererIn(table, row, column);
        return c != null ? c.getFont() : null;
      }
    });
  }
  
  private JTableCellFontQuery() {}
}