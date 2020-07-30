package ru.sentidas.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactsDeletionAllTest extends TestBase{

    @BeforeMethod
    public void ensurePrecontions() {
      Groups groups=app.db().groups();
      if(app.contact().all().size()==0){
        ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
                .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").inGroup(groups.iterator().next());;
        app.contact().create(contact, true);
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



