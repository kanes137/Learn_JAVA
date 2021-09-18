package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData()
              .withName("test1").withHeader("Модификация").withFooter("Модификация"));
    }
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().home();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("LOH").withMiddlename("Middlename").withLastname("Lastname").withNickname("nickname").withGroup("test1");
    app.contact().create((contact),true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size() - 1, before.size());

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}