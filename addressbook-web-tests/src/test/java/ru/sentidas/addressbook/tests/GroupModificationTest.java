package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest  extends TestBase {
  @BeforeMethod
  public void ensurePreconitions() {
    app.goTo().GroupPage();
    if(app.group().list().size()==0){
      app.group().create(new GroupData().withName("test3"));
    }
  }
  @Test
  public void testGroupModification() {

    List<GroupData> before=app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId()).withName("test3").withHeader("юля").withFooter("test4");

    app.group().modify(index, group);

    List<GroupData> after =app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    System.out.println(before);
    before.add(group);
    System.out.println(before);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    System.out.println(after);
    Assert.assertEquals(after, before);
  }




}
