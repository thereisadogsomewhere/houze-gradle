package commons.pageuis.housemap.app;

public class LoginPageUI {
    public static final String BUTTON_LOGIN = "//android.widget.Button[@content-desc='Đăng nhập']";
    public static final String TEXTBOX_PHONE_LOGIN = "//android.view.View" +
            "/android.widget.EditText[contains(@text, 'số điện thoại')]";
    public static final String TEXTBOX_PASSWORD_LOGIN = "//android.view.View" +
            "/android.widget.EditText[contains(@text, 'mật khẩu')]";
    public static final String MESSAGE_ERROR_WRONG_ACCOUNT = "//android.widget.EditText//parent::android.view.View";
}
