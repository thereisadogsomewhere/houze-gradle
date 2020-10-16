package commons.pageobjects.houzeagent.web;

import commons.AbstractPage;
import commons.pageuis.houzeagent.web.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public LoginPageObject inputToUsernameTextbox(String username) {
        waitElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
        return this;
    }

    public LoginPageObject inputToPasswordTextbox(String password) {
        waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
        return this;
    }

    @Override
    public boolean isAt() {
        waitElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.USERNAME_TEXTBOX);
    }
}
