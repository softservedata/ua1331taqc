package com.softserve.edu07fwk.pages;

import com.softserve.edu07fwk.api.GuestActions;
import com.softserve.edu07fwk.api.SigninResponse;
import com.softserve.edu07fwk.data.User;
import com.softserve.edu07fwk.tools.WebDriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public final class SigninPage {
    private final String VALUE_ATTRIBUTE = "value";
    private final String REMOVE_ATTRIBUTE = "document.querySelector('%s').removeAttribute('disabled')";

    private static final Logger logger = LoggerFactory.getLogger(SigninPage.class);
    protected GuestActions guestActions;
    protected WebDriver driver;
    //
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
        guestActions = new GuestActions();
        closePopup();
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.id("email"));
        passwordField = driver.findElement(By.id("password"));
        signinButton = driver.findElement(By.cssSelector("button[type='submit']"));
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

    private void activateSigninButton() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(String.format(REMOVE_ATTRIBUTE, "button[type=\"submit\"]"));
        //getSigninButton()
    }

    private void closePopup() {
        WebDriverWrapper.setImplicitlyWait(2);
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if (iframe.size() > 0) {
            driver.switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            driver.switchTo().defaultContent();
            //
            WebDriverWrapper.setImplicitlyWait(0);
            (new WebDriverWait(WebDriverWrapper.getDriver(), Duration.ofSeconds(10))).until(
                    ExpectedConditions.stalenessOf(iframe.get(0)));
        }
        WebDriverWrapper.restoreImplicitlyWait();
    }

    private void updateLocalStorage(SigninResponse signinResponse) {

    }

    // PageObject Business Operation

    //public HomeGreencityPage login(String email, String password) {
    //public SigninPage login(String email, String password) {
    public SigninPage login(User user) {
        logger.debug("start login(), user = " + user);
        //
        //typeEmail(email);
        typeEmail(user.getEmail());
        //typePassword(password);
        typePassword(user.getPassword());
        activateSigninButton();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickSigninButton();
        return new SigninPage(driver);
        //return new HomeGreencityPage(driver);
    }

    public HomeGreencityPage signin(User user) {
        logger.debug("start signin(), user = " + user);
        //
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
        activateSigninButton();
        //
        //clickSigninButton();
        SigninResponse signinResponse = guestActions.signin(user);
        updateLocalStorage(signinResponse);
        // CloseSigninPage
        // Refresh
        return new HomeGreencityPage(driver);
    }
}
