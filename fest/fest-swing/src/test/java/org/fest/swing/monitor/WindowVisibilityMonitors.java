/*
 * Created on Oct 18, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.monitor;

import java.awt.Window;
import java.util.EventListener;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Collections.filter;
import static org.fest.util.Collections.list;
import static org.fest.util.TypeFilter.byType;

/**
 * Understands utility methods related to <code>{@link WindowVisibilityMonitor}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowVisibilityMonitors {

  public static void assertWindowVisibilityMonitorCount(Window w, int expected) {
    assertCountIn(w.getWindowListeners(), expected);
    assertCountIn(w.getComponentListeners(), expected);
  }

  private static void assertCountIn(EventListener[] listeners, int expected) {
    assertThat(countIn(listeners)).isEqualTo(expected);
  }

  private static int countIn(Object[] listeners) {
    List<WindowVisibilityMonitor> filtered = filter(list(listeners), byType(WindowVisibilityMonitor.class));
    return filtered.size();
  }

  private WindowVisibilityMonitors() {}
}
