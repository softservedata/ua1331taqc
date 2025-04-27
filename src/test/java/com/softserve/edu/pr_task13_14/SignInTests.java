package com.softserve.edu.pr_task13_14;

import com.softserve.edu.pr_task12.SignInPageLocators;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.logging.Logger;


public class SignInTests extends TestRunner {

    private static final Logger logger = Logger.getLogger(SignInTests.class.getName());
    private BasePage basePage;

    @BeforeEach
    public void setUpTest() {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4205/#/greenCity");
        basePage = new BasePage(driver);
        basePage.switchToEn();
    }

    @Test
    public void verifyTitle() {
        logger.info("Start test: verifyTitle");
        assertEquals("GreenCity", driver.getTitle());
    }

    @DisplayName("Positive Login Tests")
    @ParameterizedTest
    @MethodSource("com.softserve.edu.pr_task13_14.SignInPositiveRepository#getData")
    public void signInPositive(SignInPositiveData data) {
        logger.info("Start test: signInPositive with email " + data.getEmail());
        basePage.clickElement(SignInPageLocators.SIGN_IN_BUTTON);
        assertThat(basePage.find(SignInPageLocators.WELCOME_TEXT).getText(), is("Welcome back!"));
        basePage.find(SignInPageLocators.EMAIL_INPUT).sendKeys(data.getEmail());
        basePage.find(SignInPageLocators.PASSWORD_INPUT).sendKeys(data.getPassword());
        basePage.clickElement(SignInPageLocators.SIGN_IN_SUBMIT_BUTTON);
    }
}
