package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg, Palace Square, 1", "+7(111)111-11-11", "ivanov_ivan@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }

}
