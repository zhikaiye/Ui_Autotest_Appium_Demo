package com.autotest.pages;

import com.autotest.driver.Driver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    static By[] byList ;
    BasePage() {
        byList = new By[]{
                byXpathAndId("//*[@resource-id='com.m4399.gamecenter:id/btn_dialog_horizontal_left']"),
                byXpathAndId("//*[@resource-id='android:id/button1' and @text='允许']"),
                byXpathAndId("//*[@resource-id='com.m4399.gamecenter:id/tv_skip']"),
                byXpathAndId("btn_dialog_horizontal_left")
        };
    }

    /**
     * 自定义find方法:循环查找元素（最多10次），若元素坐标连续3次不发生改变，则返回该element
     *
     * @param locator
     * @return
     */
    static AndroidElement find(By locator) {

        int i = 0;
        boolean a, b;
        Point[] points = new Point[10];
        do {
            try {
                points[i] = elementWait(Driver.getCurrentDriver(), 10, locator).getLocation();
            } catch (org.openqa.selenium.TimeoutException e) {
                elementWatch(byList);
                points[i] = elementWait(Driver.getCurrentDriver(), 10, locator).getLocation();
            }
            System.out.println(points[i]);
            a = i >= 2 && (points[i - 2].equals(points[i - 1]) && points[i - 1].equals(points[i]));
            i++;
            b = (i > points.length - 1 | a);
        } while (!b);

        return Driver.getCurrentDriver().findElement(locator);
    }



    static By byXpathAndId(String locater) {
        if (locater.matches("/.*")) {
            return By.xpath(locater);
        } else {
            return By.id(locater);
        }
    }

    static By byText(String name) {
        return By.xpath("//*[@text='" + name + "']");
    }

    /**
     * 滑动到指定的element
     *
     * @param driver
     * @param x
     * @param y
     */
    static void swipe(AndroidDriver driver, Point x, Point y) {

        TouchAction touchAction = new TouchAction(driver);
        System.out.println("aaa");

    }

    /**
     * 判断页面是否存在element。存在返回true，否则为false。
     *
     * @param by
     * @return
     */
    static boolean elementIsExist(By by) {
        try {
            Driver.getCurrentDriver().findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 元素监听，并且进行点击，遍历数组
     *
     * @param list
     */
    static void elementWatch(By[] list) {
        for (By by : list) {
            System.out.println(elementIsExist(by));
            if (elementIsExist(by)) {
                Driver.getCurrentDriver().findElement(by).click();
                break;
            }
        }
    }

    static WebElement elementWait(WebDriver driver, int timeout, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        try {
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (org.openqa.selenium.TimeoutException e){
            elementWatch(byList);
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

    }

    /**
     * 获取页面toast：循环查找5次，没次间隔0.5秒。并返回toast
     *
     * @return
     */

    static String getToast() {

        try {
            return elementWait(Driver.getCurrentDriver(),10,By.xpath("//*[@class='android.widget.Toast']")).getText();
        }catch (Exception e){
            return "未找到toast或者发生其它异常";
        }
    }
}
