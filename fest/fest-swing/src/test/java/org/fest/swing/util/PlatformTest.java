/*
 * Created on Aug 27, 2007
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
package org.fest.swing.util;

import java.awt.Event;
import java.awt.event.KeyEvent;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.Platform.isOSX;

/**
 * Tests for <code>{@link Platform}</code>
 *
 * @author Alex Ruiz
 */
public class PlatformTest {

  @Test public void shouldReturnControlOrCommandKey() {
    int controlOrCommandKey = Platform.controlOrCommandKey();
    if (isOSX()) {
      assertThat(controlOrCommandKey).isEqualTo(KeyEvent.VK_META);
    } else  
      assertThat(controlOrCommandKey).isEqualTo(KeyEvent.VK_CONTROL);
  }

  @Test public void shouldReturnControlOrCommandMask() {
    int controlOrCommandMask = Platform.controlOrCommandMask();
    if (isOSX()) {
      assertThat(controlOrCommandMask).isEqualTo(Event.META_MASK);
    } else  
      assertThat(controlOrCommandMask).isEqualTo(Event.CTRL_MASK);
  }
}
