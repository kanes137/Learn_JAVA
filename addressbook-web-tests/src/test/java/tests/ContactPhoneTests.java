package tests;

import model.ContactData;
import model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

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
              .withFirstname("test").withMiddlename("test").withLastname("test")
              .withNickname("test").withGroup("test1").withHomePhone("+7(905) 015 - 06 - 41")
              .withMobilePhone("8 8412 34 26 89").withWorkPhone("76-22-12"), true);
    }
  }

  @Test
  public void testContactInformation() {
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().intoFromEditForm(contact);
  assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[-() .]", "");
  }
}
