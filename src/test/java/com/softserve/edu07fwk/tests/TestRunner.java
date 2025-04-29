package com.softserve.edu07fwk.tests;

import com.softserve.edu07fwk.pages.HomeUbsPage;
import com.softserve.edu07fwk.tools.UrlWrapper;
import com.softserve.edu07fwk.tools.WebDriverWrapper;
import com.softserve.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RunnerExtension.class)
public abstract class TestRunner {
    //
    private final Long ONE_SECOND_DELAY = 1000L;
    //
    protected static boolean isTestSuccessful = false;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Overload
    protected void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public void setup() {
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public void tear() {
        WebDriverWrapper.quit();
        //
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        if (!RunnerExtension.isTestSuccessful) {
            // Log.error
            // logger.error("Test_Display_Name = " + testInfo.getDisplayName() + " failed");
            logger.error("Test_Name = " + testInfo.getTestMethod() + " failed");
            //
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
            //
            WebDriverWrapper.takeScreenShot();
            WebDriverWrapper.takePageSource();
        }
        WebDriverWrapper.deleteCookies();
        WebDriverWrapper.deleteTokens();
        WebDriverWrapper.refreshPage();
        //
        presentationSleep(4); // For Presentation ONLY
        //
        System.out.println("\t@AfterEach executed");
    }

    protected HomeUbsPage loadApplication() {
        logger.debug("Start loadApplication()");
        //
        WebDriverWrapper.gotoUrl(UrlWrapper.getUrl());
        presentationSleep(); // For Presentation ONLY
        return new HomeUbsPage(WebDriverWrapper.getDriver());
    }

}
