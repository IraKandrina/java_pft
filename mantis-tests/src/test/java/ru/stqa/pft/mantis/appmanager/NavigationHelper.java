package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    }

    public void goToPage() {
        click(By.xpath("//a[@href='/mantisbt-1.3.20/manage_overview_page.php']"));
        click(By.xpath("//a[@href='/mantisbt-1.3.20/manage_user_page.php']"));

    }

    public void manageUsersLink() {
        click(By.linkText("Управление пользователями"));
    }
}