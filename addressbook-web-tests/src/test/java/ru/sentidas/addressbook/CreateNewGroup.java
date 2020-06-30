package ru.sentidas.addressbook;

import org.testng.annotations.*;


public class CreateNewGroup extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();

  }

}
