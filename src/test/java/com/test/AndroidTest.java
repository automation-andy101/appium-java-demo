package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")).click();

        Thread.sleep(4000);

        // enter username
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("szdsda");

        // quit driver
        driver.quit();
    }

    @Test
    public void androidTapToOpenHamburgerMenuElementTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.setDeviceName("Android-Pixel-2");
        options.setApp(System.getProperty("user.dir") + "/APK/Android-MyDemoAppRN.1.1.0.build-226.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(4000);

        // get hamburger icon element
        WebElement hamburgerMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));

        // tab hamburger icon to open menu
        tap(hamburgerMenu, driver);

        // quit driver
        driver.quit();
    }

    @Test
    public void longPress() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.setDeviceName("Android-Pixel-2");
        options.setApp(System.getProperty("user.dir") + "/APK/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(4000);

        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath(".//*[@text='Expandable Lists']")).click();
        Thread.sleep(4000);
        driver.findElement(AppiumBy.xpath(".//*[@text='1. Custom Adapter']")).click();
        Thread.sleep(4000);
        WebElement element = driver.findElement(AppiumBy.xpath(".//*[@text='People Names']"));
        Thread.sleep(4000);
        new Actions(driver).clickAndHold(element).perform();

        // quit driver
        driver.quit();
    }

    @Test
    public void zoomTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.setDeviceName("Android-Pixel-2");
        options.setApp(System.getProperty("user.dir") + "/APK/Android-MyDemoAppRN.1.1.0.build-226.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(10000);

        // click hamburger icon
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        // wait for Drawing menu option to become visible
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(e -> e.findElement((By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]"))));

        // click Drawing menu option
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")).click();

        Thread.sleep(10000);

        // wait for Save button to become visible
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(e -> e.findElement((By.xpath("//android.widget.TextView[@text=\"Save\" and @class=\"android.widget.TextView\"]"))));

        // Zoom in on Drawing element
        WebElement element = driver.findElement((By.xpath("//android.view.ViewGroup[@content-desc=\"drawing screen\"]")));
        Point centreOfElement = getCentreOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(),
                        centreOfElement.getX() + 100,
                        centreOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(),
                        centreOfElement.getX() - 100,
                        centreOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform((Arrays.asList(sequence1, sequence2)));

        Thread.sleep(10000);

        // quit driver
        driver.quit();
    }

    @Test
    public void swipeOrScroll() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.setDeviceName("Android-Pixel-2");
        options.setApp(System.getProperty("user.dir") + "/APK/Android-MyDemoAppRN.1.1.0.build-226.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        Thread.sleep(10000);

        // click hamburger icon
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform((Collections.singletonList(sequence)));

        // quit driver
        driver.quit();
    }

    private void tap(WebElement element,AndroidDriver driver) {

        // tab to open hamburger menu
        Point location = element.getLocation();
        Dimension size = element.getSize();

        // get centre of hamburger icon
        Point centreOfElement = getCentreOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(sequence));

    }

    private void doubleTap(WebElement element,AndroidDriver driver) {

        // tab to open hamburger menu
        Point location = element.getLocation();
        Dimension size = element.getSize();

        // get centre of hamburger icon
        Point centreOfElement = getCentreOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centreOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(sequence));

    }

    private Point getCentreOfElement(Point location, Dimension size) {
        return new Point(
                location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2
        );
    }
}
