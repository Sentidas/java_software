package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    int before =app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before +1);
  }

}
