package com.softserve.edu07fwk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeGreencityPage extends MenuPart {

    private WebElement homeHeader;

    public HomeGreencityPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        homeHeader = driver.findElement(By.cssSelector("div#main-content h1"));
    }

    // PageObject Atomic Operation

    // homeHeader
    public WebElement getHomeHeader() {
        return homeHeader; // Classic Page Object
        //return driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']")); // Lazy initialization
    }

    public String getHomeHeaderText() {
        return getHomeHeader().getText().trim();
    }

    public void clickHomeHeader() {
        getHomeHeader().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation

    public HomeGreencityPage chooseEnglish() {
        //chooseEnglishLanguage();
        clickEnglish();
        return new HomeGreencityPage(driver);
    }

    public HomeGreencityPage chooseUkrainian() {
        //chooseUkrainianLanguage();
        clickUkrainian();
        return new HomeGreencityPage(driver);
    }
}
