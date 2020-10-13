package io.houze.agent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static io.houze.agent.web.employee.EmployeeData.*;

public class Test_Employee_03_Change_Password extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    EmployeePageObject employeePage;

    String homePageTitle;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke")
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePageTitle = "Home";

        loginPage = PageGeneratorManager.getLoginPageWebAdminHouzeAgent(driver);
        loginPage.inputToUsernameTextbox(GlobalConstants.USERNAME);
        loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
        homePage = loginPage.clickToLoginButton();
        homePage.isPageIsDisplayedByPageTitle(driver, homePageTitle);
        homePage.clickToLanguageDropdownButtonInNavBar();
        homePage.clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN);
        homePage.clickToDynamicMenu(AbstractPageData.MENU_EMPLOYEE);
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        employeePage = PageGeneratorManager.getEmployeePageHouzeAgent(driver);
        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_CHANGE_PASSWORD);
    }

    @AfterClass(alwaysRun = true, groups = "smoke")
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Change_Password_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Change_Password_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 01: Click Save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 02: Verify error message");
        verifyEquals(employeePage.getDynamicErrorText(Form.PASSWORD_FIELD),
                Form.REQUIRED_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 03: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_02_Change_Password_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Change_Password_Valid_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 01: Input valid info");
        employeePage.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.EditEmployee.PASSWORD);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 02: Click save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Change Password - Step 03: Verify form is closed");
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());

        ExtentTestManager.endTest();
    }
}
