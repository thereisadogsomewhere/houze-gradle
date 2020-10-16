package commons.pageobjects.houzeagent.web;

import commons.AbstractPage;
import commons.AbtractPageUI;
import commons.GlobalConstants;
import commons.pageuis.houzeagent.web.TestPageUI;
import commons.pageuis.houzeagent.web.TrainingPageUI;
import org.openqa.selenium.WebDriver;

public class TestPageObject extends AbstractPage {
    WebDriver driver;
    private static final String URL = GlobalConstants.BASE_URL_HA_PRO_QA + "/web/training/test/";

    public TestPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewButton() {
        waitElementClickable(driver, TestPageUI.CREATE_NEW_BUTTON);
        clickToElement(driver, TestPageUI.CREATE_NEW_BUTTON);
    }

    public boolean isFormOpened() {
        waitElementVisible(driver, TestPageUI.FORM_TITLE);
        return isElementDisplayed(driver, TestPageUI.FORM_TITLE);
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, TestPageUI.SAVE_BUTTON);
        clickToElement(driver, TestPageUI.SAVE_BUTTON);
    }

    public String getDynamicErrorText(String fieldName) {
        waitElementVisible(driver, TestPageUI.DYNAMIC_FORM_ERROR_TEXT, fieldName);
        return getElementText(driver, TestPageUI.DYNAMIC_FORM_ERROR_TEXT, fieldName);
    }

    public void clickToCloseFormButton() {
        waitElementClickable(driver, TestPageUI.CLOSE_FORM_BUTTON);
        clickToElement(driver, TestPageUI.CLOSE_FORM_BUTTON);
    }

    public TestPageObject inputToDynamicTextarea(String value, String fieldName) {
        waitElementVisible(driver, TestPageUI.DYNAMIC_TEXTAREA, fieldName);
        sendkeyToElement(driver, TestPageUI.DYNAMIC_TEXTAREA, value, fieldName);
        return this;
    }

    public void clickToDynamicActionDropdownItem(String action) {
        waitElementClickable(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
        clickToElement(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
    }

    public TestPageObject clearDynamicTextbox(String... textbox) {
        waitElementVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, textbox);
        clearElement(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, textbox);
        return this;
    }

    public TestPageObject clickToActionDropdownButton() {
        waitElementClickable(driver, AbtractPageUI.ACTION_DROPDOWN_BUTTON);
        clickToElement(driver, AbtractPageUI.ACTION_DROPDOWN_BUTTON);
        return this;
    }

    public TestPageObject inputToDynamicTextbox(String value, String fieldName) {
        waitElementVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, fieldName);
        sendkeyToElement(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, value, fieldName);
        return this;
    }

    public TestPageObject selectInProjectDropdown(String projectName) {
        waitElementVisible(driver, TestPageUI.PROJECT_DROPDOWN);
        selectItemsInCustomDropdown(driver, TestPageUI.PROJECT_DROPDOWN,
                TrainingPageUI.PROJECT_DROPDOWN_ITEMS,
                projectName);
        return this;
    }

    public boolean isFormClosed() {
        waitElementInvisible(driver, TestPageUI.CLOSE_FORM_BUTTON);
        return isElementUndisplayed(driver, TestPageUI.CLOSE_FORM_BUTTON);
    }

    public TestPageObject inputToFilterNameTextbox(String value) {
        waitElementVisible(driver, TestPageUI.FILTER_NAME_TEXTBOX);
        sendkeyToElement(driver, TestPageUI.FILTER_NAME_TEXTBOX, value);
        return this;
    }

    public void clickToFilterButton() {
        waitElementClickable(driver, AbtractPageUI.FILTER_BUTTON);
        clickToElement(driver, AbtractPageUI.FILTER_BUTTON);
    }

    public String getDynamicTextFirstRowTrainingList(String columnName, String rowNumber) {
        waitElementVisible(driver, TestPageUI.DYNAMIC_LIST_INFO, columnName, rowNumber);
        return getElementText(driver, TestPageUI.DYNAMIC_LIST_INFO, columnName, rowNumber);
    }

    public TestPageObject uploadImage(String... imageName) {
        uploadMultipleFiles(driver, imageName);
        return this;
    }

    @Override
    public boolean isAt() {
        return true;
    }

    public TestPageObject openPage() {
        openPageUrl(driver, URL);
        return this;
    }
}
