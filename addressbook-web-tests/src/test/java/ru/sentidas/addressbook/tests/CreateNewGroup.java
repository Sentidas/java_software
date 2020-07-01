package ru.sentidas.addressbook.tests;

import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;


public class CreateNewGroup extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

  }

}
