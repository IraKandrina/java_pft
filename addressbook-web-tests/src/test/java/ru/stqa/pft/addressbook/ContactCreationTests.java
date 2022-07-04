package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ContactCreationTests {
  private WebDriver wd;
  @BeforeMethod
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() {
    goToContactPage();
    fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg, Palace Square, 1", "+7(111)111-11-11", "ivanov_ivan@mail.ru"));
    submitContactCreation();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
    wd.get("http://localhost/addressbook/");
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(contactData.getTelephone());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void goToContactPage() {
    wd.findElement(By.linkText("add new")).click();
    wd.get("http://localhost/addressbook/edit.php");
  }

  @AfterMethod
  public void tearDown() {
    logout();
    wd.quit();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
