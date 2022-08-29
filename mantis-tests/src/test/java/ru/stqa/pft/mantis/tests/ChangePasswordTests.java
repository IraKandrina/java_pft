package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer (){
        app.mail().start();
    }

    @Test
    public void changePassword() throws MessagingException, IOException {
        Users users = app.db().usersNoAdmin();
        UserData user = users.iterator().next();

        String userName = user.getUserName();
        String email = user.getEmail();
        int id = user.getId();
        String newPassword = "updatedPassword";

        app.registration().login((String) app.getProperty("web.adminLogin"), (String) app.getProperty("web.adminPassword"));
        app.goTo().goToPage();
        app.user().selectUser(id);
        app.user().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        long now = System.currentTimeMillis();
        String realName = String.format("realname%s", now);
        app.registration().finishChangingPassword(realName, confirmationLink, newPassword);
        assertTrue(app.newSession().login(userName, newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
