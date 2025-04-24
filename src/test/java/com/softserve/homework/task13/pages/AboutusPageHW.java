package com.softserve.homework.task13.pages;

import com.softserve.edu07fwk.pages.AboutusPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutusPageHW extends MenuPartHW {
    public static final String ABOUT_US = "About Us";
    //
    private WebElement sectionHeader;

    public AboutusPageHW(WebDriver driver){
        super(driver);
        initElements();
    }

    private void initElements() {
        sectionHeader = driver.findElement(By.cssSelector("div.about-section h2.section__header"));
    }

    // PageObject Atomic Operation

    // sectionHeader
    public WebElement getSectionHeader() {
        return sectionHeader; // Classic Page Object
    }

    public String getSectionHeaderText() {
        return getSectionHeader().getText().trim();
    }

    public void clickSectionHeader() {
        getSectionHeader().click();
    }

    // PageObject Functional Operation

    // PageObject Business Operation
    public AboutusPageHW chooseEnglish() {
        //chooseEnglishLanguage();
        clickEnglish();
        return new AboutusPageHW(driver);
    }

    public AboutusPageHW chooseUkrainian() {
        //chooseUkrainianLanguage();
        clickUkrainian();
        return new AboutusPageHW(driver);
    }


}
