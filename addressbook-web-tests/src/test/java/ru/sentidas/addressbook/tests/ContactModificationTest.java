package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    if(!app.getContactHelper().isThereAContact()){

      app.getContactHelper().createContact(new ContactData("Vasia", "Petrovich",
              "Volgograd, Mira, 5-98", "petrov@ya.ru", null, "test3"), true);
    }
    app.getContactHelper().initContactEdition();
    app.getContactHelper().fillContactForm(new ContactData("Misha", "Petrova",
            "Volgograd, Mira, 5-98", "petrov@ya.ru", "89261547865", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}

