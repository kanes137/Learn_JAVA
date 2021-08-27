package ru.test.homework;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletionTests() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.getNavigationHelper().goToGroupPage();
  }
}
