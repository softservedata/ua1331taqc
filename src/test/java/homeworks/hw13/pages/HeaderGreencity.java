package homeworks.hw13.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderGreencity {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HeaderGreencity(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement jsFindElement(String selector) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector(arguments[0])", selector);
    }

    public void clickSignIn() {
        jsFindElement(".header_sign-in-link").click();
    }

    public void switchLanguage() {
        jsFindElement(".lang-option").click();
        wait.until(ExpectedConditions.elementToBeClickable(jsFindElement(".add-shadow > li:nth-child(2) > span:nth-child(1)"))).click();
    }

}
