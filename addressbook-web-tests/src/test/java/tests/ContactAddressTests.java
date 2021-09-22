package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

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
  public void testContactInformation() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().intoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
