package com.softserve.edu.selen;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoVisible {

    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        //
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        //
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public void tear() {
        if (driver != null) {
            driver.quit();
            //driver.close();
        }
        //
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        driver.get("https://demo.opencart.com/");
        Thread.sleep(2000); // For Presentation
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        Thread.sleep(4000); // For Presentation
        //
        System.out.println("\t@AfterEach executed");
    }

    @Test
    public void checkDemo() throws InterruptedException {
        System.out.println("Test start");
        //
        WebElement account = driver.findElement(By.cssSelector("div.nav.float-end a.dropdown-toggle"));
        account.click();
        Thread.sleep(2000); // For Presentation
        //
        WebElement register = driver.findElement(By.cssSelector("a[href*='route=account/register']"));
        System.out.println("account open: register.isDisplayed() = " + register.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        account.click();
        System.out.println("account close: register.isDisplayed() = " + register.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        System.out.println("Test done");
    }
}
