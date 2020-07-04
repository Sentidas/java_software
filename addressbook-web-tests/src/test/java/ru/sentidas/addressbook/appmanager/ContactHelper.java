package ru.sentidas.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.sentidas.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
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
}
