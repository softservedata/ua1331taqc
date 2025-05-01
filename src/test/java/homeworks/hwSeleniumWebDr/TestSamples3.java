package homeworks.hwSeleniumWebDr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(css = ".lang-option")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//li[@aria-label='En']")
    private WebElement enOption;

    @FindBy(xpath = "//li[@aria-label='Ua']")
    private WebElement uaOption;

    @FindBy(css = ".header_sign-in-link")
    private WebElement signInButton;

    @FindBy(xpath = "//app-sign-in//h1")
    private WebElement welcomeText;

    @FindBy(xpath = "//app-sign-in//h2")
    private WebElement signInDetailsText;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".greenStyle")
    private WebElement signInSubmitButton;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(css = "#pass-err-msg")
    private WebElement errorPassword;

    @FindBy(css = ".alert-general-error")
    private WebElement generalError;

    @FindBy(css = "#email-err-msg")
    private WebElement errorEmail;

    @FindBy(css = ".name")
    private WebElement name;

    @FindBy(css = "a.header_user-name")
    private WebElement userName;

    @FindBy(css = "li.drop-down-item:nth-child(3)")
    private WebElement logOut;


    private static WebDriver driver;
    private WebDriverWait wait;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @BeforeEach
    public void setUpEach() {
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        initPageElements();


    }

    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }


    public void switchLanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(languageSwitcher)).click();
        wait.until(ExpectedConditions.elementToBeClickable(enOption)).click();
    }



    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
        // assertThat(driver.getTitle(), is("GreenCity"));
    }



    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; weyt3$Guew^; Welcome back!; Please enter your details to sign in.; Email",

    })
    public void signInEn(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel) {
        switchLanguage();
        signInButton.click();
        assertThat(welcomeText.getText(), is(expectedWelcome));
        assertThat(signInDetailsText.getText(), is(expectedDetails));
        assertThat(emailLabel.getText(), is(expectedEmailLabel));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(name));
        Assertions.assertEquals("Svitlana", name.getText());
        userName.click();
        logOut.click();
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; weyt3$Guew^; З поверненням!; Будь ласка, внесiть свої дані для входу.; Електронна пошта"
    })
    public void signInUa(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel) {
        signInButton.click();
        assertThat(welcomeText.getText(), is(expectedWelcome));
        assertThat(signInDetailsText.getText(), is(expectedDetails));
        assertThat(emailLabel.getText(), is(expectedEmailLabel));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(name));
        Assertions.assertEquals("Svitlana", name.getText());
        userName.click();
        logOut.click();
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestesgreencity.com; Please check that your e-mail address is indicated correctly"
    })
    public void signInNotValidEn(String email, String message) {
        switchLanguage();
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("somepassword");
        signInSubmitButton.click();
        Assertions.assertEquals(message, errorEmail.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestesgreencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти"
    })
    public void signInNotValidUa(String email, String message) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("somepassword");
        signInSubmitButton.click();
        Assertions.assertEquals(message, errorEmail.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; wrongpass123!; Bad email or password",
    })
    public void signInWithInvalidPasswordEn(String email, String password, String expectedError) {
        switchLanguage();
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(generalError));
        Assertions.assertEquals(expectedError, generalError.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; wrongpass123!; Введено невірний email або пароль"
    })
    public void signInWithInvalidPasswordUa(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(generalError));
        Assertions.assertEquals(expectedError, generalError.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "''; somepassword; Email is required",

    })
    public void signInWithEmptyEmailEn(String email, String password, String expectedError) {
        switchLanguage();
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        Assertions.assertEquals(expectedError, errorEmail.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "''; somepassword; Введіть пошту"
    })
    public void signInWithEmptyEmailUa(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        Assertions.assertEquals(expectedError, errorEmail.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; ''; Password is required",

    })
    public void signInWithEmptyPasswordEn(String email, String password, String expectedError) {
        switchLanguage();
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        Assertions.assertEquals(expectedError, errorPassword.getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; ''; Введіть пароль"
    })
    public void signInWithEmptyPasswordUa(String email, String password, String expectedError) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        Assertions.assertEquals(expectedError, errorPassword.getText());
    }

    @AfterEach
    public void refreshPage() {
        driver.navigate().refresh();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
