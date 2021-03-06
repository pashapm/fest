/*
 * Created on Apr 20, 2008
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

import java.util.*;

import org.fest.swing.demo.model.WebFeed;
import org.fest.swing.demo.model.WebFeedEntry;

import static java.util.Calendar.*;

import static org.fest.util.Arrays.array;

public final class WebFeedServiceStub implements WebFeedService {
  
  private final Map<Long, WebFeed> feedsById = new HashMap<Long, WebFeed>();
  private final Map<String, WebFeed> feedsByName = new HashMap<String, WebFeed>();
  
  public void saveWebFeed(WebFeed webFeed) {
    try {
      store(webFeed);
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void updateWebFeeds(WebFeed[] webFeeds) {
    for (WebFeed feed : webFeeds) store(feed);
  }
  
  private void store(WebFeed webFeed) {
    if (webFeed.id() == 0) webFeed.id(feedsById.size() + 1);
    feedsById.put(webFeed.id(), webFeed);
    feedsByName.put(webFeed.name(), webFeed);
  }

  public WebFeedEntry[] entriesOf(WebFeed webFeed) {
    return array(
        new WebFeedEntry("The weekly bag� April 18", "", date(18, APRIL, 2008)),
        new WebFeedEntry("0.8 is easyb�s bag", "", date(3, APRIL, 2008)),
        new WebFeedEntry("Abecedarian Groovy", "", date(31, MARCH, 2008)),
        new WebFeedEntry("The weekly bag� March 21", "", date(24, MARCH, 2008)),
        new WebFeedEntry("Unambiguously analyzing metrics", "", date(20, MARCH, 2008))
        );
  }
  
  private Date date(int day, int month, int year) {
    Calendar calendar = new GregorianCalendar();
    calendar.set(DATE, day);
    calendar.set(MONTH, month);
    calendar.set(YEAR, year);
    return calendar.getTime();
  }

  public WebFeed webFeedWithName(String name) {
    return feedsByName.get(name);
  }
}