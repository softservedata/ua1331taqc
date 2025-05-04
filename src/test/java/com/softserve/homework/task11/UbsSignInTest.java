package com.softserve.homework.task11;

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
public class UbsSignInTest {

    private static WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//li[contains(@role, 'option')]//span[contains(text(), 'UA')]")
    private WebElement langButton; // mien

    @FindBy(css = ".ubs-lang-switcher-span")
    private WebElement en; // mine

    @FindBy(css = "app-header:nth-child(1) .ubs-header-sing-in-img") //can't get rid off magic number
    private WebElement signInButton;

    @FindBy(xpath = "//a//img[contains(@class, 'cross-btn')]")
    private WebElement crossButton; // mine

    @FindBy(xpath = "//app-google-btn//button")
    private WebElement googleButton;

    @FindBy(css = ".ng-star-inserted > h1")
    private WebElement welcomeText;

    @FindBy(xpath = "//app-sign-in//h2[contains(text(), 'Будь ласка, внесiть свої дані для входу.')]")
    private WebElement signInDetailsText;

    @FindBy(xpath = "//label[contains(@for, 'email')]")
    private WebElement emailLabel;

    @FindBy(xpath = "//label[contains(@for, 'password')]")
    private WebElement passwordLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-simple-snackbar > span")
    private WebElement result;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@id, 'email-err-msg')]/app-error/div")
    private WebElement errorEmail;

    @FindBy(xpath = "//div[contains(@id, 'pass-err-msg')]/app-error/div")
    private WebElement errorPassword;



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
        driver.navigate().to("http://localhost:4205/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword",
            "cheeseburger@greencity.com, CrustyCrab@21",
            "squidward@greencity.com, SquidyGriddy@228",
            "bobthebuilder@greencity.com, BuilderPro@01"
    })
    public void positiveSignInTest(String email, String password) {

        clickSafely(langButton);
        clickSafely(en);
        clickSafely(signInButton);


        assertThat(emailLabel.getText(), is("Email"));
        assertThat(passwordLabel.getText(), is("Password"));

        emailInput.sendKeys(email);
        assertThat(emailInput.getAttribute("value"), is(email));

        passwordInput.sendKeys(password);
        assertThat(passwordInput.getAttribute("value"), is(password));

        clickSafely(signInSubmitButton);

    }

//Перевірте коректність введеної електронної адреси
//Пароль повинен містити принаймні 8 символів без пробілів
    @ParameterizedTest
    @CsvSource({
            "samplestesgreencity.com, 145",
            "kimcehnin, =*",
            "bobsponge, spo",
            "squidward, sq#idy"
    })
    public void negativeSignInTest(String email, String password) {
        clickSafely(langButton);
        clickSafely(en);
        clickSafely(signInButton);

        emailInput.sendKeys(email);
        clickSafely(passwordInput);
        assertThat(errorEmail.getText(), is("Please check if the email is written correctly"));

        passwordInput.sendKeys(password);
        clickSafely(passwordLabel);
        assertThat(errorPassword.getText(), is("Password must be at least 8 characters long without spaces"));
    }

    public void waitForPasswordError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void clickSafely(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


}
