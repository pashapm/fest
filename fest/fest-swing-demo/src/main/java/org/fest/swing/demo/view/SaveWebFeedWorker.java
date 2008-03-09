/*
 * Created on Mar 8, 2008
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
package org.fest.swing.demo.view;

import java.awt.Window;

import org.fest.swing.demo.model.WebFeed;
import org.fest.swing.demo.service.Services;

/**
 * Understands saving a web feed in a database using worker thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class SaveWebFeedWorker extends SaveWorkerTemplate<WebFeed> {

  /**
   * Creates a new </code>{@link SaveWebFeedWorker}</code>.
   * @param target the web feed to save.
   * @param saveListener listener to be notified when saving the web feed to the database is complete. 
   * @param progressWindow the window showing progress made by this worker.
   */
  SaveWebFeedWorker(WebFeed target, SaveListener saveListener, Window progressWindow) {
    super(target, saveListener, progressWindow);
  }

  /** @see javax.swing.SwingWorker#doInBackground() */
  protected Void doInBackground() throws Exception {
    Services.instance().webFeedService().saveWebFeed(target);
    return null;
  }
}
