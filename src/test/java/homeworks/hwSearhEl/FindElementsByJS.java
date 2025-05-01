package homeworks.hwSearhEl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FindElementsByJS {

    private static WebDriver driver;
    private WebDriverWait wait;

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
    }

    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement getElement(String sel) {
        String js = sel.startsWith("/")
                ? "var r=document.evaluate(arguments[0],document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null);return r.singleNodeValue;"
                : "return document.querySelector(arguments[0]);";
        return (WebElement) ((JavascriptExecutor) driver).executeScript(js, sel);
    }

    private void switchLanguage() {
        wait.until(ExpectedConditions.elementToBeClickable(getElement(".lang-option"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(getElement("//li[@aria-label='En']"))).click();
    }


    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "samplestest@greencity.com|weyt3$Guew^|З поверненням!|Будь ласка, внесiть свої дані для входу.|Електронна пошта|Svitlana",
            "newuser1@greencity.com|NewPass1!|З поверненням!|Будь ласка, внесiть свої дані для входу.|Електронна пошта|Newuser1",
            "testuser2025@greencity.com|Test2025#|З поверненням!|Будь ласка, внесiть свої дані для входу.|Електронна пошта|Testuser2025",
            "greenfan3@greencity.com|GreenFan3$|З поверненням!|Будь ласка, внесiть свої дані для входу.|Електронна пошта|Greenfan3"
    })
    public void signInUkr(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel, String expectedName) {
        getElement(".header_sign-in-link").click();

        assertThat(waitForElement(By.cssSelector("app-sign-in h1")).getText(), is(expectedWelcome));
        assertThat(getElement("app-sign-in h2").getText(), is(expectedDetails));
        assertThat(getElement("//label[@for='email']").getText(), is(expectedEmailLabel));

        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();

        assertThat(waitForElement(By.cssSelector(".name")).getText(), is(expectedName));
        getElement("a.header_user-name").click();
        waitForElement(By.cssSelector("li.drop-down-item:nth-child(3)")).click();
    }


    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "samplestest@greencity.com|weyt3$Guew^|Welcome back!|Please enter your details to sign in.|Email|Svitlana",
            "newuser1@greencity.com|NewPass1!|Welcome back!|Please enter your details to sign in.|Email|Newuser1",
            "testuser2025@greencity.com|Test2025#|Welcome back!|Please enter your details to sign in.|Email|Testuser2025",
            "greenfan3@greencity.com|GreenFan3$|Welcome back!|Please enter your details to sign in.|Email|Greenfan3"
    })
    public void signInEng(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel, String expectedName) {
        switchLanguage();
        getElement(".header_sign-in-link").click();

        assertThat(waitForElement(By.cssSelector("app-sign-in h1")).getText(), is(expectedWelcome));
        assertThat(getElement("app-sign-in h2").getText(), is(expectedDetails));
        assertThat(getElement("//label[@for='email']").getText(), is(expectedEmailLabel));

        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();

        assertThat(waitForElement(By.cssSelector(".name")).getText(), is(expectedName));
        getElement("a.header_user-name").click();
        waitForElement(By.cssSelector("li.drop-down-item:nth-child(3)")).click();
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "testuser2025greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "samplestesgreencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "newuser1greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти",
            "greenfan3greencity.com; Перевірте, чи правильно вказано вашу адресу електронної пошти"
    })
    public void signInNotValidUa(String email, String message) {
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys("somepassword");
        getElement(".greenStyle").click();
        Assertions.assertEquals(message, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "testuser2025greencity.com; Please check that your e-mail address is indicated correctly",
            "samplestesgreencity.com; Please check that your e-mail address is indicated correctly",
            "newuser1greencity.com; Please check that your e-mail address is indicated correctly",
            "greenfan3greencity.com; Please check that your e-mail address is indicated correctly"
    })
    public void signInNotValidEn(String email, String message) {
        switchLanguage();
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys("somepassword");
        getElement(".greenStyle").click();
        Assertions.assertEquals(message, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }



    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "testuser2025@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "newuser1@greencity.com; wrongpass123!; Введено невірний email або пароль",
            "greenfan3@greencity.com; wrongpass123!; Введено невірний email або пароль",
    })
    public void signInWithInvalidPasswordUa(String email, String password, String expectedError) {
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector(".alert-general-error")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; wrongpass123!; Bad email or password",
            "testuser2025@greencity.com; wrongpass123!; Bad email or password",
            "newuser1@greencity.com; wrongpass123!; Bad email or password",
            "greenfan3@greencity.com; wrongpass123!; Bad email or password",
    })
    public void signInWithInvalidPasswordEn(String email, String password, String expectedError) {
        switchLanguage();
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector(".alert-general-error")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "''; somepassword; Email is required",
            "''; GreenFan3$; Email is required",
            "''; weyt3$Guew^; Email is required",
            "''; NewPass1!; Email is required",
    })
    public void signInWithEmptyEmailEn(String email, String password, String expectedError) {
        switchLanguage();
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "''; somepassword; Введіть пошту",
            "''; GreenFan3$; Введіть пошту",
            "''; weyt3$Guew^; Введіть пошту",
            "''; NewPass1!; Введіть пошту",
    })
    public void signInWithEmptyEmailUa(String email, String password, String expectedError) {
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; ''; Password is required",
            "greenfan3@greencity.com; ''; Password is required",
            "newuser1@greencity.com; ''; Password is required",
            "testuser2025@greencity.com; ''; Password is required",
    })
    public void signInWithEmptyPasswordEn(String email, String password, String expectedError) {
        switchLanguage();
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#pass-err-msg app-error div")).getText());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "samplestest@greencity.com; ''; Введіть пароль",
            "greenfan3@greencity.com; ''; Введіть пароль",
            "newuser1@greencity.com; ''; Введіть пароль",
            "testuser2025@greencity.com; ''; Введіть пароль",
    })
    public void signInWithEmptyPasswordUa(String email, String password, String expectedError) {
        getElement(".header_sign-in-link").click();
        getElement("#email").sendKeys(email);
        getElement("#password").sendKeys(password);
        getElement(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#pass-err-msg app-error div")).getText());
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @AfterEach
    public void refreshPage() {
        driver.navigate().refresh();
    }
}

