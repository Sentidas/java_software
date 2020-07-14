package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().GroupPage();
    Set<GroupData> before=app.group().all();
    GroupData group = new GroupData().withName("test11");
    app.group().create(group);
    Set<GroupData> after =app.group().all();
    System.out.println(after);
    Assert.assertEquals(after.size(), before.size()+1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(after, before);
  }

}
