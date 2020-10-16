package io.houze.houzeagent.web.training;

import com.relevantcodes.extentreports.LogStatus;
import commons.AbstractPageData;
import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.workflows.houzeagent.LoginWebAdminWorkFlow;
import commons.workflows.houzeagent.TestWorkflow;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static io.houze.houzeagent.web.training.TrainingData.Form;

public class Test_Training_01_Test_Create extends AbstractTest {
    WebDriver driver;
    DataHelper data;
    String homePageTitle, testName, docLink;

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
                        .inputToPasswordTextbox(GlobalConstants.PASSWORD))

                .homePage(driver, admin -> {
                    verifyTrue(admin.isDynamicPageIsDisplayed(homePageTitle));
                    admin
                            .clickToLanguageDropdownButtonInNavBar()
                            .clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN);
                });

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        TestWorkflow
                .testPage(driver, admin -> {
                    admin
                            .openPage()
                            .clickToCreateNewButton();
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

        TestWorkflow
                .testPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Create Test - Step 01: Click to save button");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 02: Verify info");
                    verifyEquals(admin.getDynamicErrorText(Form.TEXTAREA_NAME_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.TEXTAREA_DESC_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.DURATION_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.SCORE_FIELD),
                            Form.REQUIRED_ERROR_TEXT);
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Duration_Score_Smaller_Than_0(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Duration_Score_Smaller_Than_0");

        TestWorkflow
                .testPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 01: Input to Duration and Score Smaller than 0");
                    admin
                            .inputToDynamicTextbox(Form.INVALID_DURATION_VALUE, Form.DURATION_FIELD)
                            .inputToDynamicTextbox(Form.INVALID_SCORE_VALUE, Form.SCORE_FIELD);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 02: Click to Save button");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 03: Verify error text");
                    verifyEquals(admin.getDynamicErrorText(Form.DURATION_FIELD),
                            Form.MORE_THAN_0_ERROR_TEXT);
                    verifyEquals(admin.getDynamicErrorText(Form.SCORE_FIELD),
                            Form.MORE_THAN_0_ERROR_TEXT);
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Valid_Data_Without_Project(Method method) {
        testName = data.getTitle();
        ExtentTestManager.startTest(method.getName(), "TC_04_Valid_Data");

        TestWorkflow
                .testPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 01: Input all data to form");
                    admin
                            .inputToDynamicTextarea(testName, Form.TEXTAREA_NAME_FIELD)
                            .inputToDynamicTextarea(Form.DESC_VALUE, Form.TEXTAREA_DESC_FIELD)
                            .inputToDynamicTextbox(docLink, Form.DOC_LINK_FIELD)
                            .inputToDynamicTextbox(Form.VALID_DURATION_VALUE, Form.DURATION_FIELD)
                            .inputToDynamicTextbox(Form.VALID_SCORE_VALUE, Form.SCORE_FIELD)
                            .uploadImage(Form.IMAGE_NAME);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 02: Click to Save button");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 03: Verify new test info");
                    verifyTrue(admin.isFormClosed());
                    admin
                            .inputToFilterNameTextbox(testName)
                            .clickToFilterButton();
                    verifyEquals(admin.getDynamicTextFirstRowTrainingList(TrainingData.List.COLUMN_NAME,
                            TrainingData.List.FIRST_ROW), testName);
                });

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_04_Valid_Data_With_Project(Method method) {
        testName = data.getTitle();

        ExtentTestManager.startTest(method.getName(), "TC_04_Valid_Data");
        TestWorkflow
                .testPage(driver, admin -> {
                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 01: Input all data to form");
                    admin
                            .inputToDynamicTextarea(testName, Form.TEXTAREA_NAME_FIELD)
                            .inputToDynamicTextarea(Form.DESC_VALUE, Form.TEXTAREA_DESC_FIELD)
                            .inputToDynamicTextbox(docLink, Form.DOC_LINK_FIELD)
                            .selectInProjectDropdown(Form.PROJECT_NAME)
                            .inputToDynamicTextbox(Form.VALID_DURATION_VALUE, Form.DURATION_FIELD)
                            .inputToDynamicTextbox(Form.VALID_SCORE_VALUE, Form.SCORE_FIELD)
                            .uploadImage(Form.IMAGE_NAME);

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 02: Click to Save button");
                    admin.clickToSaveButton();

                    ExtentTestManager.getTest().log(LogStatus.INFO,
                            "Training - Step 03: Verify new test info");
                    verifyTrue(admin.isFormClosed());
                    admin
                            .inputToFilterNameTextbox(testName)
                            .clickToFilterButton();
                    verifyEquals(admin
                            .getDynamicTextFirstRowTrainingList(TrainingData.List.COLUMN_NAME,
                            TrainingData.List.FIRST_ROW), testName);
                });

        ExtentTestManager.endTest();
    }
}
