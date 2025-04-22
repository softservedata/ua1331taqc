package com.softserve.edu.selen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplFirstSelenTest {

    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        //
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
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