/*
 * Created on Oct 22, 2008
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

import javax.swing.JComboBox;

import static java.lang.String.valueOf;

import static org.fest.util.Strings.concat;

/**
 * Understands verification that a given number matches an index of an item in a <code>{@link JComboBox}</code>.
 *
 * @author Alex Ruiz 
 */
final class JComboBoxItemIndexValidator {

  // Not called in EDT
  static int validateIndex(JComboBox comboBox, int index) {
    int itemCount = comboBox.getItemCount();
    if (index >= 0 && index < itemCount) return index;
    throw new IndexOutOfBoundsException(concat(
        "Item index (", valueOf(index), ") should be between [0] and [", valueOf(itemCount - 1), "] (inclusive)"));
  }

  private JComboBoxItemIndexValidator() {}
}