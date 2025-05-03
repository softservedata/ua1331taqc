package homeworks.hw13.main;


import homeworks.hw13.data.*;
import homeworks.hw13.pages.HeaderGreencity;
import homeworks.hw13.pages.SignInPage;
import homeworks.hw13.pages.UserPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;




public class TestSample6 extends TestRunner {
    private WebDriver driver;
    private HeaderGreencity headerGreencity;
    private SignInPage signInPage;
    private UserPage userPage;

    @BeforeEach
    public void setupEach() {
        driver = TestRunner.getDriver();
        driver.get("http://localhost:4205/#/greenCity");
        headerGreencity = new HeaderGreencity(driver);
        signInPage = new SignInPage(driver);
        userPage = new UserPage(driver);

    }

    @Order(1)
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#uaUsers")
    public void signInUA(SignInData signInData) {
        headerGreencity.clickSignIn();

        Assertions.assertEquals(signInData.expectedWelcome, signInPage.welcomeText());
        Assertions.assertEquals(signInData.expectedDetails, signInPage.signInDetailsText());
        Assertions.assertEquals(signInData.expectedLabel, signInPage.emailLabel());

        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();

        Assertions.assertEquals(signInData.expectedName, userPage.getUserName());
        userPage.logOut();
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#enUsers")
    public void signInEN(SignInData signInData) {

        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();

        Assertions.assertEquals(signInData.expectedWelcome, signInPage.welcomeText());
        Assertions.assertEquals(signInData.expectedDetails, signInPage.signInDetailsText());
        Assertions.assertEquals(signInData.expectedLabel, signInPage.emailLabel());

        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();

        Assertions.assertEquals(signInData.expectedName, userPage.getUserName());
        userPage.logOut();
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidEmailUa")
    public void signInNotValidUa(SignInDataInvalidMail signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedError, signInPage.errorEmail());
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidEmailsEn")
    public void signInNotValidEn(SignInDataInvalidMail signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedError, signInPage.errorEmail());
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidPasswordUa")
    public void signInInvalidPasswordUa(SignInInvalidPassword signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorMessage());
    }

    @Order(7)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidPasswordEn")
    public void signInInvalidPasswordEn(SignInInvalidPassword signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorMessage());
    }

    @Order(8)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyEmailUa")
    public void signInWithEmptyEmailUa(SignInEmptyEmail signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorEmail, signInPage.errorEmail());
    }

    @Order(9)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyEmailEn")
    public void signInWithEmptyEmailEn(SignInEmptyEmail signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorEmail, signInPage.errorEmail());
    }

    @Order(10)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyPasswordUa")
    public void signInWithEmptyPasswordUa(EmptyPassword signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorPassword());
    }

    @Order(11)
    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyPasswordEn")
    public void signInWithEmptyPasswordEn(EmptyPassword SignInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(SignInData.email, SignInData.password);
        signInPage.submit();
        Assertions.assertEquals(SignInData.expectedErrorPass, signInPage.errorPassword());
    }

    @AfterEach
    public void refreshPage() {
        driver.navigate().refresh();
    }
}