package ru.test.homework;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Домашнее задание 4", "Домашнее задание  4", "Домашнее задание   4"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnGroupPage();
    app.getSessionHelper().logout();
  }

}
