package com.softserve.edu.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DonEnvUtils {
    private final String PATH_SEPARATOR = "/";
    //private final String FILE_NAME = ".env"; // for Passwords
    //
    private final String BROWSER_NAME = "browser.name";
    private final String BASE_URL = "base.url";
    private final String IMPLICITLY_WAIT_SECONDS = "implicitly.wait.seconds";
    //
    private Dotenv dotenv;
    private String filename;

    public DonEnvUtils() {
        //filename = FILE_NAME;
        init();
    }

//    public DonEnvUtils(String filename) {
//        this.filename = filename;
//        init();
//    }

    private void init() {
        dotenv = Dotenv.load();
    }

    public String readBrowserName() {
        String browserName = dotenv.get(BROWSER_NAME);
        return browserName;
    }

    public String readBaseUrl() {
        String baseUrl = dotenv.get(BASE_URL);
        return baseUrl;
    }

    public String readImplicitlyWait() {
        String baseUrl = dotenv.get(IMPLICITLY_WAIT_SECONDS);
        return baseUrl;
    }
}
