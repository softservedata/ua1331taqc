package com.softserve.edu07fwk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class MenuPart extends HeaderPart {
    private final String SIGN_OUT_XPATH = "//ul[@class='dropdown-list drop-down-item']//a[contains(text(), 'Sign out')]";

    private WebElement greencityLogo;
    private WebElement ecoNews;
    private WebElement events;
    private WebElement aboutus;
    private WebElement ubsCourier;
    //
    private WebElement userName;
    private WebElement signin;

    public MenuPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        greencityLogo = driver.findElement(By.cssSelector("a.header_logo img"));
        ecoNews = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']"));
        events = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/events']"));
        aboutus = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/about']"));
        ubsCourier = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/ubs']"));
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

    // userName
    public WebElement getUserName() {
        userName = driver.findElement(By.cssSelector("li.user-name"));
        return userName;
    }

    public String getUserNameText() {
        return getUserName().getText().trim();
    }

    public void clickUserName() {
        getUserName().click();
    }

    // signin
    public WebElement getSignin() {
        signin = driver.findElement(By.cssSelector("div.header_navigation-menu img[src*='/user.svg']"));
        return signin;
    }

    public void clickSignin() {
        getSignin().click();
    }

    // signOut
    private WebElement getSignOut() {
        return driver.findElement(By.xpath(SIGN_OUT_XPATH));
    }

    private String getSignOutText() {
        return getSignOut().getText().trim();
    }

    private void clickSignOut() {
        getSignOut().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation

    public AboutusPage gotoAboutusPage() {
        clickAboutus();
        return new AboutusPage(driver);
    }

    public HomeUbsPage gotoHomeUbsPage() {
        clickUbsCourier();
        return new HomeUbsPage(driver);
    }

    public SigninPage gotoSigninPage() {
        logger.debug("start gotoSigninPage()");
        //
        clickSignin();
        return new SigninPage(driver);
    }

    // signOut
    public HomeGreencityPage gotoSignOut() {
        clickGreencityLogo();
        clickUserName();
        clickSignOut();
        return new HomeGreencityPage(driver);
    }
}
