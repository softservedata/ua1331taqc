package com.softserve.edu04paral;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelenParallelTest {

    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private Map<Long, WebDriver> drivers;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        //
        drivers = new HashMap<>();
        System.out.println("@BeforeAll executed");
    }

    @AfterAll
    public void tear() {
        for (Map.Entry<Long, WebDriver> entry : drivers.entrySet()) {
            System.out.println("ThreadId key = " + entry.getKey());
            WebDriver driver = entry.getValue();
            if (driver != null) {
                driver.quit();
                //driver.close();
            }
        }
        //
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        //driver.manage().window().maximize();
        drivers.put(Thread.currentThread().getId(), driver);
        //
        driver.get("https://www.bing.com/");
        Thread.sleep(2000); // For Presentation
        //
        System.out.println("\t@BeforeEach executed");
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        Thread.sleep(8000); // For Presentation
        //
        System.out.println("\t@AfterEach executed");
    }

    @Test
    public void checkBing() throws InterruptedException {
        System.out.println("Test start");
        WebDriver driver = drivers.get(Thread.currentThread().getId());
        //
        WebElement div = driver.findElement(By.id("sb_form_c"));
        //WebElement q = div.findElement(By.cssSelector("div > textarea")); // CSS
        //WebElement q = div.findElement(By.xpath("/div/textarea")); // XPath Search from root
        WebElement q = div.findElement(By.xpath("./div/textarea")); // XPath Ok
        q.sendKeys("mac");
        //
        //driver.findElement(By.name("q")).sendKeys("mac");
        Thread.sleep(2000); // For Presentation
        //
        //driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //
        System.out.println("Test done");
    }

    @Test
    public void checkBing2() throws InterruptedException {
        System.out.println("Test start");
        WebDriver driver = drivers.get(Thread.currentThread().getId());
        //
        WebElement div = driver.findElement(By.id("sb_form_c"));
        //WebElement q = div.findElement(By.cssSelector("div > textarea")); // CSS
        //WebElement q = div.findElement(By.xpath("/div/textarea")); // XPath Search from root
        WebElement q = div.findElement(By.xpath("./div/textarea")); // XPath Ok
        q.sendKeys("mac");
        //
        //driver.findElement(By.name("q")).sendKeys("mac");
        Thread.sleep(2000); // For Presentation
        //
        //driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //
        System.out.println("Test done");
    }
}
