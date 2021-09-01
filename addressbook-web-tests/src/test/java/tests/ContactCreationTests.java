package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "Модификация", "Модификация"));
    }
    app.getContactHelper().createContact(new ContactData("Firstname", "Middlename", "Lastname", "nickname", "test1"), true);
  }
}
