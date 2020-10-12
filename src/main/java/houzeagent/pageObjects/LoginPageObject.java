package houzeagent.pageObjects;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import houzeagent.pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;


    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageObject clickToLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePageWebAdminHouzeAgent(driver);
    }

    public void inputToUsernameTextbox(String username) {
        waitElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    public void inputToPasswordTextbox(String password) {
        waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }
}
