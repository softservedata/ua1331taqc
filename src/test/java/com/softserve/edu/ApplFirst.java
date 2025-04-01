package com.softserve.edu;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplFirst {

    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        //
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        //
        WebDriver driver = new ChromeDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        //
        driver.get("https://www.bing.com/");
        Thread.sleep(2000); // For Presentation
        //
        WebElement div = driver.findElement(By.id("sb_form_c"));
        //WebElement q = div.findElement(By.cssSelector("div > textarea")); // CSS
        //WebElement q = div.findElement(By.xpath("/div/textarea")); // XPath Search from root
        WebElement q = div.findElement(By.xpath("./div/textarea")); // XPath Ok
        q.sendKeys("mac");
        //
        //driver.findElement(By.name("q")).sendKeys("mac");
        Thread.sleep(2000); // For Presentation
        //
        //driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //
        Thread.sleep(8000); // For Presentation
        //
        driver.quit();
    }

}