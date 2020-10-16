package commons.pageobjects.housemap.app;

import commons.AbstractPageMobile;
import commons.pageuis.housemap.app.LoginPageUI;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class HousemapLoginScreenObject extends AbstractPageMobile {
    AndroidDriver<AndroidElement> driver;

    public HousemapLoginScreenObject(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public void clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.BUTTON_LOGIN);
        clickToElement(driver,LoginPageUI.BUTTON_LOGIN);
    }

    public void inputToPhoneTextbox(String text) {
        waitElementVisible(driver, LoginPageUI.TEXTBOX_PHONE_LOGIN);
        sendKeyToElement(driver, LoginPageUI.TEXTBOX_PHONE_LOGIN, text);
    }

    public void inputToPasswordTextbox(String text) {
        waitElementVisible(driver, LoginPageUI.TEXTBOX_PASSWORD_LOGIN);
        sendKeyToElement(driver, LoginPageUI.TEXTBOX_PASSWORD_LOGIN, text);
    }

    public boolean isLoginButtonIsDisabled() {
        waitElementVisible(driver, LoginPageUI.BUTTON_LOGIN);
        return !isElementEnabled(driver, LoginPageUI.BUTTON_LOGIN);
    }

    public Boolean isTextErrorIsDisplayed(String errorText) {
        waitElementAttributeContains(driver, LoginPageUI.MESSAGE_ERROR_WRONG_ACCOUNT,
                "content-desc", errorText);
        return getAttributeElement(driver, LoginPageUI.MESSAGE_ERROR_WRONG_ACCOUNT,
                "content-desc")
                .contains(errorText);
    }

    @Override
    public boolean isAt() {
        waitElementVisible(driver, LoginPageUI.BUTTON_LOGIN);
        return isElementDisplayed(driver, LoginPageUI.BUTTON_LOGIN);
    }
}
