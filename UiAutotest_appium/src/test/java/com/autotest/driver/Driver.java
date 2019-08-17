package com.autotest.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private static AndroidDriver<AndroidElement> driver;

    public static void startDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformNage","android");
        capabilities.setCapability("deviceName","35a5dba2");
        capabilities.setCapability("appPackage","com.m4399.gamecenter");
        capabilities.setCapability("appActivity",".controllers.splash.SplashActivity");
        capabilities.setCapability("noReset","true");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    public static AndroidDriver<AndroidElement> getCurrentDriver(){
        return driver;
    }
}
