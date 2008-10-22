/*
 * Created on Oct 19, 2008
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
package org.fest.swing.core;

import java.awt.Component;

import static org.fest.swing.query.ComponentShowingQuery.isShowing;

/**
 * Understands a base class for implementations of <code>{@link ComponentMatcher}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class AbstractComponentMatcher implements ComponentMatcher {

  private final boolean requireShowing;

  /**
   * Creates a new </code>{@link AbstractComponentMatcher}</code>.
   */
  public AbstractComponentMatcher() {
    this(false);
  }
  
  /**
   * Creates a new </code>{@link AbstractComponentMatcher}</code>.
   * @param requireShowing indicates if the component to match should be showing or not.
   */
  public AbstractComponentMatcher(boolean requireShowing) {
    this.requireShowing = requireShowing;
  }

  /**
   * Indicates whether the component to match has to be showing.
   * @return <code>true</code> if the component to find has to be showing, <code>false</code> otherwise.
   */
  public final boolean requireShowing() { return requireShowing; }

  /**
   * Indicates if the value of the "isShowing" property of the given component matches the value specified in this
   * matcher.
   * @param c the component to verify.
   * @return <code>true</code> if the value of the "isShowing" property of the given component matches the value
   * specified in this matcher, <code>false</code> otherwise.
   */
  protected final boolean isShowingMatches(Component c) {
    return !requireShowing || isShowing(c);
  }
}