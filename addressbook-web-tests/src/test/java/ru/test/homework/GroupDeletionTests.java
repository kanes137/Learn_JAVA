package ru.test.homework;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletionTests() throws Exception {
    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    goToGroupPage();
  }
}
