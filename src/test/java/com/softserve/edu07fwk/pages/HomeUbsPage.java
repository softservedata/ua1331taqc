package com.softserve.edu07fwk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeUbsPage {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;
    //
    private WebElement aboutService;
    private WebElement sortingRules;
    private WebElement ecoShop;
    private WebElement greenCity;
    private WebElement signin;
    private WebElement order;

    public HomeUbsPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        aboutService = driver.findElement(By.cssSelector("ul a.pick-up-service"));
        sortingRules = driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/sort-station']"));
        ecoShop = driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/shop']"));
        greenCity = driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/greenCity']"));
        order = driver.findElement(By.cssSelector("div.main-content > button"));
    }

    // PageObject Atomic Operation

    // aboutService;
    public WebElement getAboutService() {
        return aboutService; // Classic Page Object
    }

    public String  getAboutServiceText() {
        return getAboutService().getText().trim();
    }

    public void  clickAboutService() {
        getAboutService().click();
    }

    // sortingRules;
    public WebElement getSortingRules() {
        return sortingRules; // Classic Page Object
    }

    public String  getSortingRulesText() {
        return getSortingRules().getText().trim();
    }

    public void  clickSortingRules() {
        getSortingRules().click();
    }

    // ecoShop;
    public WebElement getEcoShop() {
        return ecoShop; // Classic Page Object
    }

    public String  getEcoShopText() {
        return getEcoShop().getText().trim();
    }

    public void  clickEcoShop() {
        getEcoShop().click();
    }

    // greenCity;
    public WebElement getGreenCity() {
        return greenCity; // Classic Page Object
    }

    public String  getGreenCityText() {
        return getGreenCity().getText().trim();
    }

    public void  clickGreenCity() {
        getGreenCity().click();
    }

    // signin;
    public WebElement getSignin() {
        signin = driver.findElement(By.cssSelector("img.ubs-header-sing-in-img"));
        return signin;
    }

    public void clickSignin() {
        getSignin().click();
    }

    // order
    public WebElement getOrder() {
        return order; // Classic Page Object
    }

    public String  getOrderText() {
        return getOrder().getText().trim();
    }

    public void  clickOrder() {
        getOrder().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation

    public HomeGreencityPage gotoHomeGreencityPage() {
        logger.debug("start gotoHomeGreencityPage()");
        //
        clickGreenCity();
        return new HomeGreencityPage(driver);
    }

    public SigninPage gotoSigninPage() {
        clickSignin();
        return new SigninPage(driver);
    }

}
