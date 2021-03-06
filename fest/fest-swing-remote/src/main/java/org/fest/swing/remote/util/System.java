/*
 * Created on Dec 2, 2007
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
package org.fest.swing.remote.util;

/**
 * Understands system-related utility methods.
 *
 * @author Alex Ruiz
 */
public final class System {

  public static final String LINE_SEPARATOR = lineSeparator();
  
  private static String lineSeparator() {
    try {
      return java.lang.System.getProperty("line.separator");
    } catch (Exception e) {
      return "\n";
    }
  }
  
  private System() {}
}
