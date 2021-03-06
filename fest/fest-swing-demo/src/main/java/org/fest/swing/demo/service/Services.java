/*
 * Created on Mar 7, 2008
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
package org.fest.swing.demo.service;

/**
 * Understands a locator of services.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Services {

  private FolderService folderService;
  private WebFeedService webFeedService;

  public FolderService folderService() {
    return folderService;
  }

  public void updateFolderService(FolderService folderService) {
    this.folderService = folderService;
  }

  public WebFeedService webFeedService() {
    return webFeedService;
  }

  public void updateWebFeedService(WebFeedService webFeedService) {
    this.webFeedService = webFeedService;
  }
  
  public static Services instance() {
    return SingletonHolder.INSTANCE;
  }
  
  private static class SingletonHolder {
    static final Services INSTANCE = new Services();
  }
  
  private Services() {}
}
