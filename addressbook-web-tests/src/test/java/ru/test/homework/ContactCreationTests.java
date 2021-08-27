package ru.test.homework;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Goose", "Alekseevich", "Mr", "kanes173"));
    app.getContactHelper().saveContact();
    app.getNavigationHelper().goToHomePage();
    app.getSessionHelper().logout();
  }
}
