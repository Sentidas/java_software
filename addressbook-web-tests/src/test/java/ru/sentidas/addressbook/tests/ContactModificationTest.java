package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePrecontions() {
    Groups groups=app.db().groups();
    if(app.db().contacts().size()==0){
      ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
              .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
    }
  }
  @Test
  public void testContactModification() throws Exception {
    Groups groups=app.db().groups();
    Contacts before=app.db().contacts();
    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());

    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Mi").withLastname("Petrova").withAddress("Volgograd, Mira, 5-98")
    .withEmail("petrov@ya.ru").withEmail2("petrov@gm.com").withEmail3("petrov@mail.ru").withMobilePhone("89261547865").withHomePhone("84956778900")
            .withWorkPhone("84998776543");


    app.contact().modify(contact);
    System.out.println(app.contact().count());
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after =app.db().contacts();

    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());


    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListUI();
  }


}

