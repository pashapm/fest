/*
 * Created on May 6, 2007
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
package org.fest.swing.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Files.temporaryFolderPath;
import static org.fest.util.Strings.concat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link ScreenshotTaker}</code>.
 *
 * @author Alex Ruiz
 */
public class ScreenshotTakerTest {

  private ScreenshotTaker taker;

  @BeforeMethod public void setUp() {
    taker = new ScreenshotTaker();
  }

  @Test(expectedExceptions = ImageException.class)
  public void shouldThrowErrorIfFilePathIsNull() {
    taker.saveDesktopAsPng(null);
  }
  
  @Test(expectedExceptions = ImageException.class)
  public void shouldThrowErrorIfFilePathIsEmpty() {
    taker.saveDesktopAsPng("");
  }
  
  @Test(expectedExceptions = ImageException.class)
  public void shouldThrowErrorIfFilePathNotEndingWithPng() {
    taker.saveDesktopAsPng("somePathWithoutPng");
  }
  
  @Test public void shouldTakeScreenshotAndSaveItInGivenPath() throws Exception {
    String imagePath = concat(temporaryFolderPath(), imageFileName());
    taker.saveDesktopAsPng(imagePath);
    ensureFileNotExisting(imagePath);
  }
  
  private String imageFileName() {
    String timestamp = new SimpleDateFormat("yyMMdd.hhmmss").format(new GregorianCalendar().getTime());
    return concat(timestamp, ".png");
  }
  
  private void ensureFileNotExisting(String path) throws IOException {
    File imageFile = new File(path);
    assertThat(imageFile.isFile()).isTrue();
    assertThat(imageFile.getTotalSpace() > 0).isTrue();
    BufferedImage image = ImageIO.read(imageFile);
    Dimension expectedImageSize = Toolkit.getDefaultToolkit().getScreenSize();
    assertThat(image.getWidth()).isEqualTo(expectedImageSize.width);
    assertThat(image.getHeight()).isEqualTo(expectedImageSize.height);
  }
}