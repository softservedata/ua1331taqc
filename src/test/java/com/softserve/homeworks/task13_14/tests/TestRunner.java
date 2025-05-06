package com.softserve.homeworks.task13_14.tests;

import com.softserve.homeworks.task13_14.pages.HomeGreencityPage;
import com.softserve.homeworks.task13_14.pages.SigninPage;
import com.softserve.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RunnerExtension.class)
public abstract class TestRunner {
    //
    private final String BASE_URL = "http://localhost:4205/#/greenCity";
    //private final String BASE_URL = "http://greencity.eastus.cloudapp.azure.com/#/ubs";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long IMPLICITLY_WAIT_ONE_SECONDS = 1L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final String LOCALSTORAGE_REMOVE = "window.localStorage.removeItem('%s');";
    //private final String REMOVE_ATTRIBUTE = "document.querySelector('%s').removeAttribute('disabled')";
    private PropertiesUtil propertiesUtil;
    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;
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

    // Add test name
    private void takeScreenShot() {
        logger.debug("Start takeScreenShot()");
        //
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    // TODO Get Actual Page
    private void takePageSource() {
        logger.debug("Start takePageSource()");
        //
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public void setup() {
        logger.debug("BeforeAll() executed");
        readProperties();
        if (propertiesUtil.readBrowserName().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            //
            //ChromeOptions options = new ChromeOptions();
            //options.addArguments("--remote-allow-origins=*");
            //driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        }
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().window().maximize();
        //
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @AfterAll
    public void tear() {
        logger.debug("AfterAll() executed");
        if (driver != null) {
            driver.quit();
            //driver.close();
        }
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        //driver.get(BASE_URL);
        //presentationSleep(4); // For Presentation ONLY
        logger.debug("BeforeEach() executed");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        if (!RunnerExtension.isTestSuccessful) {
            logger.error("Test_Method = " + testInfo.getTestMethod() + " failed");
            //System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            //
            takeScreenShot();
            takePageSource();
        }
        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "accessToken"));
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "refreshToken"));
        // js.executeScript(String.format("window.localStorage.setItem('%s','%s');", item, value));
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY

        logger.debug("AfterEach() executed");
    }

    protected HomeGreencityPage loadApplication() {
        logger.debug("Start loadApplication()");
        //
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
        return new HomeGreencityPage(driver);
    }

    protected SigninPage openSigninPage() {
        logger.debug("Start openSigninPage()");
        return loadApplication().chooseEnglish().gotoSigninPage();
    }

    private void readProperties() {
        propertiesUtil = new PropertiesUtil();
//        try {
//            implicitlyWaitSeconds = Long.valueOf(propertiesUtil.readImplicitlyWait());
//        } catch (NumberFormatException e) {
//            implicitlyWaitSeconds = 100L;
//        }
        //baseUrl = propertiesUtil.readBaseUrl();
    }

}