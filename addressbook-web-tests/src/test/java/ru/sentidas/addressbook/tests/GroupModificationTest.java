package ru.sentidas.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;

public class GroupModificationTest  extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    int before =app.getGroupHelper().getGroupCount();
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    }
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test3", "юля", null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after =app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }


}
