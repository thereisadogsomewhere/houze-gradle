package io.houze.agent.web.employee;

import commons.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Test_Employee_02_Edit extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    EmployeePageObject employeePage;
    DataHelper data;

    String username;
    String employeeCode;
    String phone;
    String idCard;
    String convertedPhone;
    String homePageTitle;
    String editEmployeeCode;
    String editPhone;
    String convertedEditPhone;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke")
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePageTitle = "Home";

        username = data.getUsername();
        employeeCode = data.getEmployeeCode();
        phone = data.getPhone();
        convertedPhone = convertPhoneNumber(phone);
        idCard = data.getIDCard();
        editEmployeeCode = data.getEmployeeCode();
        editPhone = data.getPhone();
        convertedEditPhone = convertPhoneNumber(editPhone);

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

        employeePage.inputToDynamicTextbox(EmployeeData.Form.USERNAME_FIELD, username);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PASSWORD_FIELD, EmployeeData.Form.NewEmployee.PASSWORD);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.FULLNAME_FIELD, EmployeeData.Form.NewEmployee.FULLNAME);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.IDCARD_FIELD, EmployeeData.Form.NewEmployee.IDCARD);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD, phone);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD, employeeCode);
        employeePage.selectInDynamicDropdown(EmployeeData.Form.ROLE_DROPDOWN, EmployeeData.Form.ROLE_AGENT_VALUE);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.START_DATE_FIELD, EmployeeData.Form.NewEmployee.START_DATE);
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Blank_Data() {
        employeePage.inputToDynamicTextbox(EmployeeData.Form.FULLNAME_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.IDCARD_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD, AbstractPageData.BLANK);
        employeePage.selectInDynamicDropdown(EmployeeData.Form.ROLE_DROPDOWN, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.START_DATE_FIELD, AbstractPageData.BLANK);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.FULLNAME_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.IDCARD_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.PHONE_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.EMPLOYEE_CODE_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.ROLE_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.START_DATE_FIELD),
                EmployeeData.Form.REQUIRED_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test
    public void TC_02_Existed_Employee_Code() {
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD, EmployeeData.Form.EXISTED_EMPLOYEE_CODE);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.EMPLOYEE_CODE_FIELD),
                EmployeeData.Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test
    public void TC_03_Existed_Phone() {
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD, EmployeeData.Form.EXISTED_PHONE);
        employeePage.clickToSaveButton();
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.PHONE_FIELD),
                EmployeeData.Form.EXISTED_PHONE_ERROR_TEXT);
        employeePage.clickToCloseCreateEmployeeForm();
    }

    @Test(groups = "smoke")
    public void TC_04_Valid_Data() {
        employeePage.inputToDynamicTextbox(EmployeeData.Form.FULLNAME_FIELD,
                EmployeeData.Form.EditEmployee.FULLNAME);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.IDCARD_FIELD,
                EmployeeData.Form.EditEmployee.IDCARD);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD,
                editPhone);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD,
                editEmployeeCode);
        employeePage.selectInDynamicDropdown(EmployeeData.Form.ROLE_DROPDOWN,
                EmployeeData.Form.ROLE_MANAGER_VALUE);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.START_DATE_FIELD,
                EmployeeData.Form.EditEmployee.START_DATE);
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.EMPLOYEE_COLUMN, GlobalConstants.FIRST_ROW),
                EmployeeData.Form.EditEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.USERNAME_COLUMN, GlobalConstants.FIRST_ROW),
                username);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.PHONE_COLUMN, GlobalConstants.FIRST_ROW),
                editPhone);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.IDCARD_COLUMN, GlobalConstants.FIRST_ROW),
                EmployeeData.Form.EditEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.EMPLOYEE_CODE_COLUMN, GlobalConstants.FIRST_ROW),
                editEmployeeCode);

        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.USERNAME_FIELD), username);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.FULLNAME_FIELD), EmployeeData.Form.EditEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.IDCARD_FIELD), EmployeeData.Form.EditEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.PHONE_FIELD), editPhone);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD), editEmployeeCode);
        verifyEquals(employeePage.getDynamicValueEditEmployeeDropdown(EmployeeData.Form.ROLE_DROPDOWN), EmployeeData.Form.ROLE_MANAGER_VALUE);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.START_DATE_FIELD), EmployeeData.Form.EditEmployee.START_DATE);
    }
}
