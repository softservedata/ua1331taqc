package homework.hw10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SignInTest {
    private static WebDriver driver;
    private SignInPage signInPage;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:4205/#/greenCity");
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void initPageAndSetLanguage() throws InterruptedException {
        signInPage = new SignInPage(driver);
        signInPage.switchToEnglish(); // Потім перемикаємо мову
        signInPage.openSignInForm(); // Спочатку відкриваємо форму
    }


    @ParameterizedTest
    @CsvSource({
            "bivove2365@hikuhu.com, Adidas100!"
    })
    public void testLogin(String email, String password) throws InterruptedException {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);

        Assertions.assertTrue(signInPage.isSignInButtonEnabled(), "Sign In button should be enabled when fields are filled");
    }

    @Test
    public void testLoginWithWrongPassword() {
        signInPage.enterEmail("bivove2365@hikuhu.com");
        signInPage.enterPassword("fewfweffw");
        signInPage.submitLogin();

        Assertions.assertTrue(signInPage.getErrorMessage().contains("Bad email or password"));
    }

    @Test
    public void testLoginWithInvalidEmail() {
        signInPage.enterEmail("wrongemail.com");
        signInPage.enterPassword("AnyPassword123");
        signInPage.submitLogin();

        Assertions.assertEquals("Please check that your e-mail address is indicated correctly",
                signInPage.getEmailErrorMessage());
    }


    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}