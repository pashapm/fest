/*
 * Created on Jun 18, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.String.valueOf;

import static org.fest.assertions.Fail.fail;
import static org.fest.assertions.PrimitiveFail.failIfEqual;
import static org.fest.assertions.PrimitiveFail.failIfNotEqual;
import static org.fest.assertions.PrimitiveFail.failIfNotGreaterThan;
import static org.fest.assertions.PrimitiveFail.failIfNotLessThan;
import static org.fest.util.Strings.concat;

/**
 * Understands assertion methods for <code>char</code>s.
 * 
 * @author Yvonne Wang
 */
public class CharAssert {

  private final char actual;

  CharAssert(char actual) {
    this.actual = actual;
  }

  public CharAssert isEqualTo(char expected) {
    failIfNotEqual(actual, expected);
    return this;
  }

  public CharAssert isNotEqualTo(char other) {
    failIfEqual(actual, other);
    return this;
  }

  public CharAssert isGreaterThan(char smaller) {
    failIfNotGreaterThan(actual, smaller);
    return this;
  }

  public CharAssert isLessThan(char bigger) {
    failIfNotLessThan(actual, bigger);
    return this;
  }

  public CharAssert isUpperCase() {
    if (!Character.isUpperCase(actual)) fail(concat(valueOf(actual), " should be an uppercase character"));
    return this;
  }

  public CharAssert isLowerCase() {
    if (!Character.isLowerCase(actual)) fail(concat(valueOf(actual), " should be a lowercase character"));
    return this;
  }
}