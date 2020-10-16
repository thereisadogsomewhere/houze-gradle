package commons;

public abstract class AbtractPageUI {
    public static final String NAV_BAR_LANGUAGE_DROPDOWN_BUTTON = "//a[@id='navbarDropdown2']";
    public static final String DYNAMIC_LANGUAGE_BUTTON = "//i[contains(@class, '%s')]//parent::button";
    public static final String DYNAMIC_MENU = "//span[contains(text(), '%s')]//parent::a";
    public static final String FILTER_NAME_TEXTBOX = "//input[@id='id_name']";
    public static final String FILTER_BUTTON = "//button[text() = 'Lọc']";
    public static final String UPLOAD_FILE_TYPE = "//input[@type ='file']";
    public static final String DYNAMIC_TEXTBOX_IN_FORM = "//form[@id = 'modal-form']//input[@id = 'id_%s']";
    public static final String ACTION_DROPDOWN_BUTTON = "(//b[text() = 'Hành động']/following-sibling::span//button[contains(@class, \"dropdown-toggle\")])[1]";
    public static final String DYNAMIC_ACTION_DROPDOWN_ITEM = "//button[@aria-expanded = \"true\"]" +
            "//following-sibling::div/button[contains(@class, 'dropdown-item') and text() = '%s']";

}
