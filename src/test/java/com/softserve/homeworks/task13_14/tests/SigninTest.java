package com.softserve.homeworks.task13_14.tests;

import com.softserve.homeworks.task13_14.pages.HomeGreencityPage;
import com.softserve.homeworks.task13_14.pages.MyspacePage;
import com.softserve.homeworks.task13_14.pages.SigninPage;
import com.softserve.homeworks.task13_14.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SigninTest extends TestRunner {

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersValid")
    public void checkSigninValid(User user) {
        logger.info("Start checkSigninValid( " + user + " )");

        MyspacePage myspacePage = openSigninPage()
                .loginSuccessfully(user);

        Assertions.assertEquals(user.getUsername(), myspacePage.getUserNameText());
        HomeGreencityPage homeGreencityPage = myspacePage.gotoSignOut();
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersWrongCredentials")
    public void checkSigninWrongCredentials(User user) {
        logger.info("Start checkSigninWrongCredentials( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_GENERAL_ERROR_TEXT, signinPage.getGeneralErrorText());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersEmptyCredentials")
    public void checkSigninEmptyCredentials(User user) {
        logger.info("Start checkSigninEmptyCredentials( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_GENERAL_EMPTY_ERROR_TEXT, signinPage.getGeneralErrorText());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersInvalidEmail")
    public void checkSigninInvalidEmail(User user) {
        logger.info("Start checkSigninInvalidEmail( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_INVALID_EMAIL_ERROR_TEXT, signinPage.getEmailErrorText());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersInvalidPassword")
    public void checkSigninInvalidPassword(User user) {
        logger.info("Start checkSigninInvalidPassword( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_INVALID_PASSWORD_ERROR_TEXT, signinPage.getPasswordErrorText());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersEmptyEmail")
    public void checkSigninEmptyEmail(User user) {
        logger.info("Start checkSigninEmptyEmail( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_EMPTY_EMAIL_ERROR_TEXT, signinPage.getEmailErrorText());
    }

    @ParameterizedTest
    @MethodSource("com.softserve.homeworks.task13_14.tests.TestParameters#provideUsersEmptyPassword")
    public void checkSigninEmptyPassword(User user) {
        logger.info("Start checkSigninEmptyPassword( " + user + " )");

        SigninPage signinPage = openSigninPage()
                .loginNotSuccessfully(user);

        Assertions.assertEquals(signinPage.EN_EMPTY_PASSWORD_ERROR_TEXT, signinPage.getPasswordErrorText());
    }


}
