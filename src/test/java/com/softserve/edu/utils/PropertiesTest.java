package com.softserve.edu.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PropertiesTest {
    private Long implicitlyWaitSeconds = null;
    private String baseUrl = null;
    private String browsername = null;

    private void fillPreperties() {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        try {
            implicitlyWaitSeconds = Long.valueOf(propertiesUtil.readImplicitlyWait());
        } catch (NumberFormatException e) {
            implicitlyWaitSeconds = 100L;
        }
        baseUrl = propertiesUtil.readBaseUrl();
    }

    private void fillEnvironments() {
        DonEnvUtils donEnvUtils = new DonEnvUtils();
        try {
            implicitlyWaitSeconds = Long.valueOf(donEnvUtils.readImplicitlyWait());
        } catch (NumberFormatException e) {
            implicitlyWaitSeconds = 100L;
        }
        baseUrl = donEnvUtils.readBaseUrl();
        browsername = donEnvUtils.readBrowserName();
    }

    @BeforeAll
    public void setup() {
        fillPreperties();
        fillEnvironments();
    }

    @Test
    public void checkProperties() {
        System.out.println("implicitlyWaitSeconds = " + implicitlyWaitSeconds);
        System.out.println("baseUrl = " + baseUrl);
        //
        Assertions.assertNotNull(implicitlyWaitSeconds);
        Assertions.assertNotNull(baseUrl);
    }

    @Test
    public void checkEnvironments() {
        System.out.println("implicitlyWaitSeconds = " + implicitlyWaitSeconds);
        System.out.println("baseUrl = " + baseUrl);
        System.out.println("browsername = " + browsername);
        //
        Assertions.assertNotNull(implicitlyWaitSeconds);
        Assertions.assertNotNull(baseUrl);
        Assertions.assertNotNull(browsername);
    }
}
