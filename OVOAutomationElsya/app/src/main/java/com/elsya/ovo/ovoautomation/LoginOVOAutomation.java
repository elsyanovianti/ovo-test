package com.elsya.ovo.ovoautomation;


import java.net.URL;
import io.appium.java_client.AppiumDriver;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class LoginOVOAutomation {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ovo.id");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appActivity", "ovo.id.loyalty.LandingActivity");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("app", "/Users/elsya/Downloads/ovo.apk");

        AppiumDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities) {
        };

        Thread.sleep(5000);
        checkText(driver, "Nomor Ponsel");

        login(driver, "");
        Thread.sleep(5000);

        login(driver, "081288354666");
        Thread.sleep(5000);

        checkText(driver, "Masukan Kode");
        Thread.sleep(5000);

        driver.quit();
    }

    private static void login(AppiumDriver driver, String phoneNumber) throws InterruptedException {
        if (phoneNumber.length() > 0) {
            driver.findElementById("field_account").clear();
            driver.findElementById("field_account").sendKeys(phoneNumber);
            driver.hideKeyboard();
            Thread.sleep(5000);
        }
        driver.findElementById("btn_sign_in").click();
    }

    private static void checkText(AppiumDriver driver, String text) {
        boolean k = false;
        try {
            driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']").isDisplayed();
        } catch (RuntimeException e) {
            k = true;
        }
        Assert.assertFalse(text + " not found", k);
    }
}
