package io.houze.houzeagent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.workflows.houzeagent.EmployeeWorkflow;
import commons.workflows.houzeagent.LoginWebAdminWorkFlow;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import static io.houze.houzeagent.web.employee.EmployeeData.*;

public class Test_Employee_01_Create extends AbstractTest {
    WebDriver driver;
    AndroidDriver<AndroidElement> appDriver;
    DataHelper data;
    String username;
    String employeeCode;
    String phone;
    String convertedPhone;
    String homePageTitle;
    String fullName;

    @Parameters({"browser",
            "url",
            "appPackage",
            "deviceName"})
    @BeforeClass(groups = "smoke")
    public void beforeClass(String browserName,
                            String appUrl,
                            String appPackage,
                            String deviceName)
            throws MalformedURLException {
        driver = getBrowserDriver(browserName, appUrl);
        appDriver = getAppDriver(appPackage, deviceName);
        data = DataHelper.getData();

        homePageTitle = "Home";
        username = data.getUsername();
        employeeCode = data.getEmployeeCode();
        fullName = data.getFullname();
        phone = data.getPhone();
        convertedPhone = convertPhoneNumber(phone);

        LoginWebAdminWorkFlow
                .loginPage(driver, admin -> admin
                        .inputToUsernameTextbox(GlobalConstants.USERNAME)
                        .inputToPasswordTextbox(GlobalConstants.PASSWORD)
                        .clickToLoginButton())

                .homePage(driver, admin -> {
                    verifyTrue(admin.isDynamicPageIsDisplayed(homePageTitle));
                    admin
                            .clickToLanguageDropdownButtonInNavBar()
                            .clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN);
        });
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        EmployeeWorkflow.employeePage(driver, admin -> admin
                .openPage()
                .clickToCreateNewEmployeeButton());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
        appDriver.quit();
    }

    @Test
    public void TC_01_Login_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Login_Blank_Data");

        EmployeeWorkflow.employeePage(driver, admin -> {
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 01: Click to Save button");
            admin.clickToSaveButton();
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 02: Verify error messages");
            verifyEquals(admin.getDynamicErrorText(Form.USERNAME_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.PASSWORD_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.FULLNAME_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.IDCARD_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.PHONE_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.ROLE_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
            verifyEquals(admin.getDynamicErrorText(Form.START_DATE_FIELD),
                    Form.REQUIRED_ERROR_TEXT);
        });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Login_Existed_Username(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Existed_Username");

        EmployeeWorkflow.employeePage(driver, admin -> {
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 01: Input existed username");
            admin.inputToDynamicTextbox(Form.USERNAME_FIELD, Form.EXISTED_USERNAME);

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 02: Click to Save button");
            admin.clickToSaveButton();

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 03: Verify error messages");
            verifyEquals(admin.getDynamicErrorText(Form.USERNAME_FIELD),
                    Form.EXISTED_USERNAME_ERROR_TEXT);
        });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Login_Existed_Employee_Code(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Existed_Employee_Code");

        EmployeeWorkflow.employeePage(driver, admin -> {
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 01: Input existed employee code");
            admin.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, Form.EXISTED_EMPLOYEE_CODE);

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 02: Click Save");
            admin.clickToSaveButton();

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 03: Verify error messages");
            verifyEquals(admin.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                    Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);
        });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_04_Login_Existed_Phone(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_04_Existed_Phone");

        EmployeeWorkflow.employeePage(driver, admin -> {
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 01: Input existed phone");
            admin.inputToDynamicTextbox(Form.PHONE_FIELD, Form.EXISTED_PHONE);

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 02: Click Save");
            admin.clickToSaveButton();

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 03: Verify error messages");
            verifyEquals(admin.getDynamicErrorText(Form.PHONE_FIELD),
                    Form.EXISTED_PHONE_ERROR_TEXT);
        });

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_05_Login_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_05_Valid_Data");

        EmployeeWorkflow.employeePage(driver, admin -> {
            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 01: Input valid data");
            admin
                    .inputToDynamicTextbox(Form.USERNAME_FIELD, username)
                    .inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.NewEmployee.PASSWORD)
                    .inputToDynamicTextbox(Form.FULLNAME_FIELD, fullName)
                    .inputToDynamicTextbox(Form.IDCARD_FIELD, Form.NewEmployee.IDCARD)
                    .inputToDynamicTextbox(Form.PHONE_FIELD, phone)
                    .inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, employeeCode)
                    .selectInDynamicDropdown(Form.ROLE_DROPDOWN, Form.ROLE_AGENT_VALUE)
                    .inputToDynamicTextbox(Form.START_DATE_FIELD, Form.NewEmployee.START_DATE);

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 02: Click save");
            admin.clickToSaveButton();
            verifyTrue(admin.isCreateEmployeeFormIsClosed());

            ExtentTestManager.getTest().log(LogStatus.INFO,
                    "Employee - Create Employee - Step 03: Verify new created employee");
            verifyEquals(admin.getDynamicEmployeeListInfo(List.EMPLOYEE_COLUMN),
                    fullName);
            verifyEquals(admin.getDynamicEmployeeListInfo(List.USERNAME_COLUMN),
                    username);
            verifyEquals(admin.getDynamicEmployeeListInfo(List.PHONE_COLUMN),
                    convertedPhone);
            verifyEquals(admin.getDynamicEmployeeListInfo(List.IDCARD_COLUMN),
                    Form.NewEmployee.IDCARD);
            verifyEquals(admin.getDynamicEmployeeListInfo(List.EMPLOYEE_CODE_COLUMN),
                    employeeCode);
            admin
                    .clickToActionDropdownButton()
                    .clickToDynamicActionDropdownItem(GlobalConstants.ACTION_EDIT);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.USERNAME_FIELD),
                    username);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.FULLNAME_FIELD),
                    fullName);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.IDCARD_FIELD),
                    Form.NewEmployee.IDCARD);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.PHONE_FIELD),
                    phone);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.EMPLOYEE_CODE_FIELD),
                    employeeCode);
            verifyEquals(admin.getDynamicValueEditEmployeeDropdown(Form.ROLE_DROPDOWN),
                    Form.ROLE_AGENT_VALUE);
            verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.START_DATE_FIELD),
                    Form.NewEmployee.START_DATE);
        })
                .agentLoginScreen(appDriver, employee -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Create Employee - Step 04: Login to Houze Agent app with new Employee");
                    employee.inputToPhoneTextbox("0987654321") //phone
                            .inputToPasswordTextbox("123456") //Form.NewEmployee.PASSWORD
                            .clickToLoginButton();
                })
                .agentPersonalScreen(appDriver, employee -> {
                    verifyTrue(employee.isAgentInfoIsDisplayed(AgentApp.MESSAGE_WELCOME));
                    verifyTrue(employee.isAgentInfoIsDisplayed("Agent User")); //fullName
                    verifyTrue(employee.isAgentInfoIsDisplayed("AGENT_USER")); //employeeCode
                });

        ExtentTestManager.endTest();
    }
}
