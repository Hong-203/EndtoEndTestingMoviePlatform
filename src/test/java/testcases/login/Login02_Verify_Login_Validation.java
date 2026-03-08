package testcases.login;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.dialog.CommonDialog;

import java.time.Duration;

public class Login02_Verify_Login_Validation extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    CommonDialog dialog;

    @BeforeMethod
    public void openLoginPage() {

        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open website
        driver.get("https://demo1.cybersoft.edu.vn/");

        // Step 2: Click login link
        By byLnkLogin = By.xpath("//a[contains(.,'Đăng Nhập')]");
        wait.until(ExpectedConditions.elementToBeClickable(byLnkLogin)).click();

        loginPage = new LoginPage(driver);
        dialog = new CommonDialog(driver);
    }

    @Test(description = "TC_LOGIN_VAL_01 - Leave all fields empty")
    public void loginWithEmptyFields() {

        System.out.println("Running TC_LOGIN_VAL_01");

        loginPage.enterAcount("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String errorMsg = dialog.getTextMessage();

        Assert.assertEquals(
                errorMsg,
                "Tài khoản hoặc mật khẩu không đúng!",
                "Error message incorrect when fields are empty"
        );
    }

    @Test(description = "TC_LOGIN_VAL_04 - Invalid account or password")
    public void loginWithInvalidAccount() {

        System.out.println("Running TC_LOGIN_VAL_04");

        loginPage.enterAcount("tk_khong_ton_tai");
        loginPage.enterPassword("Pass123!");
        loginPage.clickLogin();

        String errorMsg = dialog.getTextMessage();

        Assert.assertEquals(
                errorMsg,
                "Tài khoản hoặc mật khẩu không đúng!",
                "Incorrect error message for invalid account"
        );
    }

    @Test(description = "TC_LOGIN_VAL_05 - Valid account but wrong password")
    public void loginWithWrongPassword() {

        System.out.println("Running TC_LOGIN_VAL_05");

        loginPage.enterAcount("soi01");
        loginPage.enterPassword("SaiPass123");
        loginPage.clickLogin();

        String errorMsg = dialog.getTextMessage();

        Assert.assertEquals(
                errorMsg,
                "Tài khoản hoặc mật khẩu không đúng!",
                "Incorrect error message for wrong password"
        );
    }

    @Test(description = "TC_LOGIN_VAL_06 - Special characters in account")
    public void loginWithSpecialCharacters() {

        System.out.println("Running TC_LOGIN_VAL_06");

        loginPage.enterAcount("admin' OR '1'='1");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        String errorMsg = dialog.getTextMessage();

        Assert.assertEquals(
                errorMsg,
                "Tài khoản hoặc mật khẩu không đúng!",
                "System does not handle special characters properly"
        );
    }
}