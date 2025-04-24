package com.softserve.homeWork.task12;

import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSamples3 {

    @FindBy(css = ".lang-option")
    private WebElement langButton;
    @FindBy(css = "li[aria-label='En']")
    private WebElement switchEu;
    @FindBy(css = ".header_lang-switcher-wrp>li>span")
    private WebElement lang;
    //----------------------------
    @FindBy(css = ".ubs-header-sing-in-img")
    private WebElement signInButton;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "button.greenStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = "div#pass-err-msg>app-error>div")
    private WebElement errorPassword;
    @FindBy(css = "div#email-err-msg>app-error>div")
    private WebElement errorEmail;
    @FindBy(css = ".sign-in-form>label[for='email']")
    private WebElement emailLabel;
    @FindBy(css = "div.right-side>a.close-modal-window>img")
    private WebElement exitButton;
    @FindBy(css = "app-google-btn>button")
    private WebElement googleSignIn;

    @FindBy(css = ".forgot-wrapper>a")
    private WebElement forgotPassword;
    @FindBy(css = ".restore-password-container>h1")
    private WebElement h1;

    private static WebDriver driver;
    private WebDriverWait wait;


    private void clickWaitElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void init() {
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://www.greencity.cx.ua/#/greenCity");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    @Test
    public void verifyTitleTest() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
        // assertThat(driver.getTitle(), is("GreenCity"));
    }

    @Test
    public void assertButtonTest() throws InterruptedException {
        Thread.sleep(2000);
        try {
            if (signInButton.isDisplayed()) {
                System.out.println("Disp");
                clickWaitElement(signInButton);
                Thread.sleep(2000);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Err");
        }
    }

    @Test
    @DisplayName("forgot-password()")
    public void forgotPasswordTest() {
        clickWaitElement(signInButton);
        clickWaitElement(forgotPassword);
        assertThat("Should stay on the same page", driver.getCurrentUrl(),
                is("https://www.greencity.cx.ua/#/greenCity"));
        if (lang.getText() == "En") {
            assertThat(h1.getText(), is("Trouble singing in?"));
        } else {
            assertThat(h1.getText(), is("Проблеми зі входом?"));
        }

    }

    @Test
    @DisplayName("verifyGoogleButton()")
    public void googleSignInTest() {
        clickWaitElement(signInButton);
        assertThat(googleSignIn.isDisplayed(), is(true));
        clickWaitElement(googleSignIn);
        assertThat("Should stay on the same page", driver.getCurrentUrl(),
                is("https://www.greencity.cx.ua/#/greenCity"));
    }

    @ParameterizedTest
    @DisplayName("Test in login")
    @CsvFileSource(resources = "/dataInLogin.csv", numLinesToSkip = 1)
    public void singInTest(String email, String password) throws InterruptedException {
        clickWaitElement(signInButton);

        if (emailInput.isEnabled() && emailInput.isDisplayed()) {
            clickWaitElement(emailInput);
            emailInput.sendKeys(email);
            assertThat(emailInput.getAttribute("value"), is(email));
        }

        if (passwordInput.isDisplayed() && passwordInput.isEnabled()) {
            clickWaitElement(passwordInput);
            passwordInput.sendKeys(password);
            assertThat(passwordInput.getAttribute("value"), is(password));
        }

    }

    @ParameterizedTest
    @DisplayName("Test sign ")
    @CsvSource({
            "samplestesgreencity.com, 145",
            "kimcehnin, =*",
            "bobsponge, spo",
            "squidward, sq#idy"
    })
    public void signInNotValidTest(String email, String password) {
        clickWaitElement(signInButton);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        emailLabel.click();

        if (lang.getText() == "En") {
            assertThat(errorEmail.getText(), is("Please check if the email is written correctly"));
            assertThat(errorPassword.getText(), is("Password must be at least 8 characters long without spaces"));
        } else {
            assertThat(errorEmail.getText(), is("Перевірте, чи правильно вказано вашу адресу електронної пошти"));
            assertThat(errorPassword.getText(), is("Пароль повинен мати від 8 до 20 символів без пробілів, містити хоча " +
                    "б один символ латинського алфавіту верхнього (A-Z) та нижнього регістру (a-z), число (0-9) та спеціальний " +
                    "символ (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)"));
        }
    }

    @Test
    @DisplayName("")
    public void exitButtonTest() {
        clickWaitElement(signInButton);
        assertThat(exitButton.isDisplayed(), is(true));
        clickWaitElement(exitButton);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}


