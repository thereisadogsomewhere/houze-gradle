package commons.pageobjects.houzeagent.app.agent;

import commons.AbstractPageMobile;
import commons.pageuis.houzeagent.app.agent.AgentPersonalScreenUI;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AgentPersonalScreenObject extends AbstractPageMobile {
    AndroidDriver<AndroidElement> driver;

    public AgentPersonalScreenObject(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public boolean isAgentInfoIsDisplayed(String value) {
        waitElementVisible(driver, AgentPersonalScreenUI.DYNAMIC_AGENT_INFO, value);
        return isElementDisplayed(driver, AgentPersonalScreenUI.DYNAMIC_AGENT_INFO,
                value);

    }

    @Override
    public boolean isAt() {
        waitElementVisible(driver, AgentPersonalScreenUI.DYNAMIC_AGENT_INFO, "Have a good day!");
        return isElementDisplayed(driver, AgentPersonalScreenUI.DYNAMIC_AGENT_INFO,
                "Have a good day!");
    }
}
