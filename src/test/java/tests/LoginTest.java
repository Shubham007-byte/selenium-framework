package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import io.qameta.allure.Description;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify user is able to login with valid credentials")
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }
}
