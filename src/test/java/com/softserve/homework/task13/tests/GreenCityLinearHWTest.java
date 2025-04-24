package com.softserve.homework.task13.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

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
@ExtendWith(RunnerExtensionHW.class)
public class GreenCityLinearHWTest {
    private final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    //private final String BASE_URL = "http://greencity.eastus.cloudapp.azure.com/#/ubs";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long IMPLICITLY_WAIT_ONE_SECONDS = 1L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final String LOCALSTORAGE_REMOVE = "window.localStorage.removeItem('%s');";
    private final String REMOVE_ATTRIBUTE = "document.querySelector('%s').removeAttribute('disabled')";
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    //protected static boolean isTestSuccessful = false;
    //protected final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    private void takeScreenShot() {
        //logger.debug("Start takeScreenShot()");
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

    private void takePageSource() {
        //logger.debug("Start takePageSource()");
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
        //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        //
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().window().setSize(new Dimension(1200, 800));
        //
        javascriptExecutor = (JavascriptExecutor) driver;
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
        driver.get(BASE_URL);
        presentationSleep(4); // For Presentation ONLY
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        //if (!isTestSuccessful) {
        if (!RunnerExtensionHW.isTestSuccessful) {
            // Log.error
            //logger.error("Test_Name = " + testInfo.getDisplayName() + " failed");
            //
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
            //
            takeScreenShot();
            takePageSource();
        }
        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "accessToken"));
        javascriptExecutor.executeScript(String.format(LOCALSTORAGE_REMOVE, "refreshToken"));
        // js.executeScript(String.format("window.localStorage.setItem('%s','%s');", item, value));
        driver.navigate().refresh();
        presentationSleep(4); // For Presentation ONLY
        //
        System.out.println("\t@AfterEach executed");
    }
    @Test
    public void checkAbout() throws InterruptedException {
        System.out.println("Test start");
        //
        // Login
        //driver.findElement(By.cssSelector("app-ubs .ubs-header-sing-in-img")).click();
        //
        // Goto Greencity
        driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/greenCity']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Goto About Us
        driver.findElement(By.cssSelector("div.header_container  a[href*='/greenCity/about']")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Get section__header
        WebElement aboutusHeader = driver.findElement(By.cssSelector("div.about-section h2.section__header"));
        presentationSleep(); // For Presentation ONLY
        //
        Assertions.assertTrue(aboutusHeader.getText().toLowerCase().contains("About Us".toLowerCase()));
    }

    @Test
    public void checkSignin() throws InterruptedException {
        System.out.println("Test start");
        //
        // Goto Login
        driver.findElement(By.cssSelector("app-ubs .ubs-header-sing-in-img")).click();
        //
        // Type email
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("cicada32073@mailshan.com");
        presentationSleep(2); // For Presentation ONLY
        //
        // Type password
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Qwerty_1");
        presentationSleep(2); // For Presentation ONLY
        //
        // Click Signin Button
        javascriptExecutor.executeScript(String.format(REMOVE_ATTRIBUTE, "button[type=\"submit\"]"));
        //driver.findElement(By.cssSelector("button[type='submit']")).click();
        presentationSleep(2); // For Presentation ONLY
        //
    }
}
