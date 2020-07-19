package ru.sentidas.addressbook.tests;

import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]>list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line!= null) {

           String[] split = line.split(";");
           list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
      }
    return  list.iterator();
  }

  @Test (dataProvider = "validGroups")
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
