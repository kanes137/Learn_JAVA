package ru.test.homework;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Домашнее задание 4", "Домашнее задание  4", "Домашнее задание   4"));
    submitGroupCreation();
    returnGroupPage();
    logout();
  }

}
