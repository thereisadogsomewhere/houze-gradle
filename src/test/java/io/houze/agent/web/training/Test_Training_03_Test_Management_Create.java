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

import static io.houze.agent.web.training.TrainingData.*;

public class Test_Training_03_Test_Management_Create extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    HomePageObject homePage;
    TrainingPageObject trainingPage;
    DataHelper data;
    String homePageTitle, docLink, testManagementName;

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
        homePage.clickToDynamicMenu(AbstractPageData.MENU_TEST_MANAGEMENTS);
        trainingPage = PageGeneratorManager.getTrainingPageHouzeAgent(driver);
        trainingPage.clickToCreateNewButton();
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
                "Training - Create Test Management - Step 01: Click save button");
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Create Test Management - Step 02: Verify error messages");
        verifyEquals(trainingPage.getDynamicErrorText(Form.TEXTBOX_NAME_FIELD), Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.TEXTBOX_START_DATE_FIELD), Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.TEXTBOX_END_DATE_FIELD), Form.REQUIRED_ERROR_TEXT);
        verifyEquals(trainingPage.getDynamicErrorText(Form.DROPDOWN_TESTS_FIELD), Form.REQUIRED_ERROR_TEXT);

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Create Test Management - Step 03: Click to Close form button");
        trainingPage.clickToCloseFormButton();

        ExtentTestManager.endTest();
    }

    @Test
    public void TC_02_Valid_Info(Method method) {
        testManagementName = data.getUsername();
        ExtentTestManager.startTest(method.getName(), "TC_01_Blank_Data");

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Create Test Management - Step 01: Fill the create test management form");
        trainingPage.inputToDynamicTextbox(driver, testManagementName, Form.TEXTBOX_NAME_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.START_DATE_VALUE, Form.TEXTBOX_START_DATE_FIELD);
        trainingPage.inputToDynamicTextbox(driver, Form.END_DATE_VALUE, Form.TEXTBOX_END_DATE_FIELD);
        trainingPage.selectInTestsDropdown(Form.TEST_NAME);
        trainingPage.clickToSaveButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Create Test Management - Step 02: Verify new test management is created");
        verifyTrue(trainingPage.isFormClosed());
        trainingPage.inputToFilterNameTextbox(testManagementName);
        trainingPage.clickToFilterButton();

        ExtentTestManager.getTest().log(LogStatus.INFO,
                "Training - Create Test Management - Step 03: Verify new test management in list");
        verifyEquals(trainingPage.getDynamicTextFirstRowTrainingList(List.COLUMN_NAME, GlobalConstants.FIRST_ROW)
                ,testManagementName);

        ExtentTestManager.endTest();
    }
}
