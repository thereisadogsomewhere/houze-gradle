package io.houze.houzeagent.web.training;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.workflows.houzeagent.LoginWebAdminWorkFlow;
import commons.workflows.houzeagent.TestManagementWorkflow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static io.houze.houzeagent.web.training.TrainingData.Form;
import static io.houze.houzeagent.web.training.TrainingData.List;

public class Test_Training_04_Test_Management_Edit extends AbstractTest {
    WebDriver driver;
    DataHelper data;
    String homePageTitle, docLink, testManagementName;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePageTitle = "Home";
        data = DataHelper.getData();
        docLink = data.getUrl();

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

    @BeforeMethod
    public void beforeMethod() {
        TestManagementWorkflow
                .testManagementPage(driver, admin -> {
                    admin
                            .openPage()
                            .clickToActionDropdownButton()
                            .clickToDynamicActionDropdownItem(GlobalConstants.ACTION_EDIT);
                    verifyTrue(admin.isFormOpened());
                });
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        TestManagementWorkflow
                .testManagementPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 01: Clear all fields");
                    admin
                            .clearDynamicTextbox(Form.TEXTBOX_NAME_FIELD)
                            .clearDynamicTextbox(Form.TEXTBOX_START_DATE_FIELD)
                            .clearDynamicTextbox(Form.TEXTBOX_END_DATE_FIELD)
                            .deselectAllInTestsDropdown();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 02: Click to Save button");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 03: Verify error messages");
                    verifyEquals(admin.getDynamicErrorText(Form.TEXTBOX_NAME_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.TEXTBOX_START_DATE_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.TEXTBOX_END_DATE_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.DROPDOWN_TESTS_FIELD),
                            Form.REQUIRED_ERROR_TEXT);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Create Test Management - Step 03: Click to Close form button");
                    admin.clickToCloseFormButton();
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Valid_Info(Method method) {
        testManagementName = data.getUsername();
        ExtentTestManager.startTest(method.getName(), "TC_02_Valid_Info");

        TestManagementWorkflow
                .testManagementPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 01: Fill the create test management form");
                    admin
                            .inputToDynamicTextbox(testManagementName, Form.TEXTBOX_NAME_FIELD)
                            .inputToDynamicTextbox(Form.START_DATE_EDITTED_VALUE, Form.TEXTBOX_START_DATE_FIELD)
                            .inputToDynamicTextbox(Form.END_DATE_EDITTED_VALUE, Form.TEXTBOX_END_DATE_FIELD)
                            .selectInTestsDropdown(Form.TEST_NAME_EDITTED)
                            .clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 02: Verify new test management is created");
                    verifyTrue(admin.isFormClosed());
                    admin
                            .inputToFilterNameTextbox(testManagementName)
                            .clickToFilterButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Edit Test Management - Step 03: Verify new test management in list");
                    verifyEquals(admin.getDynamicTextFirstRowTrainingList(List.COLUMN_NAME,
                            GlobalConstants.FIRST_ROW),
                            testManagementName);
                });

        ExtentTestManager.endTest();
    }
}
