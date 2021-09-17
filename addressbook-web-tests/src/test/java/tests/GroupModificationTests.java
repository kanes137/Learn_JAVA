package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData("test1", "Удаление", "Удаление"));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = app.group().list();
    app.group().select(before.size() - 1);
    app.group().initGroupModification();
    GroupData group = new GroupData("test1", "ДЗ  6", "ДЗ   6");
    app.group().fillGroupForm(group);
    app.group().submitGroupModification();
    app.group().returnGroupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(after, before);
  }
}
