package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePrecontions() {
    if(app.contact().all().size()==0){
      ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
              .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").withGroup("test3");
      app.contact().create(contact, true);
    }
  }
  @Test
  public void testContactModification() throws Exception {

    Contacts before=app.contact().all();
    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());

    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Mi").withLastname("Petrova").withAddress("Volgograd, Mira, 5-98")
    .withEmail("petrov@ya.ru").withMobilePhone("89261547865");


    app.contact().modify(contact);
    System.out.println(app.contact().count());
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after =app.contact().all();

    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());


    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}

