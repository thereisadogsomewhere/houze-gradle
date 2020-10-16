package io.houze.houzeagent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.workflows.houzeagent.EmployeeWorkflow;
import commons.workflows.houzeagent.LoginWebAdminWorkFlow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static io.houze.houzeagent.web.employee.EmployeeData.Form;

public class Test_Employee_03_Change_Password extends AbstractTest {
    WebDriver driver;
    String homePageTitle;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke")
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePageTitle = "Home";

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> admin
                        .inputToUsernameTextbox(GlobalConstants.USERNAME)
                        .inputToPasswordTextbox(GlobalConstants.PASSWORD)
                        .clickToLoginButton())

                .homePage(driver, admin -> {
                    verifyTrue(admin.isDynamicPageIsDisplayed(homePageTitle));
                    admin
                            .clickToLanguageDropdownButtonInNavBar()
                            .clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN)
                            .clickToDynamicMenu(AbstractPageData.MENU_EMPLOYEE);
                });
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        EmployeeWorkflow
                .employeePage(driver, admin -> admin
                        .clickToActionDropdownButton()
                        .clickToDynamicActionDropdownItem(GlobalConstants.ACTION_CHANGE_PASSWORD));
    }

    @AfterClass(alwaysRun = true, groups = "smoke")
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Change_Password_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Change_Password_Blank_Data");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 01: Click Save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 02: Verify error message");
                    verifyEquals(admin.getDynamicErrorText(Form.PASSWORD_FIELD),
                            Form.REQUIRED_ERROR_TEXT);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 03: Close form");
                    admin.clickToCloseCreateEmployeeForm();
                });

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_02_Change_Password_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Change_Password_Valid_Data");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 01: Input valid info");
                    admin.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.EditEmployee.PASSWORD);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 02: Click save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Change Password - Step 03: Verify form is closed");
                    verifyTrue(admin.isCreateEmployeeFormIsClosed());
                });

        ExtentTestManager.endTest();
    }
}
