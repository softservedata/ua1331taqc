package com.softserve.edu06wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumDownload {
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;

    protected static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tear() {
        presentationSleep(4); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() {
        driver.get("https://selenium.dev/downloads/");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        presentationSleep(); // For Presentation ONLY
    }

    @Test
    public void checlDisplayElements() {
        // Step
        System.out.println("[INFO] Start Test testLogin()");
        presentationSleep(); // For Presentation ONLY
        //
        //WebElement perfecto = driver.findElement(By.cssSelector("a[href='https://www.perfecto.io/']"));
        WebElement perfecto = driver.findElement(By.cssSelector("a[href*='applitools.com']"));
        //
        // Implicit
        WebElement browserstack = driver.findElement(By.cssSelector("a[href*='www.browserstack.com']"));
        //
        /* Explicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NullPointerException.class)
                .ignoring(TimeoutException.class);
        WebElement browserstack = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("a[href*='www.browserstack.com']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        */
        // by Javascript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //WebElement browserstack = (WebElement) (js
        //        .executeScript("return document.querySelector('a[href*=\"www.browserstack.com\"]')"));
        //
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("browserstack.isDisplayed() = " + browserstack.isDisplayed());
        System.out.println("browserstack.isEnabled() = " + browserstack.isEnabled());
        //
        // Scrolling to an element by JavaScript
        js.executeScript("arguments[0].scrollIntoView(true);", perfecto);
        //js.executeScript("window.scrollTo(0, 1000);");
        //
        // Scrolling by Action class
        //Actions action = new Actions(driver);
        //action.moveToElement(perfecto).perform();
        //
        //WebElement seleniumTextTenter =  driver.findElement(By.cssSelector("h2.selenium.text-center"));
        //js.executeScript("arguments[0].scrollIntoView(true);", seleniumTextTenter);
        //
        presentationSleep(4); // For Presentation ONLY
        //
        System.out.println("browserstack.isDisplayed() = " + browserstack.isDisplayed()); // Error
        System.out.println("browserstack.isEnabled() = " + browserstack.isEnabled());
        //
        //System.out.println("class = " + perfecto.getAttribute("class"));
        System.out.println("browserstack CSS class = " + browserstack.getAttribute("class"));
        presentationSleep(); // For Presentation ONLY
        //
        /* Explicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement browserstack2 = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("a[href*='www.browserstack.com']"))); // Error in Selenium
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browserstack2.click();
        */
        //
        //browserstack.click(); // Error after JS Scrolling
        //
        // CLick by JavaScript
        js.executeScript("arguments[0].click();", browserstack);
        //presentationSleep(); // For Presentation ONLY
        //
        //js.executeScript("alert('Welcome to TAQC');");
        //presentationSleep(4); // For Presentation ONLY
        //
        //driver.switchTo().alert().accept();
        //presentationSleep(); // For Presentation ONLY
    }
}
            