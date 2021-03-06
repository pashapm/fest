/*
 * Created on Jan 27, 2008
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

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.exception.LocationUnavailableException;

import static java.lang.String.valueOf;

import static org.fest.swing.driver.JTabbedPaneTabIndexQuery.indexOfTab;
import static org.fest.util.Strings.*;

/**
 * Understands a location on a <code>{@link JTabbedPane}</code> (notably a tab).
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTabbedPaneLocation {

  /**
   * Returns the index of the first tab that matches the given <code>String</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param tabbedPane the target <code>JTabbedPane</code>.
   * @param title the title to match.
   * @return the index of the first tab that matches the given <code>String</code>.
   * @throws LocationUnavailableException if a tab matching the given title could not be found.
   */
  @RunsInCurrentThread
  public int indexOf(final JTabbedPane tabbedPane, final String title) {
    int index = indexOfTab(tabbedPane, title);
    if (index >= 0) return index;
    throw new LocationUnavailableException(concat("Unable to find a tab with title ", quote(title)));
  }

  /**
   * Returns the coordinates of the tab under the given index.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for 
   * invoking this method in the EDT.
   * </p>
   * @param tabbedPane the target <code>JTabbedPane</code>.
   * @param index the given index.
   * @return the coordinates of the tab under the given index.
   * @throws IndexOutOfBoundsException if the given index is negative or out of bounds.
   * @throws LocationUnavailableException if the tab under the given index is not visible.
   */
  @RunsInCurrentThread
  public Point pointAt(final JTabbedPane tabbedPane, final int index) {
    validateIndex(tabbedPane, index);
    Rectangle rect = tabbedPane.getUI().getTabBounds(tabbedPane, index);
    // From Abbot: TODO figure out the effects of tab layout policy sometimes tabs are not directly visible
    if (rect == null || rect.x < 0)
      throw new LocationUnavailableException(concat("The tab '", valueOf(index), "' is not visible"));
    return new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
  }

  @RunsInCurrentThread
  void validateIndex(JTabbedPane tabbedPane, int index) {
    int max = tabbedPane.getTabCount() - 1;
    if (index >= 0 && index <= max) return;
    throw new IndexOutOfBoundsException(concat(
        "Index <", valueOf(index), "> is not within the JTabbedPane bounds of <0> and <", valueOf(max), "> (inclusive)"));
  }
}
