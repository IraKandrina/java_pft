package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        if (!app.getContactHelper().IsThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg, Palace Square, 1", "+7(111)111-11-11", "ivanov_ivan@mail.ru", "test1"), true);
            app.getContactHelper().submitContactCreation();
            app.getNavigationHelper().goToHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Petr", "Petrov", "St.Petersburg, Palace Square, 2", "+7(222)222-22-22", "petr_petrov@mail.ru", null);
        app.getContactHelper().fillContactForm(contact, false);

        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
