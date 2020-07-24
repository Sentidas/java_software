package ru.sentidas.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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

      Contacts before=app.contact().all();
      app.contact().deleteAll();
      Contacts after =app.contact().all();
     // assertThat(after.size()==0));
    }
  }



