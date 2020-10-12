package io.houze.agent.web.login;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Test_Login_01_Web_Admin extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = PageGeneratorManager.getLoginPageWebAdminHouzeAgent(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void Login_01_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_01_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Click to Login Button");
        homePage = loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Verify Homepage is not displayed");
        verifyFalse(homePage.isDynamicPageIsDisplayed("Home"));

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_02_Wrong_ID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_02_Wrong_ID");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
        loginPage.inputToUsernameTextbox("admin12345");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
        loginPage.inputToPasswordTextbox("123456");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is not displayed");
        verifyFalse(homePage.isDynamicPageIsDisplayed("Home"));

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_03_Wrong_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_03_Wrong_Password");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
        loginPage.inputToUsernameTextbox("admin");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
        loginPage.inputToPasswordTextbox("111111111");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is not displayed");
        verifyFalse(homePage.isDynamicPageIsDisplayed("Home"));

        ExtentTestManager.endTest();
    }

    @Test
    public void Login_04_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_03_Wrong_Password");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Input to username textbox");
        loginPage.inputToUsernameTextbox("admin");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input to password textbox");
        loginPage.inputToPasswordTextbox("123456");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click to Login button");
        homePage = loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify the Home page is displayed");
        verifyTrue(homePage.isDynamicPageIsDisplayed("Home"));

        ExtentTestManager.endTest();
    }
}
