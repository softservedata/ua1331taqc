package homeworks.hw13.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getUserName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name"))).getText();
    }

    public void logOut() {
        driver.findElement(By.cssSelector("a.header_user-name")).click();
        driver.findElement(By.cssSelector("li.drop-down-item:nth-child(3)")).click();
    }
}
