package ru.sentidas.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePrecontions() {
    if(app.contact().all().size()==0){

      app.contact().create(new ContactData().withFirstname("Vasia").withLastname("Petrovich")
              .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru"), true);
    }
  }

  @Test
  public void testGroupCreation() throws Exception {

    Contacts before=app.contact().all();
    System.out.println("список до " + before);
    System.out.println("размер до " + before.size());

    ContactData deletedContact = before.iterator().next();

   app.contact().selectContactForUpdate();
   app.contact().submitContactDelition();
   app.contact().submitAlertOfDeletion();

    Contacts after=app.contact().all();
    System.out.println("список после " + after);
    System.out.println("размер после " + after.size());
    assertThat(after.size(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}

