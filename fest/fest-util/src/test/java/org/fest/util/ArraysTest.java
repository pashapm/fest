/*
 * Created on May 13, 2007
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
package org.fest.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests for <code>{@link Arrays}</code>.
 *
 * @author Alex Ruiz
 */
public class ArraysTest {

  @Test public void shouldReturnIsEmptyIfArrayIsEmpty() {
    assertTrue(Arrays.isEmpty(new String[0]));
  }

  @Test public void shouldReturnIsEmptyIfArrayIsNull() {
    assertTrue(Arrays.isEmpty(null));
  }

  @Test public void shouldReturnIsNotEmptyIfArrayHasElements() {
    assertFalse(Arrays.isEmpty(new String[] { "Tuzi" }));
  }
}