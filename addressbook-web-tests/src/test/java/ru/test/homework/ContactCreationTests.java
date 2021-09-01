package ru.test.homework;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Firstname", "Middlename", "Lastname", "nickname", "test1"), true);
    app.getNavigationHelper().goToHomePage();
  }
}
