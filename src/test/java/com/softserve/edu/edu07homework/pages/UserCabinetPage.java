package com.softserve.edu.edu07homework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserCabinetPage extends MenuPart{

    public static final String USER_CABINET = "User Cabinet";
    public static final String USER_NAME = "Test01";
    private WebElement userStatus;
    private WebElement profileAvatar;
    private WebElement userCalendar;
    private WebElement userName;

    final String AVATAR_VISIBLE = "document.querySelector('div.profile-avatar').checkVisibility()";
    final String CALENDAR_VISIBLE = "document.querySelector('app-calendar.app-calendar').checkVisibility();";
    public UserCabinetPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        profileAvatar = driver.findElement(By.cssSelector("div.profile-avatar"));
        userStatus = driver.findElement(By.cssSelector("div.online"));
        userCalendar = driver.findElement(By.cssSelector("app-calendar.app-calendar"));
    }

    // PageObject Atomic Operation

    // profileAvatar;

    public WebElement getProfileAvatar() {
        return profileAvatar; // Classic Page Object
    }

    public String getAboutServiceText() {
        return getProfileAvatar().getText().trim();
    }
    //userStatus
    public WebElement getUserStatus() {
        return userStatus; // Classic Page Object
    }

    public String getUserStatusText() {
        return getUserStatus().getText().trim();
    }
    //userCalendar
    public WebElement getUserCalendar() {
        return userCalendar; // Classic Page Object
    }

    public boolean checkUserCalendarVisible() {
        return userCalendar.isDisplayed(); // Classic Page Object
    }

    public void userCalendarClick() {
        getUserCalendar().click();
    }

    //userName
    public WebElement getUserName() {
        return driver.findElement(By.cssSelector("p.name")); // Classic Page Object
    }

    public String getUserNameText() {
        return getUserName().getText().trim();
    }

    public UserCabinetPage gotoUserCabinetPage() {
        logger.debug("start UserCabinetPage()");
        //
        userCalendarClick();
        return new UserCabinetPage(driver);
    }

    // Business Logic
    // userAvatar
    public boolean getUserAvatarVisible() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return (boolean)javascriptExecutor.executeScript(AVATAR_VISIBLE);
        //  return getErrorEmail().getDomProperty("visibility");
    }

}
