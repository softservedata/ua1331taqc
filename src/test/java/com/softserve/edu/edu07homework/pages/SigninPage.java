package com.softserve.edu.edu07homework.pages;

import com.softserve.edu.edu07homework.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SigninPage {
    private final String VALUE_ATTRIBUTE = "value";
    private final String REMOVE_ATTRIBUTE = "document.querySelector('%s').removeAttribute('disabled')";
    public static final String WELCOMEBACK_TEXT = "З поверненням!";
    public static final String EMAILFIELD_LABEL = "Електронна пошта";
    public static final String ERROR_MESSAGE = "Введено невірний email або пароль\n";
    public static final String ERROR_MESSAGE2 = "Введено невірний email або пароль";
    private static final Logger logger = LoggerFactory.getLogger(SigninPage.class);

    protected WebDriver driver;
    //
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;
    private WebElement welcomeText;
    private WebElement emailLabel;
    private WebElement errorEmail;

    public SigninPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        emailField = driver.findElement(By.id("email"));
        passwordField = driver.findElement(By.id("password"));
        signinButton = driver.findElement(By.cssSelector("button[type='submit']"));
        welcomeText = driver.findElement(By.cssSelector("app-sign-in h1"));
        emailLabel = driver.findElement(By.cssSelector("label[for='email']"));
    }

    // PageObject Atomic Operation

    // welcomeText
    public WebElement getWelcomeText() {
        return welcomeText; // Classic Page Object
    }

    public String getWelcomeTextString() {
        return getWelcomeText().getText();
    }

    // emailLabel
    public WebElement getEmailLabel() {
        return emailLabel; // Classic Page Object
    }

    public String getEmailLabelText() {
        return getEmailLabel().getText();
    }

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

    // errorEmail
    public WebElement getErrorEmail() {
        return driver.findElement(By.xpath("//div[contains(@class, 'alert-general-error')]")); // Classic Page Object
    }

    public String getErrorEmailText() {
        return getErrorEmail().getText();
        // return driver.findElement(By.xpath("//div[contains(@class, 'alert-general-error')]")).getText();
    }

    public boolean getErrorEmailVisible() {
    //    return driver.findElement(By.xpath("//div[contains(@class, 'alert-general-error')]")).isDisplayed();
        return getErrorEmail().isDisplayed();
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
    public void loginNoClick(User user) {
        logger.debug("start loginNoClick(), user = " + user);
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
      //  clickSigninButton();
      //  return new SigninPage(driver);
        //return new HomeGreencityPage(driver);
    }

    public UserCabinetPage loginPersonalCabinet(User user) {
        logger.debug("start loginPersonalCabinet(), user = " + user);
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
        return new UserCabinetPage(driver);
        //return new HomeGreencityPage(driver);
    }

       public HomeGreencityPage clickSigninButtonSuccessfull(){
           clickSigninButton();
           return new HomeGreencityPage(driver);
       }

    public SigninPage clickSigninButtonNonSuccessfull(){
        clickSigninButton();
        return new SigninPage(driver);
    }


}
