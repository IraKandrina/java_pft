package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {

        if (!app.getContactHelper().IsThereAContact()) {
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg, Palace Square, 1", "+7(111)111-11-11", "ivanov_ivan@mail.ru", "test1"), true);
            app.getContactHelper().submitContactCreation();
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
