package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testModificationContact() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "Создание", "Создание"));
    }
    app.getNavigationHelper().goHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Модиф", "Модиф", "Модиф", "Модиф", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Модиф", "test", "Модиф", "Модиф", null), false);
    app.getContactHelper().submitContactModification();
  }
}
