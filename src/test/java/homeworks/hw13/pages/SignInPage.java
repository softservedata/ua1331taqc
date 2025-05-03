package homeworks.hw13.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String welcomeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-sign-in h1"))).getText();
    }

    public String signInDetailsText() {
        return driver.findElement(By.cssSelector("app-sign-in h2")).getText();
    }

    public String emailLabel() {
        return driver.findElement(By.cssSelector("label:nth-child(1)")).getText();
    }

    public void signInInput(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void submit() {
        driver.findElement(By.cssSelector(".greenStyle")).click();
    }


    public String errorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-general-error"))).getText();
    }

    public String errorPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#pass-err-msg"))).getText();
    }


    public String errorEmail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email-err-msg"))).getText();
    }
}

