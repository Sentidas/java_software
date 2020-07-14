package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.GroupData;

public class ContactsDeletionTest extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Vasia", "Petrovich",
              "Volgograd, Mira, 5-98", "petrov@ya.ru", null, "test3"), true);
    }
   app.getContactHelper().selectContactForUpdate();
   app.getContactHelper().submitContactDelition();
   app.getContactHelper().submitAlertOfDeletion();
  }
}

