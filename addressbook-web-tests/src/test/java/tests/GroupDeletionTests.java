package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData("test1", "Удаление", "Удаление"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    app.group().select(before.size() - 1);
    app.group().delete();
    app.goTo().groupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }
}
