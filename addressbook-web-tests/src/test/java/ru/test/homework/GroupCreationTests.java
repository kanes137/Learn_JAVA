package ru.test.homework;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Домашнее задание 4", "Домашнее задание  4", "Домашнее задание   4"));
    app.getSessionHelper().logout();
  }
}
