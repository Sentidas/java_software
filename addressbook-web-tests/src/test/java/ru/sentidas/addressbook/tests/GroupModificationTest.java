package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;

public class GroupModificationTest  extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test3", "юля", null));
    app.getGroupHelper().submitGroupModification();
  }


}
