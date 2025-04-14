package homeworks.hwSeleniumWebDr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSamples3 {
    @FindBy(css = ".lang-option")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//ul/ul/li[1]/span")
    private WebElement enOption;

    @FindBy(xpath = "//ul/ul/li[2]/span")
    private WebElement uaOption;

    @FindBy(css = ".header_sign-in-link")
    private WebElement signInButton;

    @FindBy(css = "app-sign-in.ng-star-inserted > h1:nth-child(1)")
    private WebElement welcomeText;

    @FindBy(css = "app-sign-in.ng-star-inserted > h2:nth-child(2)")
    private WebElement signInDetailsText;

    @FindBy(css = "label:nth-child(1)")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = ".greenStyle")
    private WebElement signInSubmitButton;

//    @FindBy(css="----------")
//    private WebElement result;

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(css = "#pass-err-msg > app-error:nth-child(1)")
    private WebElement errorPassword;

    @FindBy(css = ".alert-general-error")
    private WebElement generalError;

    @FindBy(css = "#email-err-msg > app-error:nth-child(1)")
    private WebElement errorEmail;

    @FindBy(css = ".name")
    private WebElement name;

    @FindBy(css = "a.header_user-name")
    private WebElement userName;

    @FindBy(css = "li.drop-down-item:nth-child(3)")
    private WebElement logOut;


//    @FindBy(css = ".description__title")
//    private WebElement hello;


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


    public void switchLanguage(String lang) {
        languageSwitcher.click();
        WebElement option = (lang.equals("en")) ? enOption : uaOption;
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }


    @Order(1)
    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
        // assertThat(driver.getTitle(), is("GreenCity"));
    }


    @Order(6)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; weyt3$Guew^; Welcome back!; Please enter your details to sign in.; Email",
            "ua; samplestest@greencity.com; weyt3$Guew^; З поверненням!; Будь ласка, внесiть свої дані для входу.; Електронна пошта"
    })
    public void signIn(String lang, String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel) {
        switchLanguage(lang);
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


    @Order(2)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestesgreencity.com; Please check that your e-mail address is indicated correctly",
            "ua; samplestesgreencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти"
    })
    public void signInNotValid(String lang, String email, String message) {
        switchLanguage(lang);
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("somepassword");
        signInSubmitButton.click();
        Assertions.assertEquals(message, errorEmail.getText());
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; wrongpass123!; Bad email or password",
            "ua; samplestest@greencity.com; wrongpass123!; Введено невірний email або пароль"
    })
    public void signInWithInvalidPassword(String lang, String email, String password, String expectedError) {
        switchLanguage(lang);
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(generalError));
        Assertions.assertEquals(expectedError, generalError.getText());
    }

    @Order(4)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; ''; somepassword; Email is required",
            "ua; ''; somepassword; Введіть пошту"
    })
    public void signInWithEmptyEmail(String lang, String email, String password, String expectedError) {
        switchLanguage(lang);
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        Assertions.assertEquals(expectedError, errorEmail.getText());
    }

    @Order(5)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; ''; Password is required",
            "ua; samplestest@greencity.com; ''; Введіть пароль"
    })
    public void signInWithEmptyPassword(String lang, String email, String password, String expectedError) {
        switchLanguage(lang);
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        Assertions.assertEquals(expectedError, errorPassword.getText());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
