package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("LOH").withMiddlename("Middlename").withLastname("Lastname").withNickname("nickname").withGroup("test1")
            .withHomePhone("+7(905) 015 - 06 - 41").withMobilePhone("8 8412 34 26 89").withWorkPhone("76-22-12")
            .withAddress("Пенза, военный городок, 137-39").withEmail("ya1@ya.ru").withEmail2("ya2@ya.ru").withEmail3("ya3@ya.ru");
    app.contact().create((contact),true);
    Contacts after = app.contact().all();
    assertEquals(after.size() - 1, before.size());
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}