package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();
    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());
    ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
    .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").withGroup("test3");
    app.contact().create(contact , true );
    Contacts  after = app.contact().all();
    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());


    // проверка
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}

