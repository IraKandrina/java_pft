package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
        wd.get("http://localhost/addressbook/group.php");
    }

    public void goToContactPage() {
        wd.findElement(By.linkText("add new")).click();
        wd.get("http://localhost/addressbook/edit.php");
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
        wd.get("http://localhost/addressbook/");
    }

}
