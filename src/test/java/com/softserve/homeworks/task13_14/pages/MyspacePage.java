package com.softserve.homeworks.task13_14.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyspacePage extends MenuPart{

    private WebElement myHabits;
    private WebElement myNews;
    private WebElement myEvents;
    private WebElement userName;

    public MyspacePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements(){
        myHabits = driver.findElement(By.cssSelector("div[role='tablist'] div#mat-tab-label-0-0"));
        myNews = driver.findElement(By.cssSelector("div[role='tablist'] div#mat-tab-label-0-1"));
        myEvents = driver.findElement(By.cssSelector("div[role='tablist'] div#mat-tab-label-0-2"));
        userName = driver.findElement(By.cssSelector(".app-profile-header p.name"));
    }

    // PageObject Atomic Operation

    // myHabits
    public WebElement getMyHabits() {
        return myHabits;
    }

    public String getMyHabitsText() {
        return getMyHabits().getText().trim();
    }

    public void clickMyHabits() {
        getMyHabits().click();
    }

    // myNews
    public WebElement getMyNews() {
        return myNews;
    }

    public String getMyNewsText() {
        return getMyNews().getText().trim();
    }

    public void clickMyNews() {
        getMyNews().click();
    }

    // myNews
    public WebElement getMyEvents() {
        return myEvents;
    }

    public String getMyEventsText() {
        return getMyEvents().getText().trim();
    }

    public void clickMyEvents() {
        getMyEvents().click();
    }

    // username
    public WebElement getUserName() {
        return userName;
    }

    public String getUserNameText() {
        return getUserName().getText().trim();
    }


}
