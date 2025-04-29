package com.softserve.edu07fwk.tools;

import com.softserve.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class WebDriverWrapper {

    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private static final String LOCALSTORAGE_REMOVE_ITEM = "window.localStorage.removeItem('%s');";
    private static final String LOCALSTORAGE_SET_ITEM = "window.localStorage.setItem('%s','%s');";
    private static PropertiesUtil propertiesUtil;
    private static Map<Long, WebDriver> drivers;

    static {
        initParameters();
    }

    protected WebDriverWrapper() {
        initParameters();
    }

    private static void initParameters() {
        drivers = new HashMap<>();
        propertiesUtil = new PropertiesUtil();
    }

    private static WebDriver startDriver() {
        String browserName = getPropertiesUtil().readBrowserName();
        WebDriver driver = null;
        if (browserName.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.contains("chromeoptions")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else { // Default chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getImplicitlyWait())); // 0 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().window().maximize();
        //
        return driver;
    }

    private static long getImplicitlyWait() {
        long implicitlyWait = 10; // Default value
        try {
            implicitlyWait = Long.parseLong(getPropertiesUtil().readImplicitlyWait());
        } catch (Exception e) {
            // TODO log.warn
            System.out.println("Use default value of implicitlyWait");
        }
        return implicitlyWait;
    }

    public static PropertiesUtil getPropertiesUtil() {
        return propertiesUtil;
    }

    public static WebDriver getDriver() {
        WebDriver driver = drivers.get(Thread.currentThread().getId());
        //
        if (driver == null) {
            driver = startDriver();
            drivers.put(Thread.currentThread().getId(), driver);
        }
        return driver;
    }

    public static void setImplicitlyWait(long implicitlyWait) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
    }

    public static void restoreImplicitlyWait() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(getImplicitlyWait()));
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    public static void navigateToUrl(String url) {
        getDriver().navigate().to(url);
    }

    public static void gotoUrl(String url) {
        getDriver().get(url);
    }

    public static void switchToFrame(WebElement webElement) {
        getDriver().switchTo().frame(webElement);
    }

    public static void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public static void takeScreenShot() {
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    public static void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = getDriver().getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    public static void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public static void deleteTokens() {
        //JavascriptExecutor js = getJavascriptExecutor();
        //js.executeScript(String.format(LOCALSTORAGE_REMOVE_ITEM, "accessToken"));
        //js.executeScript(String.format(LOCALSTORAGE_REMOVE_ITEM, "refreshToken"));
        removeItemLocalStorage("accessToken");
        removeItemLocalStorage("refreshToken");
    }

    public static void setItemLocalStorage(String item, String value) {
        JavascriptExecutor js = getJavascriptExecutor();
        js.executeScript(String.format(LOCALSTORAGE_SET_ITEM, item, value));
    }

    public static void removeItemLocalStorage(String item) {
        JavascriptExecutor js = getJavascriptExecutor();
        js.executeScript(String.format(LOCALSTORAGE_REMOVE_ITEM, item));
    }

    public static void scrollToElement(WebElement webElement) {
        JavascriptExecutor js = getJavascriptExecutor();
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void clickByJavaScript(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", webElement);
    }

    public static void quit() {
//        if (driver != null) {
//            driver.quit();
//        }
        for (Map.Entry<Long, WebDriver> driverEntry : drivers.entrySet()) {
            if (driverEntry.getValue() != null) {
                driverEntry.getValue().quit();
            }
        }
    }

}
