package com.softserve.edu.pr_task13_14;

import com.softserve.edu.pr_task12.SignInPageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void switchToEn() {
        WebElement langOption = find(SignInPageLocators.LANG_OPTION);
        langOption.click();
        if (!langOption.getText().trim().equalsIgnoreCase("En")) {
            find(SignInPageLocators.EN_LANG_OPTION).click();
        }
    }
}
