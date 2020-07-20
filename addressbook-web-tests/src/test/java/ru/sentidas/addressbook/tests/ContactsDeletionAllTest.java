package ru.sentidas.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;


  public class ContactsDeletionAllTest extends TestBase{

    @BeforeMethod
    public void ensurePrecontions() {
      if(app.contact().all().size()==0){
        ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
                .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").withGroup("test3");
        app.contact().create(contact, false);
      }
    }

    @Test (enabled = true)
    public void testContactDeletionAll() throws Exception {
      app.contact().deleteAll();
    }
  }



