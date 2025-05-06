package homework.hw10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriverWait wait;
    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@alt='language switcher']")
    private WebElement languageSwitcher;

    @FindBy(xpath = "//li[@aria-label='Ua']")
    private WebElement uaSwitcher;

    @FindBy(xpath = "//li[@aria-label='En']")
    private WebElement englishSwitcher;

    @FindBy(xpath = "//img[@class='ubs-header-sing-in-img ubs-header-sing-in-img-greencity']")
    private WebElement signInButton;

    @FindBy(xpath = "//app-sign-in//h1")
    private WebElement welcomeText;

    @FindBy(xpath = "//app-sign-in//h2")
    private WebElement signInDetailsText;

    @FindBy(xpath = "//label[@for='email']")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInSubmitButton;

    @FindBy(css = ".mat-simple-snackbar span")
    private WebElement snackbarMessage;

    @FindBy(css = ".alert-general-error")
    private WebElement generalError;

    @FindBy(xpath = "//div[@id='pass-err-msg' or contains(@class, 'pass-err-msg')]")
    private WebElement errorPassword;

    @FindBy(xpath = "//div[@id='email-err-msg' or contains(@class, 'email-err-msg')]")
    private WebElement errorEmail;

    @FindBy(xpath = "//a[@class='forgot-password']")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = "//img[@alt='close button']")
    private WebElement closeButton;

    @FindBy(xpath = "//img[@alt='show-hide-password']")
    private WebElement showHidePasswordButton;

    @FindBy(xpath = "//a[@aria-label='sign up modal window']")
    private WebElement signUpModalWindow;

    @FindBy(xpath = "//button[@class='google-sign-in']")
    private WebElement googleSignInButton;

    @FindBy(xpath = "//span[@class='or-use-google']")
    private WebElement orUseGoogleButton;

    @FindBy(xpath = "//div[contains(@class, 'missing-account')]//p")
    private WebElement missingAccount;


    public void openSignInForm() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }


    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }


    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }


    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton)).click();
    }


    public String getWelcomeText() {
        wait.until(ExpectedConditions.visibilityOf(welcomeText));
        return welcomeText.getText();
    }


    public void switchToEnglish() throws InterruptedException {
        // Чекаємо, поки буде доступний елемент для вибору мови
        wait.until(ExpectedConditions.elementToBeClickable(languageSwitcher)).click();

        // Чекаємо, поки англійська мова стане доступною для вибору
        wait.until(ExpectedConditions.elementToBeClickable(englishSwitcher)).click();
    }


    public void switchToUkrainian() {
        wait.until(ExpectedConditions.elementToBeClickable(languageSwitcher)).click();
        wait.until(ExpectedConditions.visibilityOf(uaSwitcher)).click();
    }


    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(generalError));
        return generalError.getText();
    }


    public String getPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorPassword));
        return errorPassword.getText();
    }


    public String getEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorEmail));
        return errorEmail.getText();
    }

    public boolean isSignInButtonEnabled() {
        wait.until(ExpectedConditions.visibilityOf(signInSubmitButton));
        return signInSubmitButton.isEnabled();
    }

    public String getSnackbarMessage() {
        wait.until(ExpectedConditions.visibilityOf(snackbarMessage));
        return snackbarMessage.getText();
    }

}