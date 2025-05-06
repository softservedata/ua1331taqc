package com.softserve.homeworks.task13_14.pages;

import com.softserve.homeworks.task13_14.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SigninPage {
    private final static Logger logger = LoggerFactory.getLogger(SigninPage.class);

    private final String VALUE_ATTRIBUTE = "value";
    public final String EN_GENERAL_ERROR_TEXT = "Bad email or password";
    public final String EN_GENERAL_EMPTY_ERROR_TEXT = "Please fill all red fields"; //when both email and password fields are empty
    public final String EN_INVALID_EMAIL_ERROR_TEXT = "Please check that your e-mail address is indicated correctly";
    public final String EN_INVALID_PASSWORD_ERROR_TEXT = "Password must be at least 8 characters long without spaces";
    public final String EN_EMPTY_EMAIL_ERROR_TEXT = "Email is required";
    public final String EN_EMPTY_PASSWORD_ERROR_TEXT = "Password is required";

    protected WebDriver driver;
    //
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;
    private WebElement closeFormButton;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.id("email"));
        passwordField = driver.findElement(By.id("password"));
        signinButton = driver.findElement(By.cssSelector("button[type='submit']"));
        closeFormButton = driver.findElement(By.cssSelector("a.close-modal-window"));
    }

    // PageObject Atomic Operation

    // emailField
    public WebElement getEmailField() {
        return emailField; // Classic Page Object
    }

    public String getEmailFieldText() {
        return getEmailField().getDomProperty(VALUE_ATTRIBUTE);
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void sendKeysEmailField(String email) {
        getEmailField().sendKeys(email);
    }

    // passwordField
    public WebElement getPasswordField() {
        return passwordField; // Classic Page Object
    }

    public String getPasswordFieldText() {
        //return getPasswordField().getAttribute(VALUE_ATTRIBUTE);
        return getPasswordField().getDomProperty(VALUE_ATTRIBUTE);
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void sendKeysPasswordField(String password) {
        getPasswordField().sendKeys(password);
    }

    // signinButton
    public WebElement getSigninButton() {
        return signinButton; // Classic Page Object
    }

    public String getSigninButtonText() {
        return getSigninButton().getText().trim();
    }

    public void clickSigninButton() {
        getSigninButton().click();
    }

    // closeFormButton
    public WebElement getCloseFormButton() {
        return closeFormButton; // Classic Page Object
    }

    public void clickCloseFormButton() {
        getCloseFormButton().click();
    }

    // general error
    public WebElement getGeneralError() {
        return driver.findElement(By.cssSelector(".alert-general-error"));
    }

    public String getGeneralErrorText() {
        return getGeneralError().getText().trim();
    }

    // emailError
    public WebElement getEmailError() {
        return driver.findElement(By.cssSelector("div#email-err-msg > app-error > div"));
    }

    public String getEmailErrorText() {
        return getEmailError().getText().trim();
    }

    // passwordError
    public WebElement getPasswordError() {
        return driver.findElement(By.cssSelector("div#pass-err-msg > app-error > div"));
    }

    public String getPasswordErrorText() {
        return getPasswordError().getText().trim();
    }

    // PageObject Functional Operation

    private void typeEmail(String email) {
        clickEmailField();
        clearEmailField();
        sendKeysEmailField(email);
    }

    private void typePassword(String password) {
        clickPasswordField();
        clearPasswordField();
        sendKeysPasswordField(password);
    }

    // PageObject Business Operation

    public MyspacePage loginSuccessfully(User user) {
        logger.debug("Start loginSuccessfully( " + user + " )");
        //
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
        clickSigninButton();
        return new MyspacePage(driver);
    }

    public SigninPage loginNotSuccessfully(User user) {
        logger.debug("Start loginNotSuccessfully( " + user + " )");
        //
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
        clickSigninButton();
        return new SigninPage(driver);
    }

}