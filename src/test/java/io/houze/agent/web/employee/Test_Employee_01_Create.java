package io.houze.agent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.*;
import houzeagent.pageObjects.HomePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.LoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

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
    public void TC_01_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 01: Click to Save button");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 02: Verify error messages");
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

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 03: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Existed_Username(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Existed_Username");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 01: Input existed username");
        employeePage.inputToDynamicTextbox(Form.USERNAME_FIELD, Form.EXISTED_USERNAME);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 02: Click to Save button");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 03: Verify error messages");
        verifyEquals(employeePage.getDynamicErrorText(Form.USERNAME_FIELD),
                Form.EXISTED_USERNAME_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Existed_Employee_Code(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Existed_Employee_Code");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 01: Input existed employee code");
        employeePage.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, Form.EXISTED_EMPLOYEE_CODE);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 02: Click Save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 03: Verify error messages");
        verifyEquals(employeePage.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_04_Existed_Phone(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_04_Existed_Phone");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 01: Input existed phone");
        employeePage.inputToDynamicTextbox(Form.PHONE_FIELD, Form.EXISTED_PHONE);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 02: Click Save");
        employeePage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 03: Verify error messages");
        verifyEquals(employeePage.getDynamicErrorText(Form.PHONE_FIELD),
                Form.EXISTED_PHONE_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 04: Close form");
        employeePage.clickToCloseCreateEmployeeForm();

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_05_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_05_Valid_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 01: Input valid data");
        employeePage.inputToDynamicTextbox(Form.USERNAME_FIELD, username);
        employeePage.inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.NewEmployee.PASSWORD);
        employeePage.inputToDynamicTextbox(Form.FULLNAME_FIELD, fullName);
        employeePage.inputToDynamicTextbox(Form.IDCARD_FIELD, Form.NewEmployee.IDCARD);
        employeePage.inputToDynamicTextbox(Form.PHONE_FIELD, phone);
        employeePage.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, employeeCode);
        employeePage.selectInDynamicDropdown(Form.ROLE_DROPDOWN, Form.ROLE_AGENT_VALUE);
        employeePage.inputToDynamicTextbox(Form.START_DATE_FIELD, Form.NewEmployee.START_DATE);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 02: Click save");
        employeePage.clickToSaveButton();
        verifyTrue(employeePage.isCreateEmployeeFormIsClosed());

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Employee - Create Employee - Step 03: Verify new created employee");
        verifyEquals(employeePage.getDynamicEmployeeListInfo(List.EMPLOYEE_COLUMN, GlobalConstants.FIRST_ROW),
                fullName);
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
                fullName);
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

        ExtentTestManager.endTest();
    }
}
