package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
   app.getContactHelper().selectContactForUpdate();
   app.getContactHelper().submitContactDelition();
   app.getContactHelper().submitAlertOfDeletion();
  }
}

