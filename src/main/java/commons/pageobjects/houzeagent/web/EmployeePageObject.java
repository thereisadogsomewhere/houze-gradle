package commons.pageobjects.houzeagent.web;

import commons.AbstractPage;
import commons.AbtractPageUI;
import commons.GlobalConstants;
import commons.pageuis.houzeagent.web.EmployeePageUI;
import org.openqa.selenium.WebDriver;

public class EmployeePageObject extends AbstractPage {
    private WebDriver driver;

    private static final String URL = GlobalConstants.BASE_URL_HA_PRO_QA + "/web/employee/";

    public EmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public static EmployeePageObject using(WebDriver driver) {
        return new EmployeePageObject(driver);
    }

    public String getDynamicErrorText(String fieldName) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_ERROR_TEXT, fieldName);
        return getElementText(driver, EmployeePageUI.DYNAMIC_ERROR_TEXT, fieldName);
    }

    public void clickToCreateNewEmployeeButton() {
        waitElementClickable(driver, EmployeePageUI.CREATE_NEW_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeePageUI.CREATE_NEW_EMPLOYEE_BUTTON);
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, EmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, EmployeePageUI.SAVE_BUTTON);
    }

    public EmployeePageObject inputToDynamicTextbox(String fieldName, String value) {
        waitElementVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, fieldName);
        sendkeyToElement(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, value, fieldName);
        return this;
    }

    public EmployeePageObject selectInDynamicDropdown(String dropdownName, String value) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, dropdownName);
        selectInDropdownByValue(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, value, dropdownName);
        return this;
    }

    public String getDynamicEmployeeListInfo(String columnName) {
        return getElementText(driver, EmployeePageUI.DYNAMIC_INFO_EMPLOYEE_LIST, columnName);
    }

    public void clickToCloseCreateEmployeeForm() {
        waitElementClickable(driver, EmployeePageUI.CLOSE_CREATE_EMPLOYEE_FORM);
        clickToElement(driver, EmployeePageUI.CLOSE_CREATE_EMPLOYEE_FORM);
    }

    public boolean isCreateEmployeeFormIsClosed() {
        waitElementsInvisible(driver, EmployeePageUI.CLOSE_CREATE_EMPLOYEE_FORM);
        return isElementUndisplayed(driver, EmployeePageUI.CLOSE_CREATE_EMPLOYEE_FORM);
    }

    public String getDynamicValueEditEmployeeTextbox(String fieldName) {
        waitElementVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, fieldName);
        return getElementAttribute(driver, AbtractPageUI.DYNAMIC_TEXTBOX_IN_FORM, "value", fieldName);
    }

    public String getDynamicValueEditEmployeeDropdown(String roleName) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, roleName);
        return getElementAttribute(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, "value", roleName);
    }

    public EmployeePageObject openPage() {
        openPageUrl(driver, URL);
        return this;
    }

    public EmployeePageObject inputToFilterNameTextbox(String value) {
        waitElementVisible(driver, AbtractPageUI.FILTER_NAME_TEXTBOX);
        sendkeyToElement(driver, AbtractPageUI.FILTER_NAME_TEXTBOX, value);
        return this;
    }

    public void clickToFilterButton() {
        waitElementClickable(driver, AbtractPageUI.FILTER_BUTTON);
        clickToElement(driver, AbtractPageUI.FILTER_BUTTON);
    }

    public boolean isFormDisplayed() {
        waitElementVisible(driver, EmployeePageUI.SAVE_BUTTON);
        return isElementDisplayed(driver, EmployeePageUI.SAVE_BUTTON);
    }

    public void clickToDynamicActionDropdownItem(String action) {
        waitElementClickable(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
        clickToElement(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
    }

    public EmployeePageObject clickToActionDropdownButton() {
        waitElementClickable(driver, AbtractPageUI.ACTION_DROPDOWN_BUTTON);
        clickToElement(driver, AbtractPageUI.ACTION_DROPDOWN_BUTTON);
        return this;
    }

    @Override
    public boolean isAt() {
        waitElementVisible(driver, EmployeePageUI.CREATE_NEW_EMPLOYEE_BUTTON);
        return isElementDisplayed(driver, EmployeePageUI.CREATE_NEW_EMPLOYEE_BUTTON);
    }
}
