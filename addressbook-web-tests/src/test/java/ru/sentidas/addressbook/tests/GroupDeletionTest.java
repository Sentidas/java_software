package ru.sentidas.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.GroupsHelper;
import ru.sentidas.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;


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


    Set<GroupData> before=app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after =app.group().all();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }


}

