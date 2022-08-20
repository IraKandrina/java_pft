package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0) {
            ContactData contact = new ContactData()
                    .withFirstName("Ivan")
                    .withLastName("Ivanov")
                    .withAddress("St.Petersburg, Palace Square, 1")
                    .withHomePhone("+7 495 312-15-77")
                    .withMobilePhone("+7(111)111-11-11")
                    .withWorkPhone("123-45-67")
                    .withEmail("ivanov_ivan@mail.ru")
                    .withEmail2("ivanov_ivan@list.ru")
                    .withEmail3("ivanov_ivan@gmail.com")
                    .withGroup("test1");
            app.contact().create(contact);
        }
    }

    @Test
    public void testContactAddress(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
