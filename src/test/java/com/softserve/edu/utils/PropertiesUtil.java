package com.softserve.edu.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {
    private final String PATH_SEPARATOR = "/";
    private final String FILE_NAME = "application-test.properties";
    //
    private final String BROWSER_NAME = "browser.name";
    private final String BASE_URL = "base.url";
    private final String IMPLICITLY_WAIT_SECONDS = "implicitly.wait.seconds";
    //
    private Properties appProps;
    private String filename;

    public PropertiesUtil() {
        filename = FILE_NAME;
        init();
    }

    public PropertiesUtil(String filename) {
        this.filename = filename;
        init();
    }

    private void init() {
        appProps = new Properties();
        readProperties();
    }

    private String getFullPath() {
        String path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath();
        System.out.println("\tpath = " + path);
        return path;
    }

    private void readProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(getFullPath())) {
            appProps.load(fileInputStream);
        } catch (Exception e){
            System.out.println("ERROR Reading " + filename + "  Message = " + e.getMessage());
        }
    }

    public String readBrowserName() {
        String baseUrl = appProps.getProperty(BROWSER_NAME, "chrome");
        return baseUrl;
    }

    public String readBaseUrl() {
        String baseUrl = appProps.getProperty(BASE_URL, "https://www.greencity.cx.ua/#/ubs");
        return baseUrl;
    }

    public String readImplicitlyWait() {
        String baseUrl = appProps.getProperty(IMPLICITLY_WAIT_SECONDS, "10");
        return baseUrl;
    }
}