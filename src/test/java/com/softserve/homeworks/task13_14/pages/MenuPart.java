package com.softserve.homeworks.task13_14.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class MenuPart extends HeaderPart {

    private WebElement greencityLogo;
    private WebElement ecoNews;
    private WebElement events;
    private WebElement aboutus;
    private WebElement ubsCourier;
    private WebElement loggedUserIcon;

    public MenuPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        greencityLogo = driver.findElement(By.cssSelector("a.header_logo img"));
        ecoNews = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']"));
        events = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/events']"));
        aboutus = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/about']"));
        ubsCourier = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='#']"));
        //signin = driver.findElement(By.cssSelector("div.header_navigation-menu img[src*='/user.svg']"));
    }

    // PageObject Atomic Operation

    // greencityLogo
    public WebElement getGreencityLogo() {
        return greencityLogo; // Classic Page Object
    }

    public void  clickGreencityLogo() {
        getGreencityLogo().click();
    }

    // ecoNews
    public WebElement getEcoNews() {
        return ecoNews; // Classic Page Object
        //return driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']")); // Lazy initialization
    }

    public String  getEcoNewsText() {
        return getEcoNews().getText().trim();
    }

    public void  clickEcoNews() {
        getEcoNews().click();
    }

    // events
    public WebElement getEvents() {
        return events; // Classic Page Object
        //return driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']")); // Lazy initialization
    }

    public String getEventsText() {
        return getEvents().getText().trim();
    }

    public void clickEvents() {
        getEvents().click();
    }

    // aboutus
    public WebElement getAboutus() {
        return aboutus; // Classic Page Object
        //return driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']")); // Lazy initialization
    }

    public String getAboutusText() {
        return getAboutus().getText().trim();
    }

    public void clickAboutus() {
        getAboutus().click();
    }

    // ubsCourier
    public WebElement getUbsCourier() {
        return ubsCourier; // Classic Page Object
        //return driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']")); // Lazy initialization
    }

    public String getUbsCourierText() {
        return getUbsCourier().getText().trim();
    }

    public void clickUbsCourier() {
        getUbsCourier().click();
    }

    // signin
    public WebElement getSignin() {
        //
        return driver.findElement(By.cssSelector(".header_sign-in-link"));
    }

    public void clickSignin() {
        getSignin().click();
    }

    // signOut
    private WebElement getSignOut() {
        return driver.findElement(By.cssSelector("ul#header_user-wrp li[aria-label='sign-out'] > a"));
    }

    private String getSignOutText() {
        return getSignOut().getText().trim();
    }

    private void clickSignOut() {
        getSignOut().click();
    }

    // loggedUserIcon
    private WebElement getLoggedUserIcon() {
        loggedUserIcon = driver.findElement(By.cssSelector("ul#header_user-wrp a.header_user-name"));
        return loggedUserIcon;
    }

    private void clickLoggedUserIcon() {
        getLoggedUserIcon().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation

    public SigninPage gotoSigninPage() {
        logger.debug("Start gotoSigninPage()");
        //
        clickSignin();
        return new SigninPage(driver);
    }

    // signOut
    public HomeGreencityPage gotoSignOut() {
        logger.debug("Start gotoSignOut()");
        //clickGreencityLogo();
        clickLoggedUserIcon();
        clickSignOut();
        return new HomeGreencityPage(driver);
    }
}