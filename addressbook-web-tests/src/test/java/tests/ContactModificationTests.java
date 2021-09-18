package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData()
              .withName("test1").withHeader("Модификация").withFooter("Модификация"));
    }
    app.goTo().home();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Модиф").withMiddlename("Модиф").withLastname("Модиф").withNickname("Модиф").withGroup("test1"), true);
    }
  }

  @Test
  public void testModificationContact() {
    Set<ContactData> before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withFirstname("Модиф1").withMiddlename("test").withLastname("Модиф").withNickname("Модиф").withGroup(null);
    app.contact().modify(contact);
    app.goTo().home();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
