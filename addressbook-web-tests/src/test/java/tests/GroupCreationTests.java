package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().goToGroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData()
            .withName("test1").withHeader("Создание").withFooter("Создание");
    app.group().createGroup(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(after,before);
  }
}
