package com.softserve.edu.projectstructureles2.pageobj;

import com.softserve.edu.projectstructureles2.pageobj.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SigninPage {
    private final String VALUE_ATTRIBUTE = "value";
    private final String REMOVE_ATTRIBUTE = "document.querySelector('%s').removeAttribute('disabled')";

    protected WebDriver driver;
    //
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
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

    // PageObject Business Operation

    //public HomeGreencityPage login(String email, String password) {
   // public SigninPage login(String email, String password) {
    public SigninPage login(User user) {
        //typeEmail(email);
        typeEmail(user.getEmail());
        //typePassword(password);
        typePassword(user.getPassword());
     //   typeEmail(email);
   //     typePassword(password);
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
}
