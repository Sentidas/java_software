package ru.sentidas.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;
import ru.sentidas.addressbook.model.GroupData;

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

  public void fillContactForm(ContactData contactData , boolean creation) {

    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    //if (creation) {
   //   new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    //} else {
      //Assert.assertFalse(isElementPresent(By.name("new_group")));
    //}
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());

  }
  public void submitContactCreation() {
    click(By.name("submit"));
  }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
  public void returnToHome() {
    click(By.linkText("home"));
  }

  public void selectContactForUpdate() {
    click(By.name("selected[]"));
  }

  public ContactData initContactEdition(ContactData contact) {
    int id = contact.getId();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    return contact;
  }
  public void submitContactDeletion() {
    click(By.xpath("//input[@value='Delete']"));
  }


  public void submitContactModification() {
    click(By.name("update"));
  }

  public void submitAlertOfDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, b);
    submitContactCreation();
    goToHomePage();
  }

  public void modify(ContactData contact) {

    selectContactById(contact.getId());
    fillContactForm(contact , false);
    submitContactModification();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactForUpdate();
    submitContactDeletion();
    submitAlertOfDeletion();
    returnToHome();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> items = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(items.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = items.get(1).getText();
      String firstname = items.get(2).getText();

      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();

  }
}
