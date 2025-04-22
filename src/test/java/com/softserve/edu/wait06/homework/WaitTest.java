package com.softserve.edu.wait06.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitTest {
    private static WebDriver driver;
   // private static final String BASE_URL = "https://www.greencity.cx.ua/#/greenCity";
   private static final String BASE_URL = "http://localhost:4205/#/greenCity";
    private static final long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final long ONE_SECOND_DELAY = 1000;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//img[@alt='sing in button']")
    private WebElement signInButton;
    @FindBy(css = "app-sign-in h1")
    private WebElement welcomeText;
    @FindBy(css = "app-sign-in h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "button.greenStyle[type='submit']")
    private WebElement signInSubmitButton;
    @FindBy(css=".mat-simple-snackbar > span")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@id='pass-err-msg']/app-error/div")
    private WebElement errorPassword;
    @FindBy(xpath = "//div[contains(@class, 'alert-general-error')]")
    private WebElement errorEmail;
    @FindBy(xpath = "//button[normalize-space(text())='Увійти']")
    private WebElement signIn;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }
    @BeforeEach
    public void initPageElements() {
        driver.get(BASE_URL);
        PageFactory.initElements(driver, this);
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyTitle() {
        assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/emailpassword.csv", numLinesToSkip = 1)
    public void signIn(String email, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebElement signInNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//img[@alt='sing in button']")));
        signInNew.click();

        WebElement welcomeTextNew = (WebElement) (js.executeScript(
                "return document.querySelector('app-sign-in h1')"));
        assertEquals("З поверненням!", welcomeTextNew.getText());

        WebElement signInDetailsTextNew = (WebElement) (js.executeScript(
                "return document.querySelector('app-sign-in h2')"));
        assertEquals("Будь ласка, внесiть свої дані для входу.", signInDetailsTextNew.getText());

        assertEquals("Електронна пошта", emailLabel.getText());
        WebElement emailInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("email")));
        emailInputNew.sendKeys(email);
        assertEquals(emailInputNew.getDomProperty("value"), email);

        WebElement passwordInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("password")));
        passwordInputNew.sendKeys(password);

        assertEquals(passwordInputNew.getDomProperty("value"), password);

        WebElement signInSubmitNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("button.greenStyle[type='submit']")));
        signInSubmitNew.click();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/emailpassword2.csv", numLinesToSkip = 1)
    public void signInNotSuccessful(String email, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebElement signInNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//img[@alt='sing in button']")));
        signInNew.click();

        WebElement welcomeTextNew = (WebElement) (js.executeScript(
                "return document.querySelector('app-sign-in h1')"));
        assertEquals("З поверненням!", welcomeTextNew.getText());

    //    assertEquals("З поверненням!", welcomeText.getText());
        WebElement signInDetailsTextNew = (WebElement) (js.executeScript(
                "return document.querySelector('app-sign-in h2')"));
        assertEquals("Будь ласка, внесiть свої дані для входу.", signInDetailsTextNew.getText());

        assertEquals("Електронна пошта", emailLabel.getText());
        WebElement emailInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("email")));
        emailInputNew.sendKeys(email);
        assertEquals(emailInputNew.getDomProperty("value"), email);

        WebElement passwordInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("password")));
        passwordInputNew.sendKeys(password);

        assertEquals(passwordInputNew.getDomProperty("value"), password);

        WebElement signInSubmitNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("button.greenStyle[type='submit']")));
        signInSubmitNew.click();

    }

    @ParameterizedTest
    @CsvSource({
            "Введено невірний email або пароль\n"
    })
    public void signInInvalidEmail(String message) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebElement signInNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//img[@alt='sing in button']")));
        signInNew.click();

        WebElement emailInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("email")));
        emailInputNew.sendKeys("test@gmail.com");

        WebElement passwordInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("password")));
        passwordInputNew.sendKeys("MyTest2025!");

        signIn.click();
        WebElement errorEmailNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-general-error')]")));
        assertEquals(errorEmailNew.getText(), message);

    }

    @ParameterizedTest
    @CsvSource({
            " Введено невірний email або пароль "
    })
    public void signInInvalidPassword(String message) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebElement signInNew = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//img[@alt='sing in button']")));
        signInNew.click();
        WebElement emailInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("email")));
        emailInputNew.sendKeys("test01@gmail.com");

        WebElement passwordInputNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("password")));
        passwordInputNew.sendKeys("MyTest2025");

        signIn.click();
        WebElement errorEmailNew = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-general-error')]")));
        assertEquals(errorEmailNew.getText(), message);

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit(); // close()
        }
        System.out.println("@AfterAll executed");
    }
}
