package homeworks.hwParametrTests;


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


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParametrizedTests {

    private static WebDriver driver;
    private WebDriverWait wait;


    @FindBy(css = ".lang-option")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//html/body/app-root/app-main/div/app-header/header/div[2]/div/div/div/ul/ul/li[2]")
    private WebElement enOption;

    @FindBy(xpath = "/html/body/app-root/app-main/div/app-header/header/div[2]/div/div/div/ul/ul/li[2]/span")
    private WebElement uaOption;

    @FindBy(css = ".add-shadow > li:nth-child(2) > span:nth-child(1)")
    private WebElement anotherLang;

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

    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;

    @FindBy(css = "#pass-err-msg > app-error:nth-child(1) > div:nth-child(1)")
    private WebElement errorPassword;

    @FindBy(css = "#email-err-msg > app-error:nth-child(1) > div:nth-child(1)")
    private WebElement errorEmail;

    @FindBy(css = ".name")
    private WebElement name;

    @FindBy(css = "a.header_user-name")
    private WebElement userName;

    @FindBy(css = "li.drop-down-item:nth-child(3)")
    private WebElement logOut;



    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    public void setUpEach() {
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        initPageElements();
    }


    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }


    public void switchLanguage(String lang) {
        wait.until(ExpectedConditions.elementToBeClickable(languageSwitcher)).click();
        WebElement option = (lang.equals("en")) ? enOption : uaOption;
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Order(1)
    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "samplestest@greencity.com| weyt3$Guew^| З поверненням!| Будь ласка, внесiть свої дані для входу.| Електронна пошта| Svitlana",
            "newuser1@greencity.com| NewPass1!| З поверненням!| Будь ласка, внесiть свої дані для входу.| Електронна пошта| Newuser1" ,
            "testuser2025@greencity.com| Test2025#| З поверненням!| Будь ласка, внесiть свої дані для входу.| Електронна пошта| Testuser2025",
            "greenfan3@greencity.com| GreenFan3$| З поверненням!| Будь ласка, внесiть свої дані для входу.| Електронна пошта| Greenfan3"
    })

    public void signInUkr(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel, String expectedName) {
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(welcomeText));

        assertThat(welcomeText.getText(), is(expectedWelcome));
        assertThat(signInDetailsText.getText(), is(expectedDetails));
        assertThat(emailLabel.getText(), is(expectedEmailLabel));


        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(name));
        assertThat(name.getText(), is(expectedName));

        userName.click();
        wait.until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "samplestest@greencity.com| weyt3$Guew^| Welcome back!| Please enter your details to sign in.| Email| Svitlana",
            "newuser1@greencity.com| NewPass1!| Welcome back!| Please enter your details to sign in.| Email| Newuser1",
            "testuser2025@greencity.com| Test2025#| Welcome back!| Please enter your details to sign in.| Email| Testuser2025",
            "greenfan3@greencity.com| GreenFan3$| Welcome back!| Please enter your details to sign in.| Email| Greenfan3"
    })

    public void signInEng(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel, String expectedName) throws InterruptedException {
        switchLanguage("en");

        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(welcomeText));

        assertThat(welcomeText.getText(), is(expectedWelcome));
        assertThat(signInDetailsText.getText(), is(expectedDetails));
        assertThat(emailLabel.getText(), is(expectedEmailLabel));


        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();

        wait.until(ExpectedConditions.visibilityOf(name));
        assertThat(name.getText(), is(expectedName));

        userName.click();
        wait.until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();

    }


    @Order(4)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; testuser2025greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "ua; samplestesgreencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "ua; newuser1greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "ua; greenfan3greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти"

    })
    public void signInNotValidUa(String lang, String email, String message) {
//    switchLanguage("ua");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("somepassword");
        signInSubmitButton.click();
        Assertions.assertEquals(message, errorEmail.getText());
    }
    @Order(5)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; testuser2025greencity.com; Please check that your e-mail address is indicated correctly",
            "en; samplestesgreencity.com; Please check that your e-mail address is indicated correctly",
            "en; newuser1greencity.com; Please check that your e-mail address is indicated correctly",
            "en; greenfan3greencity.com; Please check that your e-mail address is indicated correctly"

    })
    public void signInNotValidEn(String lang, String email, String message) {
        switchLanguage("en");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("somepassword");
        signInSubmitButton.click();
        Assertions.assertEquals(message, errorEmail.getText());
    }


    @Order(6)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; samplestest@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "ua; testuser2025@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "ua; newuser1@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "ua; greenfan3@greencity.com; wrongpass123!; Введено невірний email або пароль",


    })
    public void signInWithInvalidPasswordUa(String lang, String email, String password, String expectedError) {
//        switchLanguage("ua");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assertions.assertEquals(expectedError, errorMessage.getText());
    }
    @Order(7)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; wrongpass123!; Bad email or password",
            "en; testuser2025@greencity.com; wrongpass123!; Bad email or password",
            "en; newuser1@greencity.com; wrongpass123!; Bad email or password",
            "en; greenfan3@greencity.com; wrongpass123!; Bad email or password",

    })
    public void signInWithInvalidPasswordEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assertions.assertEquals(expectedError, errorMessage.getText());
    }

    @Order(8)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; ' '; somepassword; Введіть пошту",
            "ua; ' '; GreenFan3$; Введіть пошту",
            "ua; ' '; weyt3$Guew^; Введіть пошту",
            "ua; ' '; NewPass1!; Введіть пошту",


    })
    public void signInWithEmptyEmailUa(String lang, String email, String password, String expectedError) {
//        switchLanguage("ua");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        Assertions.assertEquals(expectedError, errorEmail.getText());
    }
    @Order(9)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; ' '; somepassword; Email is required",
            "en; ' '; GreenFan3$; Email is required",
            "en; ' '; weyt3$Guew^; Email is required",
            "en; ' '; NewPass1!; Email is required",


    })
    public void signInWithEmptyEmailEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        Assertions.assertEquals(expectedError, errorEmail.getText());
    }

    @Order(10)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; samplestest@greencity.com; ''; Введіть пароль",
            "ua; greenfan3@greencity.com; ''; Введіть пароль",
            "ua; newuser1@greencity.com; ''; Введіть пароль",
            "ua; testuser2025@greencity.com; ''; Введіть пароль",

    })
    public void signInWithEmptyPasswordUa(String lang, String email, String password, String expectedError) {
//        switchLanguage("ua");
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        Assertions.assertEquals(expectedError, errorPassword.getText());
    }
    @Order(11)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; ''; Password is required",
            "en; greenfan3@greencity.com; ''; Password is required",
            "en; newuser1@greencity.com; ''; Password is required",
            "en; testuser2025@greencity.com; ''; Password is required",

    })
    public void signInWithEmptyPasswordEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
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
