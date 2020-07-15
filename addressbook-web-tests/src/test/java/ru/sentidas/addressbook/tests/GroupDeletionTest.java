package ru.sentidas.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.GroupsHelper;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;


public class GroupDeletionTest extends TestBase {


  @BeforeMethod
  public void ensurePreconitions() {
    app.goTo().GroupPage();
    if(app.group().all().size()==0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    Groups before=app.group().all();
    System.out.println("список до " + before);
    System.out.println("размер до " + before.size());
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after =app.group().all();
    System.out.println("список после " + after);
    System.out.println("размер после " + after.size());
    assertThat(after.size(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.withOut(deletedGroup)));
  }
}

