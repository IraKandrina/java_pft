package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().IsThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg, Palace Square, 1", "+7(111)111-11-11", "ivanov_ivan@mail.ru", "test1"),true);
            app.getContactHelper().submitContactCreation();
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Petr", "Petrov", "St.Petersburg, Palace Square, 2", "+7(222)222-22-22", "petr_petrov@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
    }
}
