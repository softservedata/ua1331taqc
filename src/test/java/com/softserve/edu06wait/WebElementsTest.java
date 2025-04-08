package com.softserve.edu06wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebElementsTest {
    //private final String BASE_URL = "https://demo.opencart.com/";
    private final String BASE_URL = "https://demo.opencart.ua";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        // WebDriverManager.firefoxdriver().setup();
        //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();
        //
        //driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void beforeClass() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
    }

    @AfterAll
    public void afterClass() {
        presentationSleep(); // For Presentation ONLY
        // driver.close();
        if (driver != null) {
            driver.quit(); // close()
        }
    }

    @AfterEach
    public void afterMethod() {
        // logout;
        // Save Screen;

        // Take Screenshot, save sourceCode, save to log, prepare report, Return to;
        // previous state, logout, etc.
        // takeScreenShot(testName);
        // takePageSource(testName);
        // driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;

        // driver.findElement(By.cssSelector("#logo .img-responsive")).click();
        // driver.findElement(By.cssSelector("#logo > a")).click();
        //driver.findElement(By.xpath("//img[contains(@src, '/logo.png')]/..")).click();
        presentationSleep(); // For Presentation ONLY
    }

    @Test
    public void checkExistWebElement() throws Exception {
        //
        System.out.println("1. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/account']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        System.out.println("2. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // /*-
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("hahaha");
        //
        String content = driver.findElement(By.id("input-email")).getAttribute("value");
        System.out.println("***content email = " + content);
        presentationSleep(2); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.id("input-email")).sendKeys("bebebe");
        presentationSleep(); // For Presentation ONLY
        // */
        /*-
        WebElement email = driver.findElement(By.id("input-email"));
        //
        email.click();
        email.clear();
        email.sendKeys("hahaha");
        String content = email.getAttribute("value");
        System.out.println("content email = " + content);
        presentationSleep(); // For Presentation ONLY
        //
        // Refresh some webelements
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        email.sendKeys("bebebe"); // Runtime Error
        presentationSleep(); // For Presentation ONLY
        */
        //
        // driver.findElement(By.id("input-email")).sendKeys("password");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys("password");
        content = driver.findElement(By.id("input-password")).getAttribute("value");
        System.out.println("content password = " + content);
        presentationSleep(2); // For Presentation ONLY
        //
    }

}