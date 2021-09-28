package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contacts count")
  private int count;

  @Parameter(names = "-f", description = "Target file")
  private String file;

  @Parameter(names = "-d", description = "Format file")
  private String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname(),
              contact.getNickname(), contact.getGroup(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
              contact.getAddress(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private static List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstname(String.format("LOH %s", i)).withMiddlename(String.format("Middlename %s", i)).withLastname(String.format("Lastname %s", i))
              .withNickname(String.format("nickname %s", i)).withGroup(String.format("test1 %s", i))
              .withHomePhone("+7(905) 015 - 06 - 41").withMobilePhone("8 8412 34 26 89").withWorkPhone("76-22-12")
              .withAddress("Пенза, военный городок, 137-39").withEmail("ya1@ya.ru").withEmail2("ya2@ya.ru").withEmail3("ya3@ya.ru"));
    }
    return contacts;
  }
}
