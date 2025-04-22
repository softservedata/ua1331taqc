package com.softserve.edu.wait06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class FooterButton {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // 0 by default
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
        driver.get("https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/paging/");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        presentationSleep(2); // For Presentation ONLY
    }

    //@Test
    @ParameterizedTest
    @ValueSource(strings = {"First", "Second"})
    public void checlDisplayElements(String message) {
        // Step
        System.out.println("[INFO] Start Test testLogin(), item = " + message);
        presentationSleep(); // For Presentation ONLY
        //
        List<WebElement> footerButton = driver
                .findElements(By.xpath("//footer[contains(@class,'cookie')]//button"));
        System.out.println("footerButton.size() = " + footerButton.size());
        if (footerButton.size() > 0) {
            presentationSleep(); // For Presentation ONLY
            footerButton.get(0).click();
        }
        presentationSleep(); // For Presentation ONLY
        //
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 1000);");
        presentationSleep(); // For Presentation ONLY
    }
}
          