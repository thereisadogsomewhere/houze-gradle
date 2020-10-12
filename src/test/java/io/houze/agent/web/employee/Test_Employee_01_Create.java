package io.houze.agent.web.employee;

import commons.*;
import houzeagent.pageObjects.HomePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.LoginPageObject;

import static io.houze.agent.web.employee.EmployeeData.*;

public class Test_Employee_01_Create extends AbstractTest {
    WebDriver driver;
    HomePageObject homePage;
    EmployeePageObject employeePage;
    LoginPageObject loginPage;
    DataHelper data;

    String username;
    String employeeCode;
    String phone;
    String convertedPhone;
    String homePageTitle;
    String fullName;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke")
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        data = DataHelper.getData();

        homePageTitle = "Home";
        username = data.getUsername();
        employeeCode = data.getEmployeeCode();
        fullName = data.getFullname();
        phone = data.getPhone();
        convertedPhone = convertPhoneNumber(phone);

        loginPage = PageGeneratorManager.getLoginPageWebAdminHouzeAgent(driver);
        loginPage.inputToUsernameTextbox(GlobalConstants.USERNAME);
        loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
        homePage = loginPage.clickToLoginButton();
        homePage.isPageIsDisplayedByPageTitle(driver, homePageTitle);
        homePage.clickToLanguageDropdownButtonInNavBar();
        homePage.clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN);
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        homePage.clickToDynamicMenu(AbstractPageData.MENU_EMPLOYEE);
        employeePage = PageGeneratorManager.getEmployeePageHouzeAgent(driver);
        employeePage.clickToCreateNewEmployeeButton();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Blank_Data() {
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(Form.USERNAME_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.PASSWORD_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.FULLNAME_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.IDCARD_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.PHONE_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.ROLE_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(Form.START_DATE_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test
    public void TC_02_Existed_Username() {
        employeePage.inputToDynamicTextbox(Form.USERNAME_FIELD, Form.EXISTED_USERNAME);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(Form.USERNAME_FIELD),
                Form.EXISTED_USERNAME_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test
    public void TC_03_Existed_Employee_Code() {
        employeePage.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, Form.EXISTED_EMPLOYEE_CODE);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test
    public void TC_04_Existed_Phone() {
        employeePage.inputToDynamicTextbox(Form.PHONE_FIELD, Form.EXISTED_PHONE);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(Form.PHONE_FIELD),
                Form.EXISTED_PHONE_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test(groups = "smoke")
    public void TC_05_Valid_Data() {
        employeePage.inputToDynamicTextbox(Form.USERNAME_FIELD, username);
        employeePage.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.NewEmployee.PASSWORD);
        employeePage.inputToDynamicTextbox(Form.FULLNAME_FIELD, fullName);
        employeePage.inputToDynamicTextbox(Form.IDCARD_FIELD, Form.NewEmployee.IDCARD);
        employeePage.inputToDynamicTextbox(Form.PHONE_FIELD, phone);
        employeePage.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, employeeCode);
        employeePage.selectInDynamicDropdown(Form.ROLE_DROPDOWN, Form.ROLE_AGENT_VALUE);
        employeePage.inputToDynamicTextbox(Form.START_DATE_FIELD, Form.NewEmployee.START_DATE);
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.EMPLOYEE_COLUMN, GlobalConstants.FIRST_ROW),
                Form.NewEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.USERNAME_COLUMN, GlobalConstants.FIRST_ROW),
                username);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.PHONE_COLUMN, GlobalConstants.FIRST_ROW),
                convertedPhone);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.IDCARD_COLUMN, GlobalConstants.FIRST_ROW),
                Form.NewEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.EMPLOYEE_CODE_COLUMN, GlobalConstants.FIRST_ROW),
                employeeCode);

        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.USERNAME_FIELD),
                username);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.FULLNAME_FIELD),
                Form.NewEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.IDCARD_FIELD),
                Form.NewEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.PHONE_FIELD),
                phone);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.EMPLOYEE_CODE_FIELD),
                employeeCode);
        verifyEquals(employeePage.getDynamicValueEditEmployeeDropdown(Form.ROLE_DROPDOWN),
                Form.ROLE_AGENT_VALUE);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(Form.START_DATE_FIELD),
                Form.NewEmployee.START_DATE);
    }
}
