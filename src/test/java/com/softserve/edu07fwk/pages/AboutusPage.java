package com.softserve.edu07fwk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutusPage extends MenuPart {
    public static final String ABOUT_US = "About Us";

    private WebElement sectionHeader;

    public AboutusPage(WebDriver driver) {
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

    public AboutusPage chooseEnglish() {
        //chooseEnglishLanguage();
        clickEnglish();
        return new AboutusPage(driver);
    }

    public AboutusPage chooseUkrainian() {
        //chooseUkrainianLanguage();
        clickUkrainian();
        return new AboutusPage(driver);
    }
}
