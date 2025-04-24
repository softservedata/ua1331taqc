package com.softserve.homework.task13.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeGreenCityPageHW extends MenuPartHW {

    //
    private WebElement homeHeader;


    public HomeGreenCityPageHW(WebDriver driver){
        super(driver);
        initElements();
    }

    private void initElements() {
        homeHeader = driver.findElement(By.cssSelector("div#main-content h1"));
    }

    // PageObject Atomic Operation

    //homeHeader
    public WebElement getHomeHeader() {
        return homeHeader;
    }

    public String getHomeHeaderText() {
        return getHomeHeader().getText().trim();
    }

    public void clickHomeHeader() {
        getHomeHeader().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation
    public HomeGreenCityPageHW chooseEnglish() {
        //chooseEnglishLanguage();
        clickEnglish();
        return new HomeGreenCityPageHW(driver);
    }

    public HomeGreenCityPageHW chooseUkrainian() {
        //chooseUkrainianLanguage();
        clickUkrainian();
        return new HomeGreenCityPageHW(driver);
    }
}
