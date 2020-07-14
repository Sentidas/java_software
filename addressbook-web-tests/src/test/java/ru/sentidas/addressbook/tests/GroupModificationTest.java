package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTest  extends TestBase {
  @BeforeMethod
  public void ensurePreconitions() {
    app.goTo().GroupPage();
    if(app.group().all().size()==0){
      app.group().create(new GroupData().withName("test3"));
    }
  }
  @Test
  public void testGroupModification() {

    Set<GroupData> before=app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test3").withHeader("юля").withFooter("test4");

    app.group().modify(group);

    Set<GroupData> after =app.group().all();

    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);

    before.add(group);


    Assert.assertEquals(after, before);
  }




}
