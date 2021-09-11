package tests;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "Модификация", "Модификация"));
    }
    ContactData contact = new ContactData("LOH", "Middlename", "Lastname", "nickname", "test1");
    app.getContactHelper().createContact((contact),true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size() - 1, before.size());

    before.add(contact);
    Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(after, before);
  }
}