package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContactEmailTest extends TestBase  {


    @Test

    public void testContactEmails()  {
      app.contact().goToHomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
      return  Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactEmailTest::cleaned)
              .collect (Collectors.joining("\n"));
    }

  public static String cleaned(String email)  {
    return email.replaceAll("\\s", "");
 }

}





