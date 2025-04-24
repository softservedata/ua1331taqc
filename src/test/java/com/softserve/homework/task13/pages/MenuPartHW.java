package com.softserve.homework.task13.pages;

import com.softserve.edu07fwk.pages.HeaderPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class MenuPartHW extends HeaderPartHW {

    private WebElement greencityLogo;
    private WebElement ecoNews;
    private WebElement events;
    private WebElement aboutus;
    private WebElement ubsCourier;
    //
    private WebElement signin;

    public MenuPartHW(WebDriver driver) {
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

    // signin
    public WebElement getSignin() {
        signin = driver.findElement(By.cssSelector("div.header_navigation-menu img[src*='/user.svg']"));
        return signin;
    }

    public void clickSignin() {
        getSignin().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation

    public AboutusPageHW gotoAboutusPage() {
        clickAboutus();
        return new AboutusPageHW(driver);
    }

    public HomeUbsPageHW gotoHomeUbsPage() {
        clickUbsCourier();
        return new HomeUbsPageHW(driver);
    }

    public SigninUbsPageHW gotoSigninPage() {
        clickSignin();
        return new SigninUbsPageHW(driver);
    }

}
