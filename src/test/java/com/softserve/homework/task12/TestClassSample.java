package com.softserve.homework.task12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestClassSample {
    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void init() {
        // Скидаємо сесію
        driver.manage().deleteAllCookies();
        driver.navigate().to("http://localhost:4205/#/greenCity");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @ParameterizedTest
    @CsvSource({
            "test@gmail.com, Qwerty1234!"
    })
    public void ecoNewsTest(String email, String password){
        // sign in
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[contains(@alt, 'sing in button')]")));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[contains(@alt, 'sing in button')]")
        )).click();

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#email")
        ));
        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#password")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", emailInput);
//        emailInput.click();
        emailInput.sendKeys(email);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", passwordInput);
//        passwordInput.click();
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(@type, 'submit')][contains(text(), ' Увійти ')]")));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@type, 'submit')][contains(text(), ' Увійти ')]"))).click();

        // click eco news
        WebElement ecoNews = driver.findElement(By.xpath("//li[contains(@role, 'listitem')]" +
                "/a[contains(text(), ' Еко новини ')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ecoNews);

        // click create news
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("create-button"))).click();
        // click create habit
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'habit-header')]/button[contains(text(), ' Create habit ')]")
        )).click();
        // fill the inputs
        WebElement habitInput = driver.findElement(By.xpath("//div[contains(@class, 'title-box')]//input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", habitInput);
        habitInput.sendKeys("Push-ups");

        WebElement describeHabit = driver.findElement(By.xpath("//div[contains(@data-placeholder, 'Describe the habit')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", describeHabit);
        habitInput.sendKeys("20 push-ups a day");

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}

