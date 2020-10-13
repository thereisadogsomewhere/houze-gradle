package houzeagent.pageUIs;

import commons.AbtractPageUI;

public class EmployeePageUI extends AbtractPageUI {
    public static final String DYNAMIC_ERROR_TEXT = "//label[@for = 'id_%s']//following-sibling::p";
    public static final String DYNAMIC_INFO_EMPLOYEE_LIST = "(//b[text() = '%s']//following-sibling::span)[%s]";
    public static final String CREATE_NEW_EMPLOYEE_BUTTON = "//button[@id='create-employee']";
    public static final String SAVE_BUTTON = "//button[@id='id_submit']";
    public static final String DYNAMIC_CREATE_EMPLOYEE_TEXTBOX = "//input[@id='id_%s']";
    public static final String DYNAMIC_CREATE_EMPLOYEE_DROPDOWN = "//select[@id='id_%s']";
    public static final String CLOSE_CREATE_EMPLOYEE_FORM = "//button[@class='close']//span";
}
