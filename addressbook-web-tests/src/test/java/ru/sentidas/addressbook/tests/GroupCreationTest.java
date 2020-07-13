package ru.sentidas.addressbook.tests;

import org.aspectj.lang.annotation.Before;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.internal.GroupsHelper;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test11", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after =app.getGroupHelper().getGroupList();
    System.out.println(after);
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}
