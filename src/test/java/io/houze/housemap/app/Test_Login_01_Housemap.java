//package io.houze.housemap.app;
//
//import com.relevantcodes.extentreports.LogStatus;
//import commons.AbstractTest;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
//import org.testng.annotations.*;
//import reportConfig.ExtentTestManager;
//
//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
//
//public class Test_Login_01_Housemap extends AbstractTest {
//    AndroidDriver<AndroidElement> driver;
//
//    @Parameters({
//            "appPackage",
//            "deviceName"
//    })
//    @BeforeClass
//    public void beforeClass(String appPackage, String deviceName) throws MalformedURLException {
//        driver = getAppDriver(appPackage, deviceName);
//    }
//
//    @Test
//    public void TC_01_Login_With_Blank_Data(Method method) {
//        ExtentTestManager.startTest(method.getName(), "TC_01_Login_With_Blank_Data");
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Access Login menu");
//        loginPage = PageGeneratorManagerMobile.getHousemapLoginScreen(driver);
//        loginPage.clickToLoginButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Verify login button is disabled");
//        verifyTrue(loginPage.isLoginButtonIsDisabled());
//
//        ExtentTestManager.endTest();
//    }
//
//    @Test
//    public void TC_02_Login_With_Wrong_Account(Method method) {
//        ExtentTestManager.startTest(method.getName(), "TC_02_Login_With_Wrong_Account");
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Access Login menu");
//        loginPage = PageGeneratorManagerMobile.getHousemapLoginScreen(driver);
//        loginPage.clickToLoginButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input phone and password");
//        loginPage.inputToPhoneTextbox(LoginData.PHONE);
//        loginPage.inputToPasswordTextbox(LoginData.WRONG_PASSWORD);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click login button");
//        loginPage.clickToLoginButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Verify error message is displayed");
//        verifyTrue(loginPage.isTextErrorIsDisplayed(LoginData.ERROR_MESSAGE));
//
//        ExtentTestManager.endTest();
//    }
//
//    @Test
//    public void TC_03_Login_With_Valid_Data(Method method) {
//        ExtentTestManager.startTest(method.getName(), "TC_03_Login_With_Valid_Data");
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Access Login menu");
//        loginPage = PageGeneratorManagerMobile.getHousemapLoginScreen(driver);
//        loginPage.clickToLoginButton();
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 02: Input phone and password");
//        loginPage.inputToPhoneTextbox(LoginData.PHONE);
//        loginPage.inputToPasswordTextbox(LoginData.PASSWORD);
//
//        ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 03: Click login button");
//        loginPage.clickToLoginButton();
//
//        ExtentTestManager.endTest();
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        driver.closeApp();
//        driver.launchApp();
//    }
//
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
//}
