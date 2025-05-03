package homeworks.hw13.main;


import homeworks.hw13.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;





public class TestSample6 extends TestRunner {
    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity", getDriver().getTitle());
    }


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


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidEmailUa")
    public void signInNotValidUa(SignInDataInvalidMail signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedError, signInPage.errorEmail());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidEmailsEn")
    public void signInNotValidEn(SignInDataInvalidMail signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedError, signInPage.errorEmail());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidPasswordUa")
    public void signInInvalidPasswordUa(SignInInvalidPassword signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorMessage());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#invalidPasswordEn")
    public void signInInvalidPasswordEn(SignInInvalidPassword signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorMessage());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyEmailUa")
    public void signInWithEmptyEmailUa(SignInEmptyEmail signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorEmail, signInPage.errorEmail());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyEmailEn")
    public void signInWithEmptyEmailEn(SignInEmptyEmail signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorEmail, signInPage.errorEmail());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyPasswordUa")
    public void signInWithEmptyPasswordUa(EmptyPassword signInData) {
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorPassword());
    }


    @ParameterizedTest
    @MethodSource("homeworks.hw13.repo.SignInDataRep#emptyPasswordEn")
    public void signInWithEmptyPasswordEn(EmptyPassword signInData) {
        headerGreencity.switchLanguage();
        headerGreencity.clickSignIn();
        signInPage.signInInput(signInData.email, signInData.password);
        signInPage.submit();
        Assertions.assertEquals(signInData.expectedErrorPass, signInPage.errorPassword());
    }



}