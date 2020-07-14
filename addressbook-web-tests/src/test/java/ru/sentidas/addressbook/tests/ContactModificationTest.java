package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    if(!app.getContactHelper().isThereAContact()){

      app.getContactHelper().createContact(new ContactData("Vasia", "Petrovich",
              "Volgograd, Mira, 5-98", "petrov@ya.ru", null, null), true);
    }
    List<ContactData> before=app.getContactHelper().getContactList();
    System.out.println(before);

    app.getContactHelper().initContactEdition(before.size()-1);
    ContactData contact = new ContactData("Mi", "Petrova",
            "Volgograd, Mira, 5-98", "petrov@ya.ru", "89261547865", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after =app.getContactHelper().getContactList();
    System.out.println(before);
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}

