package ru.test.homework;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Домашнее задание 4", "Домашнее задание  4", "Домашнее задание   4"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("ДЗ 6", "ДЗ  6", "ДЗ   6"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupPage();
  }
}
