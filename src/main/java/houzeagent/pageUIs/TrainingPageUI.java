package houzeagent.pageUIs;

public class TrainingPageUI {
    public static final String DYNAMIC_LIST_INFO = "(//b[text() = '%s']//following-sibling::span)[%s]";
    public static final String DYNAMIC_FORM_ERROR_TEXT = "//label[@for = 'id_%s']//following-sibling::p";
    public static final String CREATE_NEW_BUTTON = "//button[@id='create-object']";
    public static final String FORM_TITLE = "//h5[@class='modal-title']";
    public static final String SAVE_BUTTON = "//button[@id='id_submit']";
    public static final String CLOSE_FORM_BUTTON = "//button[@class='btn btn-default']";
    public static final String DYNAMIC_TEXTBOX_IN_FORM = "//form[@id = 'modal-form']//input[@id = 'id_%s']";
    public static final String DYNAMIC_TEXTAREA = "//textarea[@id='id_%s']";
    public static final String PROJECT_DROPDOWN = "//span[@id='select2-id_project-container']";
    public static final String PROJECT_DROPDOWN_ITEMS = "//li[@class = 'select2-results__option']";
    public static final String FILTER_NAME_TEXTBOX = "//input[@id='id_name']";
    public static final String FILTER_BUTTON = "//button[text() = 'Lọc']";
    public static final String TESTS_DROPDOWN = "//button[@data-id = 'id_tests']";
    public static final String TESTS_DROPDOWN_ITEMS = "//div[@role = 'listbox']//a";
}
