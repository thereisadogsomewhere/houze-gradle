package commons.pageobjects.houzeagent.app.agent;

import commons.AbstractPageMobile;
import commons.pageuis.houzeagent.app.agent.AgentLoginScreenUI;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AgentLoginScreenObject extends AbstractPageMobile {
    AndroidDriver<AndroidElement> driver;

    public AgentLoginScreenObject(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public void clickToLoginButton() {
        waitElementVisible(driver, AgentLoginScreenUI.BUTTON_LOGIN);
        clickToElement(driver, AgentLoginScreenUI.BUTTON_LOGIN);
    }

    public AgentLoginScreenObject inputToPasswordTextbox(String password) {
        waitElementVisible(driver, AgentLoginScreenUI.TEXTBOX_PASSWORD);
        sendKeyToElement(driver, AgentLoginScreenUI.TEXTBOX_PASSWORD, password);
        return this;
    }

    public AgentLoginScreenObject inputToPhoneTextbox(String phone) {
        waitElementVisible(driver, AgentLoginScreenUI.TEXTBOX_PHONE);
        sendKeyToElement(driver, AgentLoginScreenUI.TEXTBOX_PHONE, phone);
        return this;
    }

    @Override
    public boolean isAt() {
        waitElementVisible(driver, AgentLoginScreenUI.BUTTON_LOGIN);
        return isElementDisplayed(driver, AgentLoginScreenUI.BUTTON_LOGIN);
    }
}
