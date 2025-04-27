package com.softserve.edu.edu07homework.tests;
import com.softserve.edu.edu07homework.data.User;
import com.softserve.edu.edu07homework.pages.HomeGreencityPage;
import com.softserve.edu.edu07homework.pages.SigninPage;
import com.softserve.edu.edu07homework.pages.UserCabinetPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.softserve.edu.edu07homework.pages.SigninPage.ERROR_MESSAGE;
import static com.softserve.edu.edu07homework.pages.SigninPage.ERROR_MESSAGE2;
import static org.junit.jupiter.api.Assertions.*;

public class SigninTest extends TestRunner {

    public SigninPage gotoSigninPage(User user){
        SigninPage signinPage = loadHomeGreencityPage()
                .chooseUkrainian()
                .gotoSigninPage();
        signinPage.clickSigninButton();
        signinPage.login(user);
        signinPage.clickSigninButton();
        return signinPage;
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.edu07homework.tests.TestParameters#provideUsers")
    public void signIn(User user) {
        logger.info("signIn() method was started");
        SigninPage signinPage = loadHomeGreencityPage()
                .chooseUkrainian()
                .gotoSigninPage();

        assertEquals(signinPage.WELCOMEBACK_TEXT, signinPage.getWelcomeTextString());
        assertEquals(signinPage.EMAILFIELD_LABEL, signinPage.getEmailLabel().getText());

        UserCabinetPage userCabinetPage = signinPage.loginPersonalCabinet(user);
        assertEquals(userCabinetPage.USER_NAME, userCabinetPage.getUserNameText());
        assertTrue(userCabinetPage.checkUserCalendarVisible());
    }


    @ParameterizedTest
    @MethodSource("com.softserve.edu.edu07homework.tests.TestParameters#provideUserNonvalid")
    public void signInNotSuccessful(User user) {
        logger.info("signInNotSuccessful() method was started" + user );
        SigninPage signinPage = loadHomeGreencityPage()
                .chooseUkrainian()
                .gotoSigninPage();

        assertEquals(signinPage.WELCOMEBACK_TEXT, signinPage.getWelcomeTextString());
        assertEquals(signinPage.EMAILFIELD_LABEL, signinPage.getEmailLabel().getText());

        signinPage = signinPage.login(user);
        signinPage = signinPage.clickSigninButtonNonSuccessfull();

        assertTrue(signinPage.getErrorEmailVisible());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.edu07homework.tests.TestParameters#provideUserNonvalidEmail")
    public void signInInvalidEmail(User user) {
        logger.info("signInInvalidEmail() method was started" + user );
        SigninPage signinPage = gotoSigninPage(user);
        assertEquals(signinPage.getErrorEmailText(), ERROR_MESSAGE2);

    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu.edu07homework.tests.TestParameters#provideUserNonvalidPassword")
    public void signInInvalidPassword(User user) {
        logger.info("signInInvalidPassword() method was started" + user );
        SigninPage signinPage = gotoSigninPage(user);
        assertEquals(signinPage.getErrorEmailText(), ERROR_MESSAGE2);

    }
}
