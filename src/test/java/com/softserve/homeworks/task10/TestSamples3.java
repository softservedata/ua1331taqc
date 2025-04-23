package com.softserve.homeworks.task10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSamples3 {
    @FindBy(css = "a.header_user-name")
    private WebElement loggedUserIcon;

    @FindBy(css = "li.lang-option > img")
    private WebElement langOption;

    @FindBy(xpath = "//ul[@role='menu']/li/span[contains(text(), 'En')]")
    private WebElement enLangOption;

    @FindBy(xpath = "//ul[@role='menu']/li/span[contains(text(), 'Ua')]")
    private WebElement uaLangOption;

    @FindBy(css = "ul.header_navigation-menu-right-list > a.header_sign-in-link")
    private WebElement signInButton;

    @FindBy(css = "app-sign-in > h1")
    private WebElement welcomeText;

    @FindBy(css = "app-sign-in > h2")
    private WebElement signInDetailsText;

    @FindBy(css = "form > label[for='email']")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(css = "form > label[for='password']")
    private WebElement passwordLabel;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    private WebElement generalErrorMessage;

    @FindBy(css = "div#pass-err-msg > app-error > div")
    private WebElement errorPassword;

    @FindBy(css = "div#email-err-msg > app-error > div")
    private WebElement errorEmail;

    private static WebDriver driver;
    private WebDriverWait wait;

    private void switchToEng() {
        langOption.click();
        wait.until(ExpectedConditions.elementToBeClickable(enLangOption));
        enLangOption.click();
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void initPageElements() {
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        switchToEng();
    }

    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^, Welcome back!, Please enter your details to sign in., Email, Password"
    })
    public void signInValid(String expectedEmail, String expectedPassword, String expectedWelcomeText,
                       String expectedSignInDetailsText, String expectedEmailLabel, String expectedPasswordLabel) {
        signInButton.click();

        assertThat(welcomeText.getText(), is(expectedWelcomeText));
        assertThat(signInDetailsText.getText(), is(expectedSignInDetailsText));

        assertThat(emailLabel.getText(), is(expectedEmailLabel));
        emailInput.sendKeys(expectedEmail);
        assertThat(emailInput.getAttribute("value"), is(expectedEmail));

        assertThat(passwordLabel.getText(), is(expectedPasswordLabel));
        passwordInput.sendKeys(expectedPassword);
        assertThat(passwordInput.getAttribute("value"), is(expectedPassword));

        signInSubmitButton.click();
        
        wait.until(ExpectedConditions.visibilityOf(loggedUserIcon));
        assertThat(loggedUserIcon.isDisplayed(), is(true));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, 111weyt3$Guew^, Bad email or password"
    })
    public void signInWrongCredentials(String expectedEmail, String expectedPassword, String expectedGeneralError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(generalErrorMessage));
        assertThat(generalErrorMessage.getText(), is(expectedGeneralError));
    }

    @ParameterizedTest
    @CsvSource({
            "'', '', Please fill all red fields"
    })
    public void signInEmptyCredentials(String expectedEmail, String expectedPassword, String expectedGeneralError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(generalErrorMessage));
        assertThat(generalErrorMessage.getText(), is(expectedGeneralError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestestgreencity.com, weyt3$Guew^, Please check that your e-mail address is indicated correctly"
    })
    public void signInNotValidEmail(String expectedEmail, String expectedPassword, String expectedEmailError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        assertThat(errorEmail.getText(), is(expectedEmailError));
    }

    @ParameterizedTest
    @CsvSource({
            "'', weyt3$Guew^, Email is required"
    })
    public void signInEmptyEmail(String expectedEmail, String expectedPassword, String expectedEmailError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        assertThat(errorEmail.getText(), is(expectedEmailError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestestgreencity.com, 111q, Password must be at least 8 characters long without spaces",
            "samplestestgreencity.com, 111q11111111111qqqqqqq, Password must be less than 20 characters long without spaces.",
    })
    public void signInNotValidPassword(String expectedEmail, String expectedPassword, String expectedPasswordError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        assertThat(errorPassword.getText(), is(expectedPasswordError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestestgreencity.com, '', Password is required"
    })
    public void signInEmptyPassword(String expectedEmail, String expectedPassword, String expectedPasswordError) {
        signInButton.click();

        emailInput.sendKeys(expectedEmail);
        passwordInput.sendKeys(expectedPassword);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        assertThat(errorPassword.getText(), is(expectedPasswordError));
    }

    @AfterEach
    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}