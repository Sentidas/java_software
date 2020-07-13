package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("V", "Petrovich",
            "Volgograd, Mira, 5-98", "petrov@ya.ru", null, "test3");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}

