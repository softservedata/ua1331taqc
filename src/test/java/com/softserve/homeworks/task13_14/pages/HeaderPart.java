package com.softserve.homeworks.task13_14.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HeaderPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WebDriver driver;
    private WebElement languageDropdown;

    public HeaderPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        languageDropdown = driver.findElement(By.cssSelector("ul.header_lang-switcher-wrp span[aria-hidden]"));
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
        clickLanguageDropdown();
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
        clickLanguageDropdown();
        return driver.findElement(By.xpath("//ul[contains(@class, 'header_lang-switcher-wrp')]//span[text()='Ua']"));
        //return ukrainian; // Classic Page Object
    }

    public String getUkrainianText() {
        return getUkrainian().getText().trim();
    }

    public void clickUkrainian() {
        getUkrainian().click();
    }
    
}