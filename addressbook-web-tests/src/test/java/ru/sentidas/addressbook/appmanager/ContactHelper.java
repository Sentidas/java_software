package ru.sentidas.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sentidas.addressbook.model.ContactData;
import ru.sentidas.addressbook.model.Contacts;

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

  public int count() {
    return  wd.findElements(By.name("selected[]")).size();
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
      String  address = items.get(3).getText();
      String  allEmails = items.get(4).getText();
      String  allPhones = items.get(5).getText();

      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
             .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);
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

  public ContactData infoFromEditForm(ContactData contact) {
    selectContactById(contact.getId());
    String firsname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();

    return new ContactData().withId(contact.getId()).withFirstname(firsname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);

  }
}
