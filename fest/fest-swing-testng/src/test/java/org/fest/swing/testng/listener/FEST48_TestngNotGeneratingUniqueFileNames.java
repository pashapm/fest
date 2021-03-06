/*
 * Created on Feb 15, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.testng.listener;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.testng.listener.ScreenshotFileNameGenerator.screenshotFileNameFrom;
import static org.fest.util.Arrays.array;

/**
 * Tests for issue <a href="https://kenai.com/jira/browse/FEST-48" target="_blank">FEST-48</a>.
 *
 * @author Alex Ruiz
 */
@Test public class FEST48_TestngNotGeneratingUniqueFileNames {

  private TestResultStub testResult;

  @BeforeMethod public void setUp() {
    testResult = new TestResultStub();
  }

  public void shouldGenerateDifferentFileNamesUsingParameterValues() {
    testResult.getMethod().setMethodName("myMethod");
    testResult.getTestClass().setName("MyClass");
    assertThat(screenshotFileNameFrom(testResult)).isEqualTo("MyClass.myMethod.png");
    testResult.setParameters(array("one", "two"));
    assertThat(screenshotFileNameFrom(testResult)).isEqualTo("MyClass.myMethod.one.two.png");
  }
  
  public void shouldGenerateFileNamesEvenIfParameterValueIsNull() {
    testResult.getMethod().setMethodName("myMethod");
    testResult.getTestClass().setName("MyClass");
    testResult.setParameters(array("one", null));
    assertThat(screenshotFileNameFrom(testResult)).isEqualTo("MyClass.myMethod.one.[null].png");
  }
}
