package com.example.coderepubliq.uiautomation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.widget.DrawerLayout;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.Gravity;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
/**
 * Created by coderepubliq on 6/2/16.
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class sample {
    private UiDevice mDevice;

    @Before
    public void before() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        assertThat(mDevice, notNullValue());

        // Start from the home screen
        mDevice.pressHome();
//        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

    }

//    @org.junit.Test
    public void noDataTest() throws InterruptedException {
        openApp("com.squadzz.app.staging");
        UiObject2 btnLogin = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutLogin"));
        btnLogin.click();
        UiObject2 txtEmail= waitForObject(By.res("com.squadzz.app.staging:id/etEmailOrMobile"));
        txtEmail.setText("");
        UiObject2 txtPass = waitForObject(By.res("com.squadzz.app.staging:id/etPassword"));
        txtPass.setText("");
        UiObject2 btnPlay = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutPlayBall"));
        btnPlay.click();

    }

//    @org.junit.Test
    public void invalidTest() throws InterruptedException {
        openApp("com.squadzz.app.staging");
        UiObject2 btnLogin = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutLogin"));
        btnLogin.click();
        UiObject2 txtEmail= waitForObject(By.res("com.squadzz.app.staging:id/etEmailOrMobile"));
        txtEmail.setText("invalidemail");
        UiObject2 txtPass = waitForObject(By.res("com.squadzz.app.staging:id/etPassword"));
        txtPass.setText("aaaaaa");
        UiObject2 btnPlay = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutPlayBall"));
        btnPlay.click();

    }

//    @org.junit.Test
    public void wrongPassTest() throws InterruptedException {
        openApp("com.squadzz.app.staging");
        UiObject2 btnLogin = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutLogin"));
        btnLogin.click();
        UiObject2 txtEmail= waitForObject(By.res("com.squadzz.app.staging:id/etEmailOrMobile"));
        txtEmail.setText("misakidono@mailinator.com");
        UiObject2 txtPass = waitForObject(By.res("com.squadzz.app.staging:id/etPassword"));
        txtPass.setText("sgsdfsdfsddfs");
        UiObject2 btnPlay = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutPlayBall"));
        btnPlay.click();

    }


    @org.junit.Test
    public void successfullLogin() throws InterruptedException, UiObjectNotFoundException {
        openApp("com.squadzz.app.staging");
        UiObject descriptionElement = new UiObject(new UiSelector().description("Open navigation drawer"));
        UiObject LoginElement = new UiObject(new UiSelector().resourceId("com.squadzz.app.staging:id/btnLayoutLogin"));
        if(descriptionElement.isEnabled()) {
            descriptionElement.click();
            UiObject2 btnLogout = waitForObject(By.text("Logout"));
            btnLogout.click();
            successlogin();
        }else if(LoginElement.isEnabled()) {
            successlogin();
         }

    }


    public void successlogin() throws InterruptedException {
        UiObject2 btnLogin = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutLogin"));
        btnLogin.click();
        UiObject2 txtEmail= waitForObject(By.res("com.squadzz.app.staging:id/etEmailOrMobile"));
        txtEmail.setText("misakidono@mailinator.com");
        UiObject2 txtPass = waitForObject(By.res("com.squadzz.app.staging:id/etPassword"));
        txtPass.setText("aaaaaa");
        UiObject2 btnPlay = waitForObject(By.res("com.squadzz.app.staging:id/btnLayoutPlayBall"));
        btnPlay.click();
    }
    private void openApp(String packageName) {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private UiObject2 waitForObject(BySelector selector) throws InterruptedException {
        UiObject2 object = null;
        int timeout = 30000;
        int delay = 1000;
        long time = System.currentTimeMillis();
        while (object == null) {
            object = mDevice.findObject(selector);
            Thread.sleep(delay);
            if (System.currentTimeMillis() - timeout > time) {
                fail();
            }
        }
        return object;
    }

}
