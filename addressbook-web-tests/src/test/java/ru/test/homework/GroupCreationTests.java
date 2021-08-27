package ru.test.homework;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() throws Exception {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Домашнее задание 4", "Домашнее задание  4", "Домашнее задание   4"));
    app.submitGroupCreation();
    app.returnGroupPage();
    app.logout();
  }

}
