package com.softserve.homeworks.task12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSamples3 {
//    @FindBy(css = "a.header_user-name")
//    private WebElement loggedUserIcon;
    private WebElement waitForLoggedUserIcon() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.header_user-name")));
    }

    @FindBy(css = "li.lang-option > img")
    private WebElement langOption;

    @FindBy(xpath = "//ul[@role='menu']/li/span[contains(text(), 'En')]")
    private WebElement enLangOption;

    @FindBy(xpath = "//ul[@role='menu']/li/span[contains(text(), 'Ua')]")
    private WebElement uaLangOption;

//    @FindBy(css = "ul.header_navigation-menu-right-list > a.header_sign-in-link")
//    private WebElement signInButton;
    private WebElement waitForSignInButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul.header_navigation-menu-right-list > a.header_sign-in-link")));
    }

//    @FindBy(css = "app-sign-in > h1")
//    private WebElement welcomeText;
    private WebElement waitForWelcomeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-sign-in > h1")));
    }


    @FindBy(css = "app-sign-in > h2")
    private WebElement signInDetailsText;

    @FindBy(css = "form > label[for='email']")
    private WebElement emailLabel;

//    @FindBy(id = "email")
//    private WebElement emailInput;
    private WebElement waitForEmailInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#email")));
    }

    @FindBy(css = "form > label[for='password']")
    private WebElement passwordLabel;

//    @FindBy(id = "password")
//    private WebElement passwordInput;
    private WebElement waitForPasswordInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password")));
    }

//    @FindBy(css = "button[type='submit']")
//    private WebElement signInSubmitButton;
    private WebElement waitForSignInSubmitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
    }

    @FindBy(css = ".alert-general-error")
    private WebElement generalErrorMessage;

    @FindBy(css = "div#pass-err-msg > app-error > div")
    private WebElement errorPassword;

    @FindBy(css = "div#email-err-msg > app-error > div")
    private WebElement errorEmail;

    private static WebDriver driver;
    private WebDriverWait wait;

    private void jsClick(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void switchToEng() {
        jsClick(langOption);
        wait.until(ExpectedConditions.elementToBeClickable(enLangOption));
        jsClick(enLangOption);
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
            "samplestest@greencity.com, weyt3$Guew^, Welcome back!, Please enter your details to sign in., Email, Password",
            "userTaras@gmail.com, Qwerty_1, Welcome back!, Please enter your details to sign in., Email, Password",
            "userOleh@gmail.com, qwerty1!Q, Welcome back!, Please enter your details to sign in., Email, Password",
            "userAndrii@gmail.com, passwordD:9, Welcome back!, Please enter your details to sign in., Email, Password",
            "rnduser@gmail.com, Rndpass12@, Welcome back!, Please enter your details to sign in., Email, Password"
    })
    public void signInValid(String expectedEmail, String expectedPassword, String expectedWelcomeText,
                       String expectedSignInDetailsText, String expectedEmailLabel, String expectedPasswordLabel) {
        jsClick(waitForSignInButton());

        assertThat(waitForWelcomeText().getText(), is(expectedWelcomeText));
        assertThat(signInDetailsText.getText(), is(expectedSignInDetailsText));

        assertThat(emailLabel.getText(), is(expectedEmailLabel));
        waitForEmailInput().sendKeys(expectedEmail);
        assertThat(waitForEmailInput().getAttribute("value"), is(expectedEmail));

        assertThat(passwordLabel.getText(), is(expectedPasswordLabel));
        waitForPasswordInput().sendKeys(expectedPassword);
        assertThat(waitForPasswordInput().getAttribute("value"), is(expectedPassword));

        jsClick(waitForSignInSubmitButton());

        assertThat(waitForLoggedUserIcon().isDisplayed(), is(true));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, 111weyt3$Guew^, Bad email or password",
            "userTaras@gmail.com, Qwerty_999, Bad email or password",
            "userOlehJoo@gmail.com, qwerty1!Q, Bad email or password",
            "userAndriy@gmail.com, passwordD:9, Bad email or password",
            "rnduser@gmail.com, RndpassQQ12@, Bad email or password"
    })
    public void signInWrongCredentials(String expectedEmail, String expectedPassword, String expectedGeneralError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        jsClick(waitForSignInSubmitButton());

        wait.until(ExpectedConditions.visibilityOf(generalErrorMessage));
        assertThat(generalErrorMessage.getText(), is(expectedGeneralError));
    }

    @ParameterizedTest
    @CsvSource({
            "'', '', Please fill all red fields"
    })
    public void signInEmptyCredentials(String expectedEmail, String expectedPassword, String expectedGeneralError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        emailLabel.click(); // for errors to be visible

        wait.until(ExpectedConditions.visibilityOf(generalErrorMessage));
        assertThat(generalErrorMessage.getText(), is(expectedGeneralError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestestgreencity.com, weyt3$Guew^, Please check that your e-mail address is indicated correctly",
            "userTaras@gmail, Qwerty_1, Please check that your e-mail address is indicated correctly",
            "@gmail.com, qwerty1!Q, Please check that your e-mail address is indicated correctly",
            "userAndrii@.com, passwordD:9, Please check that your e-mail address is indicated correctly"
    })
    public void signInNotValidEmail(String expectedEmail, String expectedPassword, String expectedEmailError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        emailLabel.click(); // for errors to be visible

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        assertThat(errorEmail.getText(), is(expectedEmailError));
    }

    @ParameterizedTest
    @CsvSource({
            "'', weyt3$Guew^, Email is required"
    })
    public void signInEmptyEmail(String expectedEmail, String expectedPassword, String expectedEmailError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        emailLabel.click(); // for errors to be visible

        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        assertThat(errorEmail.getText(), is(expectedEmailError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, 111q, Password must be at least 8 characters long without spaces",
            "samplestest@greencity.com, 111q11111111111qqqqqqq, Password must be less than 20 characters long without spaces.",
            "userTaras@gmail.com, Qwerty1, Password must be at least 8 characters long without spaces",
            "userOleh@gmail.com, qwerty1!Qqwerty1!Qqwerty1!, Password must be less than 20 characters long without spaces."
    })
    public void signInNotValidPassword(String expectedEmail, String expectedPassword, String expectedPasswordError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        emailLabel.click(); // for errors to be visible

        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        assertThat(errorPassword.getText(), is(expectedPasswordError));
    }

    @ParameterizedTest
    @CsvSource({
            "samplestestgreencity.com, '', Password is required"
    })
    public void signInEmptyPassword(String expectedEmail, String expectedPassword, String expectedPasswordError) {
        jsClick(waitForSignInButton());

        waitForEmailInput().sendKeys(expectedEmail);
        waitForPasswordInput().sendKeys(expectedPassword);
        emailLabel.click(); // for errors to be visible

        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        assertThat(errorPassword.getText(), is(expectedPasswordError));
    }

    @AfterEach
    public void deleteCookies() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.removeItem('accessToken');");
        js.executeScript("window.localStorage.removeItem('refreshToken');");
        driver.navigate().refresh();
        driver.manage().deleteAllCookies();

    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}