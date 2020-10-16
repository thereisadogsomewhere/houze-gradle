package io.houze.houzeagent.web.login;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractTest;
import commons.workflows.houzeagent.LoginWebAdminWorkFlow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Test_Login_01_Web_Admin extends AbstractTest {
    WebDriver driver;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void Login_01_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_01_Blank_Data");

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Click to Login Button");
                    admin.clickToLoginButton();
                })

                .homePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Verify Homepage is not displayed");
                    verifyFalse(admin.isDynamicPageIsDisplayed("Home"));
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_02_Wrong_ID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_02_Wrong_ID");

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
                    admin.inputToUsernameTextbox("admin12345");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
                    admin.inputToPasswordTextbox("123456");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
                    admin.clickToLoginButton();
                })

                .homePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is not displayed");
                    verifyFalse(admin.isDynamicPageIsDisplayed("Home"));
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_03_Wrong_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_03_Wrong_Password");

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
                    admin.inputToUsernameTextbox("admin");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
                    admin.inputToPasswordTextbox("111111111");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
                    admin.clickToLoginButton();
                })

                .homePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is not displayed");
                    verifyFalse(admin.isDynamicPageIsDisplayed("Home"));
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_04_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_03_Wrong_Password");

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
                    admin.inputToUsernameTextbox("admin");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
                    admin.inputToPasswordTextbox("123456");

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
                    admin.clickToLoginButton();
                })

                .homePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is displayed");
                    verifyTrue(admin.isDynamicPageIsDisplayed("Home"));
                });

        ExtentTestManager.endTest();
    }
}
