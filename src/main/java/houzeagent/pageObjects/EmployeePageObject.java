package houzeagent.pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import houzeagent.pageUIs.EmployeePageUI;

public class EmployeePageObject extends AbstractPage {
    WebDriver driver;


    public EmployeePageObject(WebDriver driver) {
        this.driver = driver;
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

    public void inputToDynamicTextbox(String fieldName, String value) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_TEXTBOX, fieldName);
        sendkeyToElement(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_TEXTBOX, value, fieldName);
    }

    public void selectInDynamicDropdown(String dropdownName, String value) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, dropdownName);
        selectInDropdownByValue(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN,value, dropdownName);
    }

    public String getDynamicEmployeeListInfo(String columnName, String rowNumber) {
        return getElementText(driver, EmployeePageUI.DYNAMIC_INFO_EMPLOYEE_LIST, columnName, rowNumber);
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
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_TEXTBOX, fieldName);
        return getElementAttribute(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_TEXTBOX, "value", fieldName);
    }

    public String getDynamicValueEditEmployeeDropdown(String roleName) {
        waitElementVisible(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, roleName);
        return getElementAttribute(driver, EmployeePageUI.DYNAMIC_CREATE_EMPLOYEE_DROPDOWN, "value", roleName);
    }

    public boolean isDynamicErrorMessageDisplayed(String fieldName) {
        return isElementDisplayed(driver, EmployeePageUI.DYNAMIC_ERROR_TEXT, fieldName);
    }
}
