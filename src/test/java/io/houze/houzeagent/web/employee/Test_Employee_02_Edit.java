package io.houze.houzeagent.web.employee;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.workflows.houzeagent.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;
import java.lang.reflect.Method;
import static io.houze.houzeagent.web.employee.EmployeeData.Form;
import static io.houze.houzeagent.web.employee.EmployeeData.List;

public class Test_Employee_02_Edit extends AbstractTest {
    WebDriver driver;
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
    String fullName;

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
        username = data.getUsername();
        fullName = data.getFullname();

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

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    admin
                            .inputToDynamicTextbox(Form.USERNAME_FIELD, username)
                            .inputToDynamicTextbox(Form.PASSWORD_FIELD, Form.NewEmployee.PASSWORD)
                            .inputToDynamicTextbox(Form.FULLNAME_FIELD, fullName)
                            .inputToDynamicTextbox(Form.IDCARD_FIELD, Form.NewEmployee.IDCARD)
                            .inputToDynamicTextbox(Form.PHONE_FIELD, phone)
                            .inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, employeeCode)
                            .selectInDynamicDropdown(Form.ROLE_DROPDOWN, Form.ROLE_AGENT_VALUE)
                            .inputToDynamicTextbox(Form.START_DATE_FIELD, Form.NewEmployee.START_DATE)
                            .clickToSaveButton();
                    verifyTrue(admin.isCreateEmployeeFormIsClosed());
                });
    }

    @BeforeMethod(groups = "smoke")
    public void beforeMethod() {
        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    admin
                            .openPage()
                            .inputToFilterNameTextbox(fullName)
                            .clickToFilterButton();
                    verifyEquals(admin.getDynamicEmployeeListInfo(List.EMPLOYEE_COLUMN), fullName);
                    admin
                            .clickToActionDropdownButton()
                            .clickToDynamicActionDropdownItem(GlobalConstants.ACTION_EDIT);
                    verifyTrue(admin.isFormDisplayed());
                });
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Edit_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 01: Clear all fields");
                    admin.inputToDynamicTextbox(Form.FULLNAME_FIELD, AbstractPageData.BLANK)
                            .inputToDynamicTextbox(Form.IDCARD_FIELD, AbstractPageData.BLANK)
                            .inputToDynamicTextbox(Form.PHONE_FIELD, AbstractPageData.BLANK)
                            .inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD, AbstractPageData.BLANK)
                            .selectInDynamicDropdown(Form.ROLE_DROPDOWN, AbstractPageData.BLANK)
                            .inputToDynamicTextbox(Form.START_DATE_FIELD, AbstractPageData.BLANK);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 02: Click save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 03: Verify error messages");
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

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 04: Close form");
                    admin.clickToCloseCreateEmployeeForm();
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Edit_Existed_Employee_Code(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Edit_Existed_Employee_Code");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 01: Input existed employee code");
                    admin.inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD,
                            Form.EXISTED_EMPLOYEE_CODE);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 02: Click save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 03: Verify error messages");
                    verifyEquals(admin.getDynamicErrorText(Form.EMPLOYEE_CODE_FIELD),
                            Form.EXISTED_EMPLOYEE_CODE_ERROR_TEXT);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 04: Close form");
                    admin.clickToCloseCreateEmployeeForm();

                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Edit_Existed_Phone(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_03_Edit_Existed_Phone");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 01: Input existed phone");
                    admin.inputToDynamicTextbox(Form.PHONE_FIELD,
                            Form.EXISTED_PHONE);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 02: Click save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 03: Verify error messages");
                    verifyEquals(admin.getDynamicErrorText(Form.PHONE_FIELD),
                            Form.EXISTED_PHONE_ERROR_TEXT);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 04: Close form");
                    admin.clickToCloseCreateEmployeeForm();
                });

        ExtentTestManager.endTest();
    }

    @Test(groups = "smoke")
    public void TC_04_Edit_Valid_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_04_Edit_Valid_Data");

        EmployeeWorkflow
                .employeePage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 01: Input valid data");
                    admin
                            .inputToDynamicTextbox(Form.FULLNAME_FIELD,
                                    Form.EditEmployee.FULLNAME)
                            .inputToDynamicTextbox(Form.IDCARD_FIELD,
                                    Form.EditEmployee.IDCARD)
                            .inputToDynamicTextbox(Form.PHONE_FIELD,
                                    editPhone)
                            .inputToDynamicTextbox(Form.EMPLOYEE_CODE_FIELD,
                                    editEmployeeCode)
                            .selectInDynamicDropdown(Form.ROLE_DROPDOWN,
                                    Form.ROLE_MANAGER_VALUE)
                            .inputToDynamicTextbox(Form.START_DATE_FIELD,
                                    Form.EditEmployee.START_DATE);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 02: Click save");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Employee - Edit Employee - Step 03: Verify editted employee");
                    verifyTrue(admin.isCreateEmployeeFormIsClosed());
                    verifyEquals(admin.getDynamicEmployeeListInfo(List.EMPLOYEE_COLUMN),
                            Form.EditEmployee.FULLNAME);
                    verifyEquals(admin.getDynamicEmployeeListInfo(List.PHONE_COLUMN),
                            convertedEditPhone);
                    verifyEquals(admin.getDynamicEmployeeListInfo(List.IDCARD_COLUMN),
                            Form.EditEmployee.IDCARD);
                    verifyEquals(admin.getDynamicEmployeeListInfo(List.EMPLOYEE_CODE_COLUMN),
                            editEmployeeCode);
                    admin
                            .clickToActionDropdownButton()
                            .clickToDynamicActionDropdownItem(GlobalConstants.ACTION_EDIT);
                    verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.FULLNAME_FIELD),
                            Form.EditEmployee.FULLNAME);
                    verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.IDCARD_FIELD),
                            Form.EditEmployee.IDCARD);
                    verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.PHONE_FIELD),
                            editPhone);
                    verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.EMPLOYEE_CODE_FIELD),
                            editEmployeeCode);
                    verifyEquals(admin.getDynamicValueEditEmployeeDropdown(Form.ROLE_DROPDOWN),
                            Form.ROLE_MANAGER_VALUE);
                    verifyEquals(admin.getDynamicValueEditEmployeeTextbox(Form.START_DATE_FIELD),
                            Form.EditEmployee.START_DATE);
                });

        ExtentTestManager.endTest();
    }
}
