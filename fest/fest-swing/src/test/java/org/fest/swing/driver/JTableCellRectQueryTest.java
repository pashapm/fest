/*
 * Created on Aug 10, 2008
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
package org.fest.swing.driver;

import java.awt.Rectangle;

import javax.swing.JTable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.assertions.Assertions;
import org.fest.mocks.EasyMockTemplate;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import static org.fest.swing.testing.TestGroups.EDT_QUERY;

/**
 * Tests for <code>{@link JTableCellRectQuery}</code>.
 *
 * @author Alex Ruiz
 */
@Test(groups = EDT_QUERY)
public class JTableCellRectQueryTest {

  private JTable table;
  private int row;
  private int column;
  private Rectangle rectangle;

  @BeforeMethod public void setUp() {
    table = createMock(JTable.class);
    row = 8;
    column = 6;
    rectangle = new Rectangle(80, 60);
  }
  
  public void shouldReturnCellRectInJTable() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.getCellRect(row, column, false)).andReturn(rectangle);
      }

      protected void codeToTest() {
        Assertions.assertThat(JTableCellRectQuery.cellBoundsOf(table, row, column)).isSameAs(rectangle);
      }
    }.run();
  }
}
