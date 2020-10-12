package houzeagent.pageObjects;

import commons.AbstractPage;
import houzeagent.pageUIs.TrainingPageUI;
import org.openqa.selenium.WebDriver;

public class TrainingPageObject extends AbstractPage {
    WebDriver driver;


    public TrainingPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewButton() {
        waitElementClickable(driver, TrainingPageUI.CREATE_NEW_BUTTON);
        clickToElement(driver, TrainingPageUI.CREATE_NEW_BUTTON);
    }

    public boolean isFormOpened() {
        waitElementVisible(driver, TrainingPageUI.FORM_TITLE);
        return isElementDisplayed(driver, TrainingPageUI.FORM_TITLE);
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, TrainingPageUI.SAVE_BUTTON);
        clickToElement(driver, TrainingPageUI.SAVE_BUTTON);
    }

    public String getDynamicErrorText(String fieldName) {
        waitElementVisible(driver, TrainingPageUI.DYNAMIC_FORM_ERROR_TEXT, fieldName);
        return getElementText(driver, TrainingPageUI.DYNAMIC_FORM_ERROR_TEXT, fieldName);
    }

    public void clickToCloseFormButton() {
        waitElementClickable(driver, TrainingPageUI.CLOSE_FORM_BUTTON);
        clickToElement(driver, TrainingPageUI.CLOSE_FORM_BUTTON);
    }

    public void inputToDynamicTextbox(String value, String fieldName) {
        waitElementVisible(driver, TrainingPageUI.DYNAMIC_TEXTBOX_IN_FORM, fieldName);
        sendkeyToElement(driver, TrainingPageUI.DYNAMIC_TEXTBOX_IN_FORM,value, fieldName);
    }

    public void inputToDynamicTextarea(String value, String fieldName) {
        waitElementVisible(driver, TrainingPageUI.DYNAMIC_TEXTAREA, fieldName);
        sendkeyToElement(driver, TrainingPageUI.DYNAMIC_TEXTAREA,value, fieldName);
    }

    public void selectInProjectDropdown(String projectName) {
        waitElementVisible(driver, TrainingPageUI.PROJECT_DROPDOWN);
        selectItemsInCustomDropdown(driver, TrainingPageUI.PROJECT_DROPDOWN,
                TrainingPageUI.PROJECT_DROPDOWN_ITEMS,
                projectName);
    }

    public void selectInTestsDropdown(String testName) {
        waitElementVisible(driver, TrainingPageUI.TESTS_DROPDOWN);
        selectItemsInCustomDropdown(driver, TrainingPageUI.TESTS_DROPDOWN, TrainingPageUI.TESTS_DROPDOWN_ITEMS,
                testName);
    }

    public void uploadImage(String... imageName) {
        uploadMultipleFiles(driver, imageName);
    }

    public boolean isFormClosed() {
        waitElementInvisible(driver, TrainingPageUI.CLOSE_FORM_BUTTON);
        return isElementUndisplayed(driver, TrainingPageUI.CLOSE_FORM_BUTTON);
    }

    public void inputToFilterNameTextbox(String value) {
        waitElementVisible(driver, TrainingPageUI.FILTER_NAME_TEXTBOX);
        sendkeyToElement(driver, TrainingPageUI.FILTER_NAME_TEXTBOX, value);
    }

    public void clickToFilterButton() {
        waitElementClickable(driver, TrainingPageUI.FILTER_BUTTON);
        clickToElement(driver, TrainingPageUI.FILTER_BUTTON);
    }

    public String getDynamicTextFirstRowTrainingList(String columnName, String rowNumber) {
        waitElementVisible(driver, TrainingPageUI.DYNAMIC_LIST_INFO, columnName, rowNumber);
        return getElementText(driver, TrainingPageUI.DYNAMIC_LIST_INFO, columnName, rowNumber);
    }
}
