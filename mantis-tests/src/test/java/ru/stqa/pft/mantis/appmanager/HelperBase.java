package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By Locator) {
        wd.findElement(Locator).click();
    }

    protected void type(By Locator, String text) {
        click(Locator);
        if (text != null) {
            String existingText = wd.findElement(Locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(Locator).clear();
                wd.findElement(Locator).sendKeys(text);
                wd.findElement(Locator).click();
                wd.findElement(Locator).clear();
                wd.findElement(Locator).sendKeys(text);
            }
        }
    }
    protected void attach(By Locator, File file) {

        if (file != null) {
            String existingText = wd.findElement(Locator).getAttribute("value");
            if (!file.equals(existingText)) {
                wd.findElement(Locator).sendKeys(file.getAbsolutePath());
            }
        }
    }
    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}