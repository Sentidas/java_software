package ru.sentidas.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupCreationTest extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().GroupPage();
    Groups before=app.group().all();

    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());

    GroupData group = new GroupData().withName("test11").withHeader("test12").withFooter("test13");
    app.group().create(group);
    Groups after =app.group().all();

    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());

    // проверка
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
