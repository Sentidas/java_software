package ru.sentidas.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }

      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();

      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) throws Exception {
    Groups groups=app.db().groups();
    if (groups.size()==0) {
      GroupData newGroup= new GroupData().withName("test1").withHeader("test1").withFooter("test1");
      app.goTo().GroupPage();
      app.group().create(newGroup);
    } else {
      Contacts before = app.db().contacts();
      File photo = new File("src/test/resources/1.jpg");
      ContactData newContact = contact.inGroup(groups.iterator().next()).withFhoto(photo);

    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());
    app.contact().create(newContact, true);
    Contacts  after =app.db().contacts();
    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());


    // проверка
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListUI();

  }}
}

