package ru.sentidas.addressbook.tests;

import org.testng.annotations.*;
import ru.sentidas.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData(null, "Petrovna",
            "Volgograd, Mira, 5-98", "petrov@ya.ru", null));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }
}

