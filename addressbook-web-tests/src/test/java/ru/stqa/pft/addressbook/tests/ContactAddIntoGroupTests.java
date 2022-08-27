package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddIntoGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        File photo = new File("src/test/resources/photo.jpg");
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test0"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().goToHomePage();
            Groups groups = app.db().groups();
            ContactData contact = new ContactData()
                    .withFirstName("Ivan")
                    .withLastName("Ivanov")
                    .withPhoto(photo)
                    .withAddress("St.Petersburg, Palace Square, 1")
                    .withHomePhone("+7 495 312-15-77")
                    .withMobilePhone("+7(111)111-11-11")
                    .withWorkPhone("123-45-67")
                    .withEmail("ivanov_ivan@mail.ru")
                    .withEmail2("ivanov_ivan@list.ru")
                    .withEmail3("ivanov_ivan@gmail.com");
            app.contact().create(contact);
        }

        app.contact().showContactsWithoutGroup();
        Set<ContactData> contactsWithoutGroup = app.contact().all();
        if (contactsWithoutGroup.size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Ivan")
                    .withLastName("Ivanov")
                    .withPhoto(photo)
                    .withAddress("St.Petersburg, Palace Square, 1")
                    .withHomePhone("+7 495 312-15-77")
                    .withMobilePhone("+7(111)111-11-11")
                    .withWorkPhone("123-45-67")
                    .withEmail("ivanov_ivan@mail.ru")
                    .withEmail2("ivanov_ivan@list.ru")
                    .withEmail3("ivanov_ivan@gmail.com");
            app.contact().create(contact);
        }
    }

    @Test
    public void addContactToGroup(){
        app.contact().showContactsWithoutGroup();
        Set<ContactData> contactsWithoutGroup = app.contact().all();

        ContactData contactWithoutGroup = contactsWithoutGroup.iterator().next();
        int id = contactWithoutGroup.getId();

        ContactData contact = app.db().getContactById(id);
        GroupData group = app.db().groups().iterator().next();
        app.contact().addToGroup(contact, group);
        assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(true));
        verifyContactListInUI();
    }

}
