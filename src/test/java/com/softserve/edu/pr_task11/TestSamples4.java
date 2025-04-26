package com.softserve.edu.pr_task11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSamples4 {

    @FindBy(css = ".lang-option")
    private WebElement langOption;

    @FindBy(xpath = "//li[@role='menuitem' and @aria-label='En']")
    private WebElement enLangOption;

    @FindBy(css = ".ubs-header-sing-in-img")
    private WebElement signInButton;

    @FindBy(css = "app-sign-in h1")
    private WebElement welcomeText;

    @FindBy(css = "app-sign-in h2")
    private WebElement signInDetailsText;

    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(css = "label[for='password']")
    private WebElement passwordLabel;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'alert-general-error')]")
    private WebElement errorPassword;

    @FindBy(css = "#email-err-msg div")
    private WebElement errorEmail;

    @FindBy(css = "#pass-err-msg div")
    private WebElement passwordRequiredError;

    @FindBy(css = "#email-err-msg div")
    private WebElement emailRequiredError;

    @FindBy(css = ".image-show-hide-password")
    private WebElement showHidePassword;

    @FindBy(css = ".google-text-sign-in")
    private WebElement googleTextSignIn;

    @FindBy(css = ".cross-btn")
    private WebElement closeButton;

    @FindBy(css = "div.missing-account p")
    private WebElement missingAccount;

    @FindBy(css = "a.green-link")
    private WebElement signUpLink;

    private static WebDriver driver;
    private WebDriverWait wait;


    private void switchToEng() {
        langOption.click();
        String currentLanguage = langOption.getText().trim();
        if (!currentLanguage.equalsIgnoreCase("En")) {
            enLangOption.click();
        }
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void initPageElements() {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4205/#/greenCity");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        switchToEng();
    }

    @Test
    public void verifyTitle() {
        assertEquals("GreenCity", driver.getTitle());
    }

    @DisplayName("Positive Login Tests")
    @ParameterizedTest
    @CsvSource({
            "gabim67170@npo2.com, Gabim67170!",
            "cptt31km3v@knmcadibav.com, Cptt31km3v@",
            "eqoyutv@mailto.plus, eqoyutv@T3"
    })

    public void signInPositive(String email, String password) {
        signInButton.click();

        assertThat(welcomeText.getText(), is("Welcome back!"));
        emailInput.sendKeys(email);
        assertThat(Objects.requireNonNull(emailInput.getAttribute("value")), is(email));
        passwordInput.sendKeys(password);
        assertThat(Objects.requireNonNull(passwordInput.getAttribute("value")), is(password));

        signInSubmitButton.click();
    }

    @DisplayName("Negative Test: Invalid Email Format")
    @ParameterizedTest
    @CsvSource({
            "gabim67170npo2.com, Gabim67170!, Please check that your e-mail address is indicated correctly",
            "cptt31km3vknmcadibav.com, Cptt31km3v@, Please check that your e-mail address is indicated correctly"
    })
    public void signInNotValidEmail(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        assertThat(errorEmail.getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Invalid Password")
    @ParameterizedTest
    @CsvSource({
            "cptt31km3v@knmcadibav.com, Cpt31km3v@, Bad email or password",
            "eqoyutv@mailto.plus, eqoyutv@У3, Bad email or password"
    })

    public void signInNotValidPassword(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        assertThat(errorPassword.getText().trim(), is(expectedError));
    }

    @DisplayName("Negative Test: Password is Required")
    @ParameterizedTest
    @CsvSource({
            "gabim67170@npo2.com, '', Password is required",
            "eqoyutv@mailto.plus, '', Password is required"
    })

    public void signInPasswordIsRequired(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        assertThat(passwordRequiredError.getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Email is Required")
    @ParameterizedTest
    @CsvSource({
            "'', Gabim67170!, Email is required",
            "'', eqoyutv@T3, Email is required"
    })

    public void signInEmailIsRequired(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        assertThat(emailRequiredError.getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Both Fields are Required")
    @ParameterizedTest
    @CsvSource({
            "'', '', Please fill all red fields"
    })
    public void signInBothFieldsRequired(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        assertThat(errorPassword.getText(), is(expectedError));
    }

    @Test
    public void verifyAllSignInElementsDisplayed() {
        signInButton.click();

        assertThat(welcomeText.isDisplayed(), is(true));
        assertThat(signInDetailsText.isDisplayed(), is(true));
        assertThat(emailLabel.isDisplayed(), is(true));
        assertThat(emailInput.isDisplayed(), is(true));
        assertThat(passwordLabel.isDisplayed(), is(true));
        assertThat(passwordInput.isDisplayed(), is(true));
        assertThat(signInSubmitButton.isDisplayed(), is(true));
        assertThat(showHidePassword.isDisplayed(), is(true));
        assertThat(googleTextSignIn.isDisplayed(), is(true));
        assertThat(closeButton.isDisplayed(), is(true));
        assertThat(signUpLink.isDisplayed(), is(true));
        assertThat(missingAccount.isDisplayed(), is(true));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
