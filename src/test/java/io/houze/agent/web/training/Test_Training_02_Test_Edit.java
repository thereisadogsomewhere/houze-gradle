package io.houze.agent.web.training;

import com.relevantcodes.extentreports.LogStatus;
import commons.*;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;
import houzeagent.pageObjects.TrainingPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static io.houze.agent.web.training.TrainingData.Form;
import static io.houze.agent.web.training.TrainingData.List;

public class Test_Training_02_Test_Edit extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    TrainingPageObject trainingPage;
    DataHelper data;

    String homePageTitle, testName, docLink;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePageTitle = "Home";
        data = DataHelper.getData();
        docLink = data.getUrl();

        loginPage = PageGeneratorManager.getLoginPageWebAdminHouzeAgent(driver);

        loginPage.inputToUsernameTextbox(GlobalConstants.USERNAME);
        loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
        homePage = loginPage.clickToLoginButton();
        homePage.isPageIsDisplayedByPageTitle(driver, homePageTitle);
        homePage.clickToLanguageDropdownButtonInNavBar();
        homePage.clickToDynamicLanguageButton(AbstractPageData.LANGUAGE_VN);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        homePage.clickToLogo();
        homePage.clickToDynamicMenu(AbstractPageData.MENU_TRAINING);
        homePage.clickToDynamicMenu(AbstractPageData.MENU_TESTS);
        trainingPage = PageGeneratorManager.getTrainingPageHouzeAgent(driver);
        trainingPage.clickToActionDropdownButton(driver, "1");
        trainingPage.clickToDynamicActionDropdownItem(driver, GlobalConstants.ACTION_EDIT);
        verifyTrue(trainingPage.isFormOpened());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void TC_01_Blank_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Edit Test - Step 01: Clear all required fields");
        trainingPage.inputToDynamicTextarea(AbstractPageData.BLANK, Form.TEXTAREA_NAME_FIELD);
        trainingPage.inputToDynamicTextarea(AbstractPageData.BLANK, Form.TEXTAREA_DESC_FIELD);
        trainingPage.inputToDynamicTextbox(driver, AbstractPageData.BLANK, Form.DURATION_FIELD);
        trainingPage.inputToDynamicTextbox(driver, AbstractPageData.BLANK, Form.SCORE_FIELD);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Edit Test - Step 02: Click save button");
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Edit Test - Step 03: Verify info");
        verifyEquals(trainingPage.getDynamicErrorText(Form.TEXTAREA_NAME_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.TEXTAREA_DESC_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.DURATION_FIELD),
                Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.SCORE_FIELD),
                Form.REQUIRED_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Edit Test - Step 04: Click to Close form button");
        trainingPage.clickToCloseFormButton();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Duration_Score_Smaller_Than_0(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02_Duration_Score_Smaller_Than_0");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 01: Input to Duration and Score Smaller than 0");
        trainingPage.inputToDynamicTextbox(driver, Form.INVALID_DURATION_VALUE, Form.DURATION_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.INVALID_SCORE_VALUE, Form.SCORE_FIELD);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 02: Click to Save button");
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 03: Verify error text");
        verifyEquals(trainingPage.getDynamicErrorText(Form.DURATION_FIELD),
                Form.MORE_THAN_0_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.SCORE_FIELD),
                Form.MORE_THAN_0_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 04: Click to Close form button");
        trainingPage.clickToCloseFormButton();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_03_Valid_Data_Without_Project(Method method) {
        testName = data.getTitle();
        ExtentTestManager.startTest(method.getName(), "TC_03_Valid_Data_Without_Project");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 01: Input all data to form");
        trainingPage.inputToDynamicTextarea(testName, Form.TEXTAREA_NAME_FIELD);
        trainingPage.inputToDynamicTextarea(Form.DESC_VALUE, Form.TEXTAREA_DESC_FIELD);
        trainingPage.inputToDynamicTextbox(driver, docLink, Form.DOC_LINK_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.VALID_DURATION_VALUE, Form.DURATION_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.VALID_SCORE_VALUE, Form.SCORE_FIELD);
        trainingPage.uploadImage(Form.IMAGE_NAME);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 02: Click to Save button");
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 03: Verify form is closed");
        verifyTrue(trainingPage.isFormClosed());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 04: Input to filter name textbox");
        trainingPage.inputToFilterNameTextbox(testName);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 05: Click to Filter button");
        trainingPage.clickToFilterButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 06: Verify info after filter");
        verifyEquals(trainingPage.getDynamicTextFirstRowTrainingList(List.COLUMN_NAME,
                List.FIRST_ROW), testName);

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_04_Valid_Data_With_Project(Method method) {
        testName = data.getTitle();
        ExtentTestManager.startTest(method.getName(), "TC_04_Valid_Data_With_Project");

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 01: Input all data to form");
        trainingPage.inputToDynamicTextarea(testName, Form.TEXTAREA_NAME_FIELD);
        trainingPage.inputToDynamicTextarea(Form.DESC_VALUE, Form.TEXTAREA_DESC_FIELD);
        trainingPage.inputToDynamicTextbox(driver, docLink, Form.DOC_LINK_FIELD);
        trainingPage.selectInProjectDropdown(Form.PROJECT_NAME);
        trainingPage.inputToDynamicTextbox(driver, Form.VALID_DURATION_VALUE, Form.DURATION_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.VALID_SCORE_VALUE, Form.SCORE_FIELD);
        trainingPage.uploadImage(Form.IMAGE_NAME);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 02: Click to Save button");
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 03: Verify form is closed");
        verifyTrue(trainingPage.isFormClosed());

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 04: Input to filter name textbox");
        trainingPage.inputToFilterNameTextbox(testName);

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 05: Click to Filter button");
        trainingPage.clickToFilterButton();

        ExtentTestManager.getTest().log(LogStatus.INFO, "Training - Step 06: Verify info after filter");
        verifyEquals(trainingPage.getDynamicTextFirstRowTrainingList(List.COLUMN_NAME,
                List.FIRST_ROW), testName);

        ExtentTestManager.endTest();
    }
}
