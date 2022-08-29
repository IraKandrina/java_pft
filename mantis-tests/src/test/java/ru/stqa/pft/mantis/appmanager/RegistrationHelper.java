package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String userName, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"),userName);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти'"));
    }

    public void finishChangingPassword(String realname, String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name ("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись'"));
    }
}
