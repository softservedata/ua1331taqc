package com.softserve.edu07fwk.tests;

import com.softserve.edu07fwk.pages.AboutusPage;
import com.softserve.edu07fwk.pages.SigninPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GreencityTest extends TestRunner {

    @Test
    public void checkAboutPages() throws InterruptedException {
        AboutusPage aboutusPage = loadApplication()
                .gotoHomeGreencityPage()
                .chooseEnglish()
                .gotoAboutusPage();
        //
        Assertions.assertEquals(AboutusPage.ABOUT_US, aboutusPage.getSectionHeaderText());
    }

    @ParameterizedTest
    @CsvSource({"cicada32073@mailshan.com, Qwerty_1"})
    public void checkSigninPages(String email, String password) {
        SigninPage signinPage = loadApplication()
                .gotoHomeGreencityPage()
                .chooseEnglish()
                .gotoSigninPage()
                .login(email, password);
        //
        Assertions.assertEquals(email, signinPage.getEmailFieldText());
        Assertions.assertEquals(password, signinPage.getPasswordFieldText());
    }
}
