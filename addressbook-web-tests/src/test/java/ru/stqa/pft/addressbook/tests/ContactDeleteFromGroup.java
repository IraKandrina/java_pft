package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactDeleteFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/photo.jpg");
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test0").withHeader("test0").withFooter("test0"));
            app.goTo().goToHomePage();
        }

        if (app.db().contacts().size() == 0) {
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
    public void deleteContactFromGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        int id = contact.getId();

        if(contact.getGroups().size() == 0){
            GroupData newGroup = app.db().groups().iterator().next();
            app.contact().addToGroup(contact, newGroup);
            app.goTo().goToHomePage();
        }

        ContactData newContact = app.db().getContactById(id);
        Groups deletedGroup = newContact.getGroups();
        app.contact().removeFromGroup(newContact);
        assertThat(app.db().getContactById(contact.getId()).getGroups().contains(deletedGroup), equalTo(false));
    }

}
