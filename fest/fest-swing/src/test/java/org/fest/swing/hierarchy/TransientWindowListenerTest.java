/*
 * Created on Nov 12, 2007
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
package org.fest.swing.hierarchy;

import static org.easymock.classextension.EasyMock.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.TestDialog;
import org.fest.swing.TestFrame;

import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import static java.awt.event.ComponentEvent.COMPONENT_SHOWN;
import java.awt.event.WindowEvent;
import static java.awt.event.WindowEvent.WINDOW_CLOSED;
import static java.awt.event.WindowEvent.WINDOW_OPENED;

/**
 * Tests for <code>{@link TransientWindowListener}</code>.
 *
 * @author Alex Ruiz
 */
public class TransientWindowListenerTest {

  private TransientWindowListener listener;
  private WindowFilter mockWindowFilter;
  private TestDialog eventSource;
  private TestFrame dialogParent;

  @BeforeMethod public void setUp() {
    mockWindowFilter = createMock(MockWindowFilter.class);
    listener = new TransientWindowListener(mockWindowFilter);
    dialogParent = new TestFrame(getClass());
    eventSource = new TestDialog(dialogParent);
  }

  @AfterMethod public void tearDown() {
    eventSource.destroy();
    dialogParent.destroy();
  }

  @Test public void shouldUnfilterOpenedWindowIfImplicitFiltered() {
    assertUnfilterWindowIfImplicitFiltered(openedWindowEvent());
  }

  @Test public void shouldUnfilterShownWindowIfImplicitFiltered() {
    assertUnfilterWindowIfImplicitFiltered(shownWindowEvent());
  }

  private void assertUnfilterWindowIfImplicitFiltered(final AWTEvent event) {
    new EasyMockTemplate(mockWindowFilter) {
      protected void expectations() {
        expect(mockWindowFilter.isImplicitFiltered(eventSource)).andReturn(true);
        mockWindowFilter.unfilter(eventSource);
        expectLastCall();
      }

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  @Test public void shouldFilterOpenedWindowIfParentIsFiltered() {
    assertFilterWindowIfParentIsFiltered(openedWindowEvent());
  }

  @Test public void shouldFilterShownWindowIfParentIsFiltered() {
    assertFilterWindowIfParentIsFiltered(shownWindowEvent());
  }

  private void assertFilterWindowIfParentIsFiltered(final AWTEvent event) {
    new EasyMockTemplate(mockWindowFilter) {
      protected void expectations() {
        expect(mockWindowFilter.isImplicitFiltered(eventSource)).andReturn(false);
        expect(mockWindowFilter.isFiltered(dialogParent)).andReturn(true);
        mockWindowFilter.filter(eventSource);
        expectLastCall();
      }

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  private WindowEvent openedWindowEvent() {
    return new WindowEvent(eventSource, WINDOW_OPENED);
  }

  private ComponentEvent shownWindowEvent() {
    return new ComponentEvent(eventSource, COMPONENT_SHOWN);
  }

  @Test public void shouldNotDoAnythingIfClosedWindowAlreadyFiltered() {
    new EasyMockTemplate(mockWindowFilter) {
      protected void expectations() {
        expect(mockWindowFilter.isFiltered(eventSource)).andReturn(true);
      }

      protected void codeToTest() {
        listener.eventDispatched(closedWindowEvent());
      }
    }.run();
  }

  @Test public void shouldFilterClosedWindowAfterEventIsProcessed() {
    new EasyMockTemplate(mockWindowFilter) {
      protected void expectations() {
        expect(mockWindowFilter.isFiltered(eventSource)).andReturn(false);
        mockWindowFilter.implicitFilter(eventSource);
        expectLastCall();
        waitTillClosedEventIsHandled();
        expect(mockWindowFilter.isImplicitFiltered(eventSource)).andReturn(true);
        mockWindowFilter.filter(eventSource);
        expectLastCall();
      }

      protected void codeToTest() {
        listener.eventDispatched(closedWindowEvent());
      }
    }.run();
  }

  @Test public void shouldNotFilterClosedWindowAfterEventIsProcessedIfWindowNotImplicitFiltered() {
    new EasyMockTemplate(mockWindowFilter) {
      protected void expectations() {
        expect(mockWindowFilter.isFiltered(eventSource)).andReturn(false);
        mockWindowFilter.implicitFilter(eventSource);
        expectLastCall();
        waitTillClosedEventIsHandled();
        expect(mockWindowFilter.isImplicitFiltered(eventSource)).andReturn(false);
      }

      protected void codeToTest() {
        listener.eventDispatched(closedWindowEvent());
      }
    }.run();
  }

  private void waitTillClosedEventIsHandled() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private WindowEvent closedWindowEvent() {
    return new WindowEvent(eventSource, WINDOW_CLOSED);
  }
}