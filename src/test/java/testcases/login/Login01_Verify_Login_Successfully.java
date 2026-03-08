package testcases.login;

import base.BaseTest;
import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.dialog.CommonDialog;
import java.time.Duration;

public class Login01_Verify_Login_Successfully extends BaseTest {

    @Test(description = "Verify that user can login successfully with valid account")
    public void Login01_Login_Successfully() {

        String account = "soi01";
        String password = "Soi@123a";
        String fullname = "Đặng Soi";

        WebDriver driver = DriverFactory.getDriver();

        //Step 1: Go to https://demo1.cybersoft.edu.vn/
        System.out.println("Step 1: Go to https://demo1.cybersoft.edu.vn/");
        driver.get("https://demo1.cybersoft.edu.vn/");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);

        //Step 2: Click 'Đăng Nhap' link on the top right
        System.out.println("Step 2: Click 'Đăng Nhap' link on the top right");
        By byLnkLogin = By.linkText("Đăng Nhập");
        WebElement lnkLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkLogin));
        lnkLogin.click();

        //Step 3: Enter account
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterAcount(account);

        //Step 4: Enter password
        loginPage.enterPassword("Soi@123a");
        loginPage.clickRemember();
        //Step 5: Click 'Dang Nhap' button
        loginPage.clickLogin();

        //Step 6: Verify user login successfully
        //VP1: Check 'Dang Nhap Thanh Cong' message display
        CommonDialog dialog = new CommonDialog(driver);
        String recordedLoginMsg = dialog.getTextMessage();
        Assert.assertEquals(recordedLoginMsg, "Đăng nhập thành công", "Incorrect login message !");

        //VP2: Hiện link Đăng xuất
        By byLnkLogout = By.xpath("//a[contains(.,'Đăng xuất')]");
        WebElement lnkLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(byLnkLogout));
        Assert.assertTrue(lnkLogout.isDisplayed(), "Logout out link not display !");

        //VP3: Hiện tên user ở góc phải
        By byLblUserProfile = By.xpath("//a/h3[text()='" + fullname + "']");
        WebElement lblUserProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(byLblUserProfile));
        Assert.assertTrue(lblUserProfile.isDisplayed(), "User profile not display !");
    }
}
