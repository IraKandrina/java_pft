package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test0").withHeader("test0").withFooter("test0"));
            app.goTo().goToHomePage();
        }
        if (app.db().contacts().size() == 0) {
            File photo = new File("src/test/resources/photo.jpg");
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
                    .withEmail3("ivanov_ivan@gmail.com")
                    .inGroup(groups.iterator().next());
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().goToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }


}
