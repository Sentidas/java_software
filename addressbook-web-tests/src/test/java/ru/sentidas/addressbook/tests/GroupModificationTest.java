package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;
import ru.sentidas.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class GroupModificationTest  extends TestBase {
  @BeforeMethod
  public void ensurePrecontions() {

    if(app.db().groups().size()==0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test3"));
    }
  }
  @Test
  public void testGroupModification() {

    Groups before=app.db().groups();
    //System.out.println("список до " + before);
    //System.out.println("размер до " + before.size());
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test3").withHeader("юля").withFooter("test4");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));

    Groups after =app.db().groups();
    //System.out.println("список после " + after);
    //System.out.println("размер после " + after.size());

    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListUI();
  }

}
