package com.softserve.edu.selen;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VisileTest {
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;
    private JavascriptExecutor js;
    protected boolean isTestSuccessful = false;

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
        driver.get("https://www.pick-up.city/#/ubs");
        isTestSuccessful = false;
        Thread.sleep(2000); // For Presentation
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        if (!isTestSuccessful) {
            // Log.error
            //logger.error("Test_Name = " + testInfo.getDisplayName() + " failed");
            //
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
        }
        Thread.sleep(4000); // For Presentation
        //
        System.out.println("\t@AfterEach executed");
    }

    @Test
    public void checkPickUp() throws InterruptedException {
        System.out.println("Test start");
        //
        WebElement howPrepare = driver.findElement(By.cssSelector("#first-section > h2"));
        System.out.println("howPrepare.isDisplayed() = " + howPrepare.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        js.executeScript("window.scrollTo(0,900)");
        System.out.println("window.scrollTo(0,900) howPrepare.isDisplayed() = " + howPrepare.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        js.executeScript("window.scrollTo(0,1500)");
        System.out.println("window.scrollTo(0,1500) howPrepare.isDisplayed() = " + howPrepare.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        js.executeScript("window.scrollTo(0,900)");
        driver.findElement(By.cssSelector("img.ubs-header-sing-in-img")).click();
        Thread.sleep(2000); // For Presentation
        //
        System.out.println("img.ubs-header-sing-in-img howPrepare.isDisplayed() = " + howPrepare.isDisplayed());
        Thread.sleep(2000); // For Presentation
        //
        isTestSuccessful = true;
        //
        System.out.println("Test done");
    }
}
