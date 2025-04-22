package com.softserve.edu.paramtstshomework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParamHomeWorkTest {
    private static WebDriver driver;
    // private static final String BASE_URL = "https://www.greencity.cx.ua/#/greenCity";
    private static final String BASE_URL = "http://localhost:4205/#/ubs";
    private static final long IMPLICITLY_WAIT_SECONDS = 10L;
    private static final long ONE_SECOND_DELAY = 1000;

    @FindBy(xpath = "//app-ubs/app-header//div[@class='container-fluid']//ul/img")
    private WebElement signInButton;
    @FindBy(css = "app-sign-in h1")
    private WebElement welcomeText;
    @FindBy(css = "app-sign-in h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for='email']")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "button.ubsStyle")
    private WebElement signInSubmitButton;
    @FindBy(css=".mat-simple-snackbar > span")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(css = "form div.alert-general-error.ng-star-inserted")
    //  @FindBy(xpath = "//*[@id='pass-err-msg']/app-error/div")
    private WebElement errorPassword;
    // @FindBy(xpath = "//*[@id='email-err-msg']/app-error/div")
    @FindBy(css = "div#email-err-msg app-error div")
    private WebElement errorEmail;
    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1264, 798));
    }
    @BeforeEach
    public void initPageElements() {
        driver.get(BASE_URL);
        PageFactory.initElements(driver, this);
        presentationSleep(4);
    }
    @Test
    public void verifyTitle() {
        assertEquals("GreenCity", driver.getTitle());
        // assertThat(driver.getTitle(), is("GreenCity"));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
  //  @CsvFileSource(resources = "/emailpassword.csv", numLinesToSkip = 2)
    public void signIn(String email, String password) {
        signInButton.click();
        presentationSleep(4);
        assertEquals("З поверненням!", welcomeText.getText());
        assertEquals("Будь ласка, внесiть свої дані для входу.", signInDetailsText.getText());
        assertEquals("Електронна пошта", emailLabel.getText());
        emailInput.sendKeys("test01@gmail.com");
        assertEquals(emailInput.getDomProperty("value"), email);
        passwordInput.sendKeys("MyTest2025!");
        assertEquals(passwordInput.getDomProperty("value"), password);
        signInSubmitButton.click();
        presentationSleep(15);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/emailpassword.csv", numLinesToSkip = 1)
    public void signInNotSuccessful(String email, String password) {
        signInButton.click();
        presentationSleep(4);
        assertEquals("З поверненням!", welcomeText.getText());
        assertEquals("Будь ласка, внесiть свої дані для входу.", signInDetailsText.getText());
        assertEquals("Електронна пошта", emailLabel.getText());
        emailInput.sendKeys("test0@gmail.com");
        assertNotEquals(emailInput.getDomProperty("value"), email);
        passwordInput.sendKeys("MyTest2025");
        assertNotEquals(passwordInput.getDomProperty("value"), password);
        signInSubmitButton.click();
        presentationSleep(15);
    }
    @ParameterizedTest
    @CsvSource({
            "Перевірте коректність введеної електронної адреси"
    })
    public void signInInvalidEmail(String message) {
        presentationSleep(4);
        signInButton.click();
        emailInput.sendKeys("test0@gmail.com");
        passwordInput.sendKeys("MyTest2025!");
        assertEquals(errorEmail.getText(), message);
    }

    @ParameterizedTest
    @CsvSource({
            " Введено невірний email або пароль "
    })
    public void signInInvalidPassword(String message) {
        presentationSleep(4);
        signInButton.click();
        emailInput.sendKeys("test01@gmail.com");
        passwordInput.sendKeys("MyTest2025");
        assertEquals(errorEmail.getText(), message);
    }


    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit(); // close()
        }
        System.out.println("@AfterAll executed");
    }
}