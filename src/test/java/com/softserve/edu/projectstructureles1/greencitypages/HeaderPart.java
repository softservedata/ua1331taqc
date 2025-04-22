package com.softserve.edu.projectstructureles1.greencitypages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class HeaderPart {

    protected WebDriver driver;
    //
    private WebElement ecoNews;
    private WebElement events;
    private WebElement aboutus;
    private WebElement ubsCourier;
    private WebElement signin;

    public HeaderPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        ecoNews = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']"));
        events = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/events']"));
        aboutus = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/about']"));
        ubsCourier = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/ubs']"));
        //signin = driver.findElement(By.cssSelector("div.header_navigation-menu img[src*='/user.svg']"));
    }

    // PageObject Atomic Operation

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
        return ecoNews; // Classic Page Object
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
        return ecoNews; // Classic Page Object
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
        return ecoNews; // Classic Page Object
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

    public AboutusPage gotoAboutusPage() {
        clickAboutus();
        return new AboutusPage(driver);
    }

    public HomeUbsPage gotoHomeUbsPage() {
        clickUbsCourier();
        return new HomeUbsPage(driver);
    }
}
