package ru.test.homework;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Goose", "Alekseevich", "Mr", "kanes173"));
    app.saveContact();
    app.goToHomePage();
    app.logout();
  }
}
