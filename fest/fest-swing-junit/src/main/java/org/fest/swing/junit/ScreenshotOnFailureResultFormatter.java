/*
 * Created on Jun 4, 2007
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
package org.fest.swing.junit;

import java.lang.reflect.Method;

import junit.framework.Test;

import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.w3c.dom.Element;

import static java.io.File.separator;
import static org.apache.tools.ant.taskdefs.optional.junit.JUnitVersionHelper2.getTestCaseClassName;
import static org.apache.tools.ant.taskdefs.optional.junit.JUnitVersionHelper2.getTestCaseName;
import static org.apache.tools.ant.taskdefs.optional.junit.XMLConstants.ERROR;

import static org.fest.swing.util.ScreenshotTaker.PNG_EXTENSION;

import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isEmpty;
import static org.fest.util.Strings.join;
import static org.fest.util.Strings.quote;

import org.fest.swing.util.GUITests;
import org.fest.swing.util.ImageException;
import org.fest.swing.util.ScreenshotTaker;

/**
 * Understands a JUnit XML report formatter that takes a screenshot when a GUI test fails.
 * <p>
 * <strong>Note:</strong> A test is consider a GUI test if it is marked with the annotation
 * <code>{@link org.fest.swing.GUITest}</code>.
 * </p>
 * 
 * @author Alex Ruiz
 */
public final class ScreenshotOnFailureResultFormatter extends XmlJUnitResultFormatter {

  private static final String SCREENSHOT_FOLDER_PROPERTY_NAME = "fest.screenshot.dir";
  
  private static final String FEST_ELEMENT = "fest";
  private static final String SCREENSHOT_ELEMENT = "screenshot";
  private static final String SCREENSHOT_FILE_ATTRIBUTE = "file";
  
  private ScreenshotTaker screenshotTaker;
  private boolean ready;
  private String output;

  private ImageException couldNotCreateScreenshotTaker;
  
  public ScreenshotOnFailureResultFormatter() {
    try {
      screenshotTaker = new ScreenshotTaker();
      ready = screenshotTaker != null;
    } catch (ImageException e) {
      couldNotCreateScreenshotTaker = e;
    }
  }

  @Override protected void onStartTestSuite(JUnitTest suite) {
    if (couldNotCreateScreenshotTaker != null) {
      writeCouldNotCreateScreenshotTakerError();
      couldNotCreateScreenshotTaker = null;
      return;
    }
    output = screenshotOutputFrom(suite);
    logScreenshotFolder();
  }

  private void writeCouldNotCreateScreenshotTakerError() {
    Element errorElement = document().createElement(ERROR);
    writeErrorAndStackTrace(couldNotCreateScreenshotTaker, errorElement);
    rootElement().appendChild(errorElement);
  }

  private String screenshotOutputFrom(JUnitTest suite) {
    return suite.getProperties().getProperty(SCREENSHOT_FOLDER_PROPERTY_NAME);
  }

  private void logScreenshotFolder() {
    if (isEmpty(output)) {
      logEmptyScreenshotFolderName();
      return;
    } 
    logScreenshotFolderNameFound();
  }
  
  private void logEmptyScreenshotFolderName() {
    Element logElement = document().createElement(FEST_ELEMENT);
    writeText(concat("Unable to find property '", SCREENSHOT_FOLDER_PROPERTY_NAME,
        ",' which indicates where to store desktop screenshots"), logElement);    
    rootElement().appendChild(logElement);
  }
  
  private void logScreenshotFolderNameFound() {
    Element logElement = document().createElement(FEST_ELEMENT);
    writeText(concat("FEST output is ", quote(output)), logElement);
    rootElement().appendChild(logElement);
  }

  @Override protected void onFailureOrError(Test test, Throwable error, Element errorElement) {
    if (!ready) return;
    String className = getTestCaseClassName(test);
    String methodName = getTestCaseName(test);
    if (!isGUITest(className, methodName)) return;
    String screenshotFileName = takeScreenshotAndReturnFileName(className, methodName);
    if (isEmpty(screenshotFileName)) return;
    writeScreenshotFileName(screenshotFileName, errorElement);
  }

  private boolean isGUITest(String className, String methodName) {
    try {
      Class<?> testClass = Class.forName(className);
      Method testMethod = testClass.getDeclaredMethod(methodName, new Class<?>[0]);
      return GUITests.isGUITest(testClass, testMethod);
    } catch (Exception e) {
      return false;
    }
  }

  private String takeScreenshotAndReturnFileName(String className, String methodName) {
    String imageName = join(className,methodName, PNG_EXTENSION).with(".");
    String imagePath = concat(output, separator, imageName);
    try {
      screenshotTaker.saveDesktopAsPng(imagePath);
    } catch (ImageException e) {
      return null;
    }
    return imageName;
  }
  
  private void writeScreenshotFileName(String screenshotFileName, Element errorElement) {
    Element screenshotElement = document().createElement(SCREENSHOT_ELEMENT);
    screenshotElement.setAttribute(SCREENSHOT_FILE_ATTRIBUTE, screenshotFileName);
    errorElement.appendChild(screenshotElement);
  }
}
