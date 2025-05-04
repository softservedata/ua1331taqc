package com.softserve.homework.task10;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

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

    @FindBy(css = "app-sign-in > h1")
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

    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
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

    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword"
    })
    public void signIn(String email, String password) {

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

    @ParameterizedTest
    @CsvSource({
            "samplestesgreencity.com, Перевірте коректність введеної електронної адреси",
            "wrong, Пароль повинен містити принаймні 8 символів без пробілів"
    })
    public void signInNotValid(String input, String message) {
        clickSafely(signInButton);
        emailInput.sendKeys(input);
        passwordInput.sendKeys(input);
        emailLabel.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if (input.equals("samplestesgreencity.com")) {
            wait.until(ExpectedConditions.visibilityOf(errorEmail));
            assertThat("Email error message", errorEmail.getText(), is(message));
        } else if (input.equals("wrong")) {
            wait.until(ExpectedConditions.visibilityOf(errorPassword));
            assertThat("Password error message", errorPassword.getText(), is(message));
        }
    }


    @ParameterizedTest
    @DisplayName("verifyCross()")
    @CsvSource({
            "true, Cross is visible"
    })
    public void crossCheck(boolean expectedVisible, String description){
        clickSafely(signInButton);
        assertThat("Cross button visibility", crossButton.isDisplayed(), is(expectedVisible));
        clickSafely(crossButton);
    }

    @Test
    public void googleBtnCheck(){
        clickSafely(signInButton);
        assertThat(googleButton.isDisplayed(), is(true));
        clickSafely(googleButton);
        assertThat("Should stay on the same page", driver.getCurrentUrl(),
                is("http://localhost:4205/#/ubs"));
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
