package ru.sentidas.addressbook.tests;

import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test3", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

  }
}
