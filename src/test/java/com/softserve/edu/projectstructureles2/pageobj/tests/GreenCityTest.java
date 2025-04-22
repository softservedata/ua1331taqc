package com.softserve.edu.projectstructureles2.pageobj.tests;
import org.junit.jupiter.api.Assertions;
import com.softserve.edu.projectstructureles2.pageobj.data.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.softserve.edu.projectstructureles2.pageobj.AboutusPage;
import com.softserve.edu.projectstructureles2.pageobj.SigninPage;
import com.softserve.edu.projectstructureles2.pageobj.tests.RunnerExtension;
import org.junit.jupiter.params.provider.MethodSource;

public class GreenCityTest extends TestRunner {

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
 //  @CsvSource({"cicada32073@mailshan.com, Qwerty_1"})
    @MethodSource("com.softserve.edu.projectstructureles2.pageobj.tests.TestParameters#provideUsers")
     public void checkSigninPages(User user) {
        SigninPage signinPage = loadApplication()
                .gotoHomeGreencityPage()
                .chooseEnglish()
                .gotoSigninPage()
                .login(user);
        //
      //   Assertions.assertEquals(email, signinPage.getEmailFieldText());
        Assertions.assertEquals(user.getEmail(), signinPage.getEmailFieldText());
        Assertions.assertEquals(user.getPassword(), signinPage.getEmailFieldText());
      //  Assertions.assertEquals(password, signinPage.getPasswordFieldText());
    }
}