package com.softserve.edu07fwk.tools;

public final class UrlWrapper {
    private static final String GREENCIT_DEFAULT_URL = "https://www.greencity.cx.ua/#/ubs";

    private UrlWrapper() {
    }

    public static String getUrl() {
        String url = GREENCIT_DEFAULT_URL;
        if (WebDriverWrapper.getPropertiesUtil().readBaseUrl().contains("http")) {
            url = WebDriverWrapper.getPropertiesUtil().readBaseUrl();
        }
        return url;
    }
}
