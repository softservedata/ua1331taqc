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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    private WebElement getElementByJs(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return document.querySelector(arguments[0]);", cssSelector);
    }

    private void switchLanguage(String lang) {
        getElementByJs(".lang-option").click();
        String selector = lang.equals("en") ?
                "/html/body/app-root/app-main/div/app-header/header/div[2]/div/div/div/ul/ul/li[2]/span" :
                "//html/body/app-root/app-main/div/app-header/header/div[2]/div/div/div/ul/ul/li[2]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector))).click();
    }

    @Order(1)
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
        getElementByJs(".header_sign-in-link").click();

        assertThat(waitForElement(By.cssSelector("app-sign-in h1")).getText(), is(expectedWelcome));
        assertThat(getElementByJs("app-sign-in h2").getText(), is(expectedDetails));
        assertThat(getElementByJs("label:nth-child(1)").getText(), is(expectedEmailLabel));

        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();

        assertThat(waitForElement(By.cssSelector(".name")).getText(), is(expectedName));
        getElementByJs("a.header_user-name").click();
        waitForElement(By.cssSelector("li.drop-down-item:nth-child(3)")).click();
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "samplestest@greencity.com|weyt3$Guew^|Welcome back!|Please enter your details to sign in.|Email|Svitlana",
            "newuser1@greencity.com|NewPass1!|Welcome back!|Please enter your details to sign in.|Email|Newuser1",
            "testuser2025@greencity.com|Test2025#|Welcome back!|Please enter your details to sign in.|Email|Testuser2025",
            "greenfan3@greencity.com|GreenFan3$|Welcome back!|Please enter your details to sign in.|Email|Greenfan3"
    })
    public void signInEng(String email, String password, String expectedWelcome, String expectedDetails, String expectedEmailLabel, String expectedName) {
        switchLanguage("en");
        getElementByJs(".header_sign-in-link").click();

        assertThat(waitForElement(By.cssSelector("app-sign-in h1")).getText(), is(expectedWelcome));
        assertThat(getElementByJs("app-sign-in h2").getText(), is(expectedDetails));
        assertThat(getElementByJs("label:nth-child(1)").getText(), is(expectedEmailLabel));

        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();

        assertThat(waitForElement(By.cssSelector(".name")).getText(), is(expectedName));
        getElementByJs("a.header_user-name").click();
        waitForElement(By.cssSelector("li.drop-down-item:nth-child(3)")).click();
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
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys("somepassword");
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(message, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
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
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys("somepassword");
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(message, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
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
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector(".alert-general-error")).getText());
    }

    @Order(7)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; samplestest@greencity.com; wrongpass123!; Bad email or password",
            "ua; testuser2025@greencity.com; wrongpass123!; Bad email or password",
            "ua; newuser1@greencity.com; wrongpass123!; Bad email or password",
            "ua; greenfan3@greencity.com; wrongpass123!; Bad email or password",
    })
    public void signInWithInvalidPasswordEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector(".alert-general-error")).getText());
    }

    @Order(8)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; ''; somepassword; Email is required",
            "en; ''; GreenFan3$; Email is required",
            "en; ''; weyt3$Guew^; Email is required",
            "en; ''; NewPass1!; Email is required",
    })
    public void signInWithEmptyEmailEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }

    @Order(9)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; ''; somepassword; Введіть пошту",
            "ua; ''; GreenFan3$; Введіть пошту",
            "ua; ''; weyt3$Guew^; Введіть пошту",
            "ua; ''; NewPass1!; Введіть пошту",
    })
    public void signInWithEmptyEmailUa(String lang, String email, String password, String expectedError) {
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#email-err-msg app-error div")).getText());
    }

    @Order(10)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; ''; Password is required",
            "en; greenfan3@greencity.com; ''; Password is required",
            "en; newuser1@greencity.com; ''; Password is required",
            "en; testuser2025@greencity.com; ''; Password is required",
    })
    public void signInWithEmptyPasswordEn(String lang, String email, String password, String expectedError) {
        switchLanguage("en");
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#pass-err-msg app-error div")).getText());
    }

    @Order(11)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "ua; samplestest@greencity.com; ''; Введіть пароль",
            "ua; greenfan3@greencity.com; ''; Введіть пароль",
            "ua; newuser1@greencity.com; ''; Введіть пароль",
            "ua; testuser2025@greencity.com; ''; Введіть пароль",
    })
    public void signInWithEmptyPasswordUa(String lang, String email, String password, String expectedError) {
        getElementByJs(".header_sign-in-link").click();
        getElementByJs("#email").sendKeys(email);
        getElementByJs("#password").sendKeys(password);
        getElementByJs(".greenStyle").click();
        Assertions.assertEquals(expectedError, waitForElement(By.cssSelector("#pass-err-msg app-error div")).getText());
    }

    @Order(12)
    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "en; samplestest@greencity.com; ''; Email is required",
            "en; greenfan3@greencity.com; ''; Email is required",
            "en; newuser1@greencity.com; ''; Email is required",
            "en; testuser2025@greencity.com; ''; Email is required",
    })

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @AfterEach
    public void refreshPage() {
        driver.navigate().refresh();
    }
}

