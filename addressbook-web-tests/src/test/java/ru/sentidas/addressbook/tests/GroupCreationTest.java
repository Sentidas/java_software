package ru.sentidas.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTest extends TestBase {



  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();

      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();

      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

    }
  }

  @Test (dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {

    app.goTo().GroupPage();
    Groups before=app.group().all();

    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after =app.group().all();

    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());

    // проверка

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }


  @Test (enabled = false)
  public void testBadGroupCreation() throws Exception {

    app.goTo().GroupPage();
    Groups before=app.group().all();
    GroupData group = new GroupData().withName("test11'").withHeader("test12").withFooter("test13");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after =app.group().all();
    assertThat(after, equalTo(before));
  }

}
