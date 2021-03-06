/*
 * Created on Feb 14, 2008
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
package org.fest.assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Fail.*;

/**
 * Understands assertion methods for <code>long</code> arrays. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(long[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class LongArrayAssert extends ArrayAssert<long[]> {

  LongArrayAssert(long... actual) {
    super(actual);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(values).<strong>as</strong>(&quot;Some values&quot;).isNotEmpty();
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public LongArrayAssert as(String description) {
    description(description);
    return this;
  }

  /**
   * Alternative to <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(values).<strong>describedAs</strong>(&quot;Some values&quot;).isNotEmpty();
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public LongArrayAssert describedAs(String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(values).<strong>as</strong>(new BasicDescription(&quot;Some values&quot;)).isNotEmpty();
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public LongArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /**
   * Alternative to <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(values).<strong>describedAs</strong>(new BasicDescription(&quot;Some values&quot;)).isNotEmpty();
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public LongArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>long</code> array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not contain the given values.
   */
  public LongArrayAssert contains(long...values) {
    isNotNull();
    assertContains(list(values));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array does not contain the given objects, or if the
   *          actual <code>long</code> array contains elements other than the ones specified.
   */
  public LongArrayAssert containsOnly(long...values) {
    isNotNull();
    assertContainsOnly(list(values));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array contains any of the given values.
   */
  public LongArrayAssert excludes(long...values) {
    isNotNull();
    assertExcludes(list(values));
    return this;
  }

  List<Object> copyActual() {
    return list(actual);
  }

  private List<Object> list(long[] values) {
    List<Object> list = new ArrayList<Object>();
    for (long value : values) list.add(value);
    return list;
  }

  /**
   * Verifies that the actual <code>long</code> array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array does not satisfy the given condition.
   * @throws IllegalArgumentException if the given condition is null.
   */
  public LongArrayAssert satisfies(Condition<long[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array satisfies the given condition.
   * @throws IllegalArgumentException if the given condition is null.
   */
  public LongArrayAssert doesNotSatisfy(Condition<long[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   */
  public LongArrayAssert isNotNull() {
    assertArrayNotNull();
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the actual <code>long</code> array is empty.
   */
  public LongArrayAssert isNotEmpty() {
    assertNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(long[], long[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is not equal to the given one.
   */
  public LongArrayAssert isEqualTo(long[] expected) {
    if (!Arrays.equals(actual, expected)) fail(errorMessageIfNotEqual(actual, expected));
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(long[], long[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is equal to the given one.
   */
  public LongArrayAssert isNotEqualTo(long[] array) {
    if (Arrays.equals(actual, array)) fail(errorMessageIfEqual(actual, array));
    return this;
  }

  /**
   * Verifies that the number of elements in the actual <code>long</code> array is equal to the given one.
   * @param expected the expected number of elements in the actual <code>long</code> array.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is <code>null</code>.
   * @throws AssertionError if the number of elements in the actual <code>long</code> array is not equal to the given
   *          one.
   */
  public LongArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Returns the number of elements in the actual <code>int</code> array.
   * @return the number of elements in the actual <code>int</code> array.
   */
  protected int actualGroupSize() {
    isNotNull();
    return actual.length;
  }

  /**
   * Verifies that the actual <code>long</code> array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is not the same as the given one.
   */
  public LongArrayAssert isSameAs(long[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual <code>long</code> array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>long</code> array is the same as the given one.
   */
  public LongArrayAssert isNotSameAs(long[] expected) {
    assertNotSameAs(expected);
    return this;
  }
}
