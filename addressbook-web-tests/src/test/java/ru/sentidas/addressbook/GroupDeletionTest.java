package ru.sentidas.addressbook;


import org.testng.annotations.Test;


public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    goToGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();

  }

}

