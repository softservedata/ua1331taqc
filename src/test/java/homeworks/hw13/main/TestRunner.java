package homeworks.hw13.main;
import homeworks.hw13.pages.HeaderGreencity;
import homeworks.hw13.pages.SignInPage;
import homeworks.hw13.pages.UserPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class TestRunner {
    private static WebDriver driver;
    protected HeaderGreencity headerGreencity;
    protected SignInPage signInPage;
    protected UserPage userPage;
    protected static final String BASE_URL = "http://localhost:4205/#/greenCity";




    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void setupEach() {
        driver.get(BASE_URL);
        headerGreencity = new HeaderGreencity(driver);
        signInPage = new SignInPage(driver);
        userPage = new UserPage(driver);
    }
    @AfterEach
    public void refreshPage() {
        driver.navigate().refresh();
    }


    @AfterAll
    public static void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
