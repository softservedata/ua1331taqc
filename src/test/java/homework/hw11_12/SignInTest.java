package homework.hw11_12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SignInTest {
    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeAll
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1264, 798));
        driver.get("http://localhost:4205/#/greenCity");
        signInPage = new SignInPage(driver);
        signInPage.switchToEnglish(); // Лише один раз
    }

    @BeforeEach
    public void initPage() {
        signInPage.openSignInForm(); // Перед кожним тестом тільки форма
    }


    @ParameterizedTest
    @CsvSource({
            "user1@example.com, Password123!",
            "user2@domain.com, AnotherPass1@",
            "test.account@site.org, SecureP@ss9"
    })
    public void testValidLoginInputs(String email, String password) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.submitLogin();
        assertTrue(signInPage.isSignInButtonEnabled(), "Sign In button should be enabled when fields are filled");
    }

    @ParameterizedTest
    @CsvSource({
            "bivove2365@hikuhu.com, WrongPass1!, Bad email or password",
            "validuser@example.com, 12345678, Bad email or password",
            "test.email@domain.com, qwerty3f, Bad email or password"
    })
    public void testLoginWithWrongPassword(String email, String password, String expectedError) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.submitLogin();
        Assertions.assertTrue(signInPage.getErrorMessage().contains(expectedError),
                "Expected error message to contain: " + expectedError);
    }


    @ParameterizedTest
    @CsvSource({
            "wrongemail.com, AnyPassword123, Please check that your e-mail address is indicated correctly",
            "noatsignemail, Pass123!, Please check that your e-mail address is indicated correctly",
            "@missingusername.com, P@ssword, Please check that your e-mail address is indicated correctly"
    })
    public void testLoginWithInvalidEmail(String email, String password, String expectedError) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.submitLogin();
        Assertions.assertEquals(expectedError, signInPage.getEmailErrorMessage());
    }


    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
