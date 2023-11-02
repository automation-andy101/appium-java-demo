package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AndroidTest {

    @Test
    public void androidLaunchTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.setDeviceName("Android-Pixel-2");
        options.setApp(System.getProperty("user.dir") + "/APK/Android-MyDemoAppRN.1.1.0.build-226.apk");
//        options.setApp(System.getProperty("user.dir") + "/APK/ChatLoop_1.0_apkcombo.com.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(4000);

        // click hamburger icon
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        // wait for login menu option to become visible
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(e -> e.findElement((By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]"))));

        // click login menu option
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]"));

        // enter username
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("szdsda");



    }
}
