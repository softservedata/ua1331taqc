package com.softserve.edu.pr_task12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSamples5 {

    private static WebDriver driver;
    private static WebDriverWait wait;


    private void switchToEn() {
        WebElement langOption = find(SignInPageLocators.LANG_OPTION);
        langOption.click();
        if (!langOption.getText().trim().equalsIgnoreCase("En")) {
            find(SignInPageLocators.EN_LANG_OPTION).click();
        }
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void initPage() {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4205/#/greenCity");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        switchToEn();
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
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);

        assertThat(find(SignInPageLocators.WELCOME_TEXT).getText(), is("Welcome back!"));

        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);

        clickElement(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON);
    }

    @DisplayName("Negative Test: Invalid Email Format")
    @ParameterizedTest
    @CsvSource({
            "gabim67170npo2.com, Gabim67170!, Please check that your e-mail address is indicated correctly",
            "cptt31km3vknmcadibav.com, Cptt31km3v@, Please check that your e-mail address is indicated correctly"
    })

    public void signInInvalidEmail(String email, String password, String expectedError) {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);
        clickElement(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON);
        assertThat(find(SignInPageLocators.ERROR_EMAIL).getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Invalid Password")
    @ParameterizedTest
    @CsvSource({
            "cptt31km3v@knmcadibav.com, Cpt31km3v@, Bad email or password",
            "eqoyutv@mailto.plus, eqoyutv@У3, Bad email or password"
    })

    public void signInInvalidPassword(String email, String password, String expectedError) {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);
        clickElement(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON);
        assertThat(find(SignInPageLocators.ERROR_PASSWORD).getText().trim(), is(expectedError));
    }

    @DisplayName("Negative Test: Password is Required")
    @ParameterizedTest
    @CsvSource({
            "gabim67170@npo2.com, '', Password is required",
            "eqoyutv@mailto.plus, '', Password is required"
    })

    public void signInPasswordIsRequired(String email, String password, String expectedError) {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        assertThat(find(SignInPageLocators.PASSWORD_REQUIRED_ERROR).getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Email is Required")
    @ParameterizedTest
    @CsvSource({
            "'', Gabim67170!, Email is required",
            "'', eqoyutv@T3, Email is required"
    })

    public void signInEmailIsRequired(String email, String password, String expectedError) {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);
        clickElement(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON);
        assertThat(find(SignInPageLocators.EMAIL_REQUIRED_ERROR).getText(), is(expectedError));
    }

    @DisplayName("Negative Test: Both Fields are Required")
    @ParameterizedTest
    @CsvSource({
            "'', '', Please fill all red fields"
    })

    public void signInBothFieldsRequired(String email, String password, String expectedError) {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        find(SignInPageLocators.PASSWORD_INPUT).sendKeys(password);
        find(SignInPageLocators.EMAIL_INPUT).sendKeys(email);
        assertThat(find(SignInPageLocators.ERROR_MESSAGE).getText(), is(expectedError));
    }

    @DisplayName("Verify All Sign In Elements Displayed")
    @Test
    public void verifyAllSignInElementsDisplayed() {
        clickElement(SignInPageLocators.SIGN_IN_BUTTON);

        assertThat(find(SignInPageLocators.WELCOME_TEXT).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.SIGN_IN_DETAILS_TEXT).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.EMAIL_LABEL).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.EMAIL_INPUT).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.PASSWORD_LABEL).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.PASSWORD_INPUT).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.SHOW_HIDE_PASSWORD).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.GOOGLE_TEXT_SIGN_IN).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.CLOSE_BUTTON).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.MISSING_ACCOUNT).isDisplayed(), is(true));
        assertThat(find(SignInPageLocators.SIGN_UP_LINK).isDisplayed(), is(true));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

