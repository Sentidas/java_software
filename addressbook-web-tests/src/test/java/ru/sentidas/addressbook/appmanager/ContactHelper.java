package ru.sentidas.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sentidas.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private ApplicationManager app;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }
  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());

    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("address"),contactData.getAddress());
    type(By.name("email"),contactData.getEmail());
    type(By.name("mobile"),contactData.getMobile());
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
  public void selectContactForUpdate() {
    click(By.name("selected[]"));
  }
  public void initContactEdition() {
    click(By.xpath("//img[@alt='Edit']"));
  }
  public void submitContactDelition() {
    click(By.xpath("//input[@value='Delete']"));
  }


  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void submitAlertOfDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contactData, boolean b) {
    initContactCreation();
    fillContactForm(contactData,b);
    submitContactCreation();
    goToHomePage();

  }
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> items = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(items.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = items.get(1).getText();
      String firstname = items.get(2).getText();

      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
