package ru.sentidas.addressbook.tests;

import org.aspectj.lang.annotation.Before;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.internal.GroupsHelper;
import ru.sentidas.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test3", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData> after =app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);


    int max = 0;
    for (GroupData g : after) {
     max=g.getId();
   }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<>(after));
  }

}
