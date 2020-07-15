package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePrecontions() {
    if(app.contact().all().size()==0){

      app.contact().create(new ContactData().withFirstname("Vasia").withLastname("Petrovich")
              .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru"), true);
    }
  }

  @Test
  public void testContactModification() throws Exception {

    Contacts before=app.contact().all();
    System.out.println("список до " + before);
    System.out.println("размер до " + before.size());

    //ContactData modifiedContact = initContactEdition(before.iterator().next());
    ContactData modifiedContact = app.contact().initContactEdition(before.iterator().next());

    ContactData contact = new ContactData().withFirstname("Mi").withLastname("Petrova").withAddress("Volgograd, Mira, 5-98")
    .withEmail("petrov@ya.ru").withMobile("89261547865");
    app.contact().fillContactForm(contact , false);
    app.contact().submitContactModification();
    app.contact().returnToHomePage();
    Contacts after =app.contact().all();
    System.out.println("список после " + after);
    System.out.println("размер после " + after.size());
    assertThat(after.size(), equalTo(before.size()));


    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

  }
}

