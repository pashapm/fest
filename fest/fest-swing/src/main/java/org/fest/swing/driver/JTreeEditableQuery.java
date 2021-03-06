package org.fest.swing.driver;

import javax.swing.JTree;

import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that indicates whether a <code>{@link JTree}</code> 
 * is editable or not.
 * @see JTree#isEditable()
 *
 * @author Alex Ruiz 
 */
final class JTreeEditableQuery {
  
  static boolean isEditable(final JTree tree) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return tree.isEditable();
      }
    });
  }
  
  private JTreeEditableQuery() {}
}