/*
 * Created on Sep 10, 2007
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

import java.awt.Component;

import javax.swing.JTable;

import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.data.TableCell;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;

import static org.fest.swing.core.MouseButton.*;

/**
 * Understands simulation of user events on a cell in a <code>{@link JTable}</code> and verification of the state of
 * such table cell.
 * <p>
 * Example:
 * <pre>
 * // import static org.fest.swing.fixture.TableCellBuilder.row;
 * {@link JTableCellFixture} cell = dialog.{@link JTableFixture table}("records").cell({@link TableCell#row(int) row}(3).column(0));
 * cell.select().showPopupMenu();
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 *
 * @see TableCell
 */
public class JTableCellFixture implements ItemFixture {

  private final JTableFixture table;
  private final TableCell cell;

  /**
   * Creates a new <code>{@link JTableCellFixture}</code>.
   * @param table manages the <code>JTable</code> containing the table cell to be managed by this fixture.
   * @param cell row and column indices of the table cell to be managed by this fixture.
   * @throws NullPointerException if <code>table</code> is <code>null</code>.
   * @throws NullPointerException if <code>cell</code> is <code>null</code>.
   */
  protected JTableCellFixture(JTableFixture table, TableCell cell) {
    if (table == null) 
      throw new NullPointerException("The JTableFixture should not be null");
    if (cell == null) 
      throw new NullPointerException("The TableCell should not be null");
    this.table = table;
    this.cell = cell;
  }

  JTableFixture table() { return table; }
  TableCell cell() { return cell; }

  /**
   * Simulates a user selecting this fixture's table cell.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture select() {
    table.selectCell(cell);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's table cell.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture click() {
    table.click(cell, LEFT_BUTTON);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's table cell.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture click(MouseClickInfo mouseClickInfo) {
    table.click(cell, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's table cell.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture doubleClick() {
    return click(LEFT_BUTTON, 2);
  }

  /**
   * Simulates a user right-clicking this fixture's table cell.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture rightClick() {
    return click(RIGHT_BUTTON);
  }

  /**
   * Simulates a user clicking a cell in this fixture's table cell once, using the specified mouse button.
   * @param button the mouse button to use.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableCellFixture click(MouseButton button) {
    table.click(cell, button);
    return this;
  }

  private JTableCellFixture click(MouseButton button, int times) {
    table.click(cell, button, times);
    return this;
  }

  /**
   * Starts editing this fixture's table cell. This method should be called <strong>before</strong> manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #editor()}</code>.
   * <p>
   * This method uses the <code>{@link JTableCellWriter}</code> from the <code>{@link JTableFixture}</code> that
   * created this fixture.
   * </p>
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if this cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableFixture#cellWriter(JTableCellWriter)
   * @see JTableCellWriter
   * @see #editor()
   */
  public JTableCellFixture startEditing() {
    table.driver().startCellEditing(table.target, cell);
    return this;
  }

  /**
   * Stops editing this fixture's table cell. This method should be called <strong>after</strong> manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #editor()}</code>.
   * <p>
   * This method uses the <code>{@link JTableCellWriter}</code> from the <code>{@link JTableFixture}</code> that
   * created this fixture.
   * </p>
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if this cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableFixture#cellWriter(JTableCellWriter)
   * @see JTableCellWriter
   * @see #editor()
   */
  public JTableCellFixture stopEditing() {
    table.driver().stopCellEditing(table.target, cell);
    return this;
  }

  /**
   * Cancels editing this fixture's table cell. This method should be called <strong>after</strong> manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #editor()}</code>.
   * <p>
   *
   * <pre>
   * TableCellFixture cell = table.cell(row(6).column(8));
   * Component editor = cell.editor();
   * // assume editor is a JTextField
   * JTextComponentFixture editorFixture = new JTextComponentFixture(robot, (JTextField) editor);
   * cell.{@link #startEditing()};
   * editorFixture.enterText(&quot;Hello&quot;);
   * // discard any entered value
   * cell.cancelEditing();
   * </pre>
   *
   * </p>
   * <p>
   * This method uses the <code>{@link JTableCellWriter}</code> from the <code>{@link JTableFixture}</code> that
   * created this fixture.
   * </p>
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if this cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableFixture#cellWriter(JTableCellWriter)
   * @see JTableCellWriter
   * @see #editor()
   */
  public JTableCellFixture cancelEditing() {
    table.driver().cancelCellEditing(table.target, cell);
    return this;
  }

  /**
   * Returns the editor of this fixture's table cell. To manipulate the editor (e.g. wrapping it with a
   * <code>ComponentFixture</code>,) the method <code>{@link #startEditing()}</code> should be called first. To
   * apply any changes back to the table cell, the method <code>{@link #stopEditing()}</code> should be called. This
   * method uses the <code>{@link JTableCellWriter}</code> from the <code>{@link JTableFixture}</code> that created
   * this fixture.
   * <p>
   * Example:
   *
   * <pre>
   * TableCellFixture cell = table.cell(row(6).column(8));
   * Component editor = cell.editor();
   * // assume editor is a JTextField
   * JTextComponentFixture editorFixture = new JTextComponentFixture(robot, (JTextField) editor);
   * cell.{@link #startEditing()};
   * editorFixture.enterText(&quot;Hello&quot;);
   * cell.{@link #stopEditing()};
   * </pre>
   *
   * </p>
   * @return the editor of this fixture's table cell.
   * @see JTableFixture#cellWriter(JTableCellWriter)
   * @see JTableCellWriter
   */
  public Component editor() {
    return table.driver().cellEditor(table.target, cell);
  }

  /**
   * Enters the given value to this fixture's table cell. This method starts cell edition, enters the given value and
   * stops cell edition. To change the value of a cell, only a call to this method is necessary. If you need more
   * flexibility, you can retrieve the cell editor with <code>{@link #editor()}</code>.
   * <p>
   * This method uses the <code>{@link JTableCellWriter}</code> from the <code>{@link JTableFixture}</code> that
   * created this fixture.
   * </p>
   * @param value the value to enter in the cell.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if this cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this driver's <code>JTableCellValueReader</code> is unable to enter the given
   * value.
   * @see JTableFixture#cellWriter(JTableCellWriter)
   * @see JTableCellWriter
   */
  public JTableCellFixture enterValue(String value) {
    table.driver().enterValueInCell(table.target, cell, value);
    return this;
  }

  /**
   * Asserts that this fixture's table cell contains the given value.
   * @param value the expected value of this fixture's table cell.
   * @return this fixture.
   * @throws AssertionError if the value of this fixture's table cell is not equal to the expected one.
   */
  public JTableCellFixture requireValue(String value) {
    table.requireCellValue(cell, value);
    return this;
  }

  /**
   * Returns a fixture that verifies the font of this fixture's table cell. This method uses the
   * <code>{@link JTableCellReader}</code> from the <code>{@link JTableFixture}</code> that created this fixture.
   * @return a fixture that verifies the font of this fixture's table cell.
   * @see JTableFixture#cellReader(JTableCellReader)
   * @see JTableCellReader
   */
  public FontFixture font() {
    return table.fontAt(cell);
  }

  /**
   * Returns a fixture that verifies the background color of this fixture's table cell. This method uses the
   * <code>{@link JTableCellReader}</code> from the <code>{@link JTableFixture}</code> that created this fixture.
   * @return a fixture that verifies the background color of this fixture's table cell.
   * @see JTableFixture#cellReader(JTableCellReader)
   * @see JTableCellReader
   */
  public ColorFixture background() {
    return table.backgroundAt(cell);
  }

  /**
   * Returns a fixture that verifies the foreground color of this fixture's table cell. This method uses the
   * <code>{@link JTableCellReader}</code> from the <code>{@link JTableFixture}</code> that created this fixture.
   * @return a fixture that verifies the foreground color of this fixture's table cell.
   * @see JTableFixture#cellReader(JTableCellReader)
   * @see JTableCellReader
   */
  public ColorFixture foreground() {
    return table.foregroundAt(cell);
  }

  /**
   * Returns the <code>String</code> representation of the value of this fixture's table cell. This method uses the
   * <code>{@link JTableCellReader}</code> from the <code>{@link JTableFixture}</code> that created this fixture.
   * @return the <code>String</code> representation of the value of this fixture's table cell.
   * @see JTableFixture#cellReader(JTableCellReader)
   * @see JTableCellReader
   */
  public String value() {
    return table.valueAt(cell);
  }

  /**
   * Simulates a user dragging this fixture's table cell.
   * @return this fixture.
   */
  public JTableCellFixture drag() {
    table.drag(cell);
    return this;
  }

  /**
   * Simulates a user dropping into this fixture's table cell.
   * @return this fixture.
   */
  public JTableCellFixture drop() {
    table.drop(cell);
    return this;
  }

  /**
   * Shows a pop-up menu using this fixture's table cell as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return table.showPopupMenuAt(cell);
  }

  /**
   * Asserts that this fixture's table cell is editable.
   * @return this fixture.
   * @throws AssertionError if this fixture's table cell is not editable.
   */
  public JTableCellFixture requireEditable() {
    table.requireEditable(cell);
    return this;
  }


  /**
   * Asserts that this fixture's table cell is not editable.
   * @return this fixture.
   * @throws AssertionError if this fixture's table cell is editable.
   */
  public JTableCellFixture requireNotEditable() {
    table.requireNotEditable(cell);
    return this;
  }

  /**
   * Returns the row index of this fixture's table cell.
   * @return the row index of this fixture's table cell.
   */
  public int row() { return cell.row; }

  /**
   * Returns the column index of this fixture's table cell.
   * @return the column index of this fixture's table cell.
   */
  public int column() { return cell.column; }
}
