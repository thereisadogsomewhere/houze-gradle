package io.houze.agent.web.employee;

import commons.AbstractPageData;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;

import static io.houze.agent.web.employee.EmployeeData.*;

public class Test_Employee_03_Change_Password extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    EmployeePageObject employeePage;

    String homePageTitle;

    String rdUsername = Form.NewEmployee.USERNAME + randomNumber();
    String rdEmployeeCode = Form.NewEmployee.EMPLOYEE_CODE + randomNumber();
    String rdPhone = Form.NewEmployee.PHONE + randomNumber();

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

        employeePage = PageGeneratorManager.getEmployeePageHouzeAgent(driver);
        employeePage.clickToCreateNewEmployeeButton();

        employeePage.inputToDynamicTextbox(Form.USERNAME_FIELD, rdUsername);
        employeePage.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.NewEmployee.PASSWORD);
        employeePage.inputToDynamicTextbox(Form.FULLNAME_FIELD, Form.NewEmployee.FULLNAME);
        employeePage.inputToDynamicTextbox(Form.IDCARD_FIELD, Form.NewEmployee.IDCARD);
        employeePage.inputToDynamicTextbox(Form.PHONE_FIELD, rdPhone);
        employeePage.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, rdEmployeeCode);
        employeePage.selectInDynamicDropdown(Form.ROLE_DROPDOWN, Form.ROLE_AGENT_VALUE);
        employeePage.inputToDynamicTextbox(Form.START_DATE_FIELD, Form.NewEmployee.START_DATE);
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_CHANGE_PASSWORD);
    }

    @AfterClass(alwaysRun = true, groups = "smoke")
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Blank_Data() {
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(Form.PASSWORD_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test(groups = "smoke")
    public void TC_02_Valid_Data() {
        employeePage.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.EditEmployee.PASSWORD);
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
    }
}
