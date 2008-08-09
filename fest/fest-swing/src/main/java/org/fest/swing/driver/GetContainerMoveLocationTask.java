/*
 * Created on Aug 7, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import org.fest.swing.core.GuiTask;

/**
 * Understands an action, executed in the event dispatch thread, that returns the point where the mouse usually grabs to
 * move a <code>{@link Container}</code>
 * 
 * @author Alex Ruiz
 */
class GetContainerMoveLocationTask extends GuiTask<Point> {

  private final Container container;

  static Point moveLocationOf(Container container) {
    return new GetContainerMoveLocationTask(container).run();
  }
  
  private GetContainerMoveLocationTask(Container container) {
    this.container = container;
  }
  
  protected Point executeInEDT() throws Throwable {
    return moveLocation(container.getSize(), container.getInsets());
  }

  private Point moveLocation(Dimension size, Insets insets) {
    return new Point(size.width / 2, insets.top / 2);
  }

}