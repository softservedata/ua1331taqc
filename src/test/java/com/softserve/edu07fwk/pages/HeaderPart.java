package com.softserve.edu07fwk.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class HeaderPart {

    protected WebDriver driver;
    //
    private WebElement languageDropdown;
    //private WebElement english;
    //private WebElement ukrainian;

    public HeaderPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        languageDropdown = driver.findElement(By.cssSelector("ul.header_lang-switcher-wrp span[aria-hidden]"));
        //english = driver.findElement(By.xpath("//ul[contains(@class, 'header_lang-switcher-wrp')]//span[text()='En']"));
        //ukrainian = driver.findElement(By.xpath("//ul[contains(@class, 'header_lang-switcher-wrp')]//span[text()='Ua']"));
    }

    // PageObject Atomic Operation

    // languageDropdown
    public WebElement getLanguageDropdown() {
        return languageDropdown; // Classic Page Object
    }

    public String getLanguageDropdownText() {
        return getLanguageDropdown().getText().trim();
    }

    public void clickLanguageDropdown() {
        getLanguageDropdown().click();
    }

    // english
    public WebElement getEnglish() {
        getLanguageDropdown();
        return driver.findElement(By.xpath("//ul[contains(@class, 'header_lang-switcher-wrp')]//span[text()='En']"));
        //return english; // Classic Page Object
    }

    public String getEnglishText() {
        return getEnglish().getText().trim();
    }

    public void clickEnglish() {
        getEnglish().click();
    }

    // ukrainian
    public WebElement getUkrainian() {
        getLanguageDropdown();
        return driver.findElement(By.xpath("//ul[contains(@class, 'header_lang-switcher-wrp')]//span[text()='Ua']"));
        //return ukrainian; // Classic Page Object
    }

    public String getUkrainianText() {
        return getUkrainian().getText().trim();
    }

    public void clickUkrainian() {
        getUkrainian().click();
    }

    // PageObject Functional Operation
//    protected void chooseEnglishLanguage() {
//        clickLanguageDropdown();
//        clickEnglish();
//    }

//    protected void chooseUkrainianLanguage() {
//        clickLanguageDropdown();
//        clickUkrainian();
//    }

    // PageObject Business Operation
}
