package com.softserve.edu.pr_task12;

import org.openqa.selenium.By;

public class SignInPageLocators {
    public static final By LANG_OPTION = By.cssSelector(".lang-option");
    public static final By EN_LANG_OPTION = By.xpath("//li[@role='menuitem' and @aria-label='En']");
    public static final By SIGN_IN_BUTTON = By.cssSelector(".ubs-header-sing-in-img");
    public static final By WELCOME_TEXT = By.cssSelector("app-sign-in h1");
    public static final By SIGN_IN_DETAILS_TEXT = By.cssSelector("app-sign-in h2");
    public static final By EMAIL_LABEL = By.cssSelector("label[for='email']");
    public static final By EMAIL_INPUT = By.id("email");
    public static final By PASSWORD_LABEL = By.cssSelector("label[for='password']");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By SIGN_IN_SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    public static final By ERROR_MESSAGE = By.cssSelector(".alert-general-error");
    public static final By ERROR_PASSWORD = By.xpath("//div[contains(@class, 'alert-general-error')]");
    public static final By ERROR_EMAIL = By.cssSelector("#email-err-msg div");
    public static final By PASSWORD_REQUIRED_ERROR = By.cssSelector("#pass-err-msg div");
    public static final By EMAIL_REQUIRED_ERROR = By.cssSelector("#email-err-msg div");
    public static final By SHOW_HIDE_PASSWORD = By.cssSelector(".image-show-hide-password");
    public static final By GOOGLE_TEXT_SIGN_IN = By.cssSelector(".google-text-sign-in");
    public static final By CLOSE_BUTTON = By.cssSelector(".cross-btn");
    public static final By MISSING_ACCOUNT = By.cssSelector("div.missing-account p");
    public static final By SIGN_UP_LINK = By.cssSelector("a.green-link");
}