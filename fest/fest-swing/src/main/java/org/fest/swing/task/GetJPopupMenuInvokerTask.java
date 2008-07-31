/*
 * Created on Jul 31, 2008
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
package org.fest.swing.task;

import java.awt.Component;

import javax.swing.JPopupMenu;

import org.fest.swing.core.GuiTask;

/**
 * Understands a task that returns the invoker of a <code>{@link JPopupMenu}</code>.
 *
 * @author Alex Ruiz 
 */
public class GetJPopupMenuInvokerTask extends GuiTask<Component> {

  private final JPopupMenu popupMenu;

  /**
   * Returns the invoker of the given <code>{@link JPopupMenu}</code>. This action is executed in the event dispatch
   * thread.
   * @param popupMenu the given <code>JPopupMenu</code>.
   * @return the invoker of the given <code>JPopupMenu</code>.
   */
  public static Component invokerOf(JPopupMenu popupMenu) {
    return new GetJPopupMenuInvokerTask(popupMenu).run();
  }

  private GetJPopupMenuInvokerTask(JPopupMenu popupMenu) {
    this.popupMenu = popupMenu;
  }

  /**
   * Returns the invoker in this task's <code>{@link JPopupMenu}</code>. This action is executed in the event dispatch 
   * thread.
   * @return the invoker in this task's <code>JPopupMenu</code>.
   */
  protected Component executeInEDT() throws Throwable {
    return popupMenu.getInvoker();
  }
}
