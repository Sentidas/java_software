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
    if(app.db().contacts().size()==0){
      Groups groups=app.db().groups();
      ContactData contact = new ContactData().withFirstname("V").withLastname("Petrovich")
              .withAddress("Volgograd, Mira, 5-98").withEmail("petrov@ya.ru").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    Contacts before=app.db().contacts();
    System.out.println("список до " + before);
    System.out.println("размер до " + before.size());

    ContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);


    Contacts after =app.db().contacts();

    System.out.println("список после " + after);
    System.out.println("размер после " + after.size());
    assertThat(app.contact().count(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.withOut(deletedContact)));
    verifyContactListUI();
  }
}

