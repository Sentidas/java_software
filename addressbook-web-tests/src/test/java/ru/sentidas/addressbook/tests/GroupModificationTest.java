package ru.sentidas.addressbook.tests;

import org.testng.annotations.Test;
import ru.sentidas.addressbook.model.GroupData;

public class GroupModificationTest  extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test4", null, null));
    app.getGroupHelper().submitGroupModification();
  }


}
