package com.softserve.edu.pr_task13_14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {
    protected static WebDriver driver;

    @BeforeAll
    public static void globalSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @AfterAll
    public static void globalTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}