package utils;

import java.io.FileInputStream;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import io.qameta.allure.Allure;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String path = ScreenshotUtil.captureScreenshot(driver, result.getName());

        try {
            Allure.addAttachment("Screenshot",
                new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
