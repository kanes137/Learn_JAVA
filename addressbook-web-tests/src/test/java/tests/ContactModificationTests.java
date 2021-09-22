package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData()
              .withName("test1").withHeader("Модификация").withFooter("Модификация"));
    }
    app.goTo().home();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("LOH").withMiddlename("Middlename").withLastname("Lastname").withNickname("nickname").withGroup("test1")
              .withHomePhone("+7(905) 015 - 06 - 41").withMobilePhone("8 8412 34 26 89").withWorkPhone("76-22-12")
              .withAddress("Пенза, военный городок, 137-39").withEmail("ya1@ya.ru").withEmail2("ya2@ya.ru").withEmail3("ya3@ya.ru"), true);
    }
  }

  @Test
  public void testModificationContact() {
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withFirstname("Модиф1").withMiddlename("test").withLastname("Модиф").withNickname("Модиф").withGroup(null);
    app.contact().modify(contact);
    app.goTo().home();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
  }
}
