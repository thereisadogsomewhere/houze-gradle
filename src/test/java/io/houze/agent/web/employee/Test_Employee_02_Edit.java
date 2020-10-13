package io.houze.agent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Test_Employee_02_Edit extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    EmployeePageObject employeePage;
    DataHelper data;

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
        data = DataHelper.getData();
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
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        homePage.clickToDynamicMenu(AbstractPageData.MENU_EMPLOYEE);
        employeePage = PageGeneratorManager.getEmployeePageHouzeAgent(driver);
        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Edit_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 01: Clear all fields");
        employeePage.inputToDynamicTextbox(EmployeeData.Form.FULLNAME_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.IDCARD_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD, AbstractPageData.BLANK);
        employeePage.selectInDynamicDropdown(EmployeeData.Form.ROLE_DROPDOWN, AbstractPageData.BLANK);
        employeePage.inputToDynamicTextbox(EmployeeData.Form.START_DATE_FIELD, AbstractPageData.BLANK);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 02: Click save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 03: Verify error messages");
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

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Edit_Existed_Employee_Code(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Edit_Existed_Employee_Code");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 01: Input existed employee code");
        employeePage.inputToDynamicTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD,
                EmployeeData.Form.EXISTED_EMPLOYEE_CODE);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 02: Click save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 03: Verify error messages");
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.EMPLOYEE_CODE_FIELD),
                EmployeeData.Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Edit_Existed_Phone(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Edit_Existed_Phone");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 01: Input existed phone");
        employeePage.inputToDynamicTextbox(EmployeeData.Form.PHONE_FIELD,
                EmployeeData.Form.EXISTED_PHONE);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 02: Click save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 03: Verify error messages");
        verifyEquals(employeePage.getDynamicErrorText(EmployeeData.Form.PHONE_FIELD),
                EmployeeData.Form.EXISTED_PHONE_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_04_Edit_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_04_Edit_Valid_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 01: Input valid data");
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

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 02: Click save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Edit Employee - Step 03: Verify editted employee");
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.EMPLOYEE_COLUMN, GlobalConstants.FIRST_ROW),
                EmployeeData.Form.EditEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.PHONE_COLUMN, GlobalConstants.FIRST_ROW),
                convertedEditPhone);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.IDCARD_COLUMN, GlobalConstants.FIRST_ROW),
                EmployeeData.Form.EditEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicEmployeeListInfo(EmployeeData.List.EMPLOYEE_CODE_COLUMN, GlobalConstants.FIRST_ROW),
                editEmployeeCode);
        employeePage.clickToActionDropdownButton(driver, GlobalConstants.FIRST_ROW);
        employeePage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.FULLNAME_FIELD), EmployeeData.Form.EditEmployee.FULLNAME);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.IDCARD_FIELD), EmployeeData.Form.EditEmployee.IDCARD);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.PHONE_FIELD), editPhone);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.EMPLOYEE_CODE_FIELD), editEmployeeCode);
        verifyEquals(employeePage.getDynamicValueEditEmployeeDropdown(EmployeeData.Form.ROLE_DROPDOWN), EmployeeData.Form.ROLE_MANAGER_VALUE);
        verifyEquals(employeePage.getDynamicValueEditEmployeeTextbox(EmployeeData.Form.START_DATE_FIELD), EmployeeData.Form.EditEmployee.START_DATE);

        ExtentTestManager.endTest();
    }
}
