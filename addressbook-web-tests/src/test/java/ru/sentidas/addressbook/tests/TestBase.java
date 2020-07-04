package ru.sentidas.addressbook.tests;

import com.applitools.eyes.selenium.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.sentidas.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected  ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() throws Exception {
    app.stop();
  }

}
