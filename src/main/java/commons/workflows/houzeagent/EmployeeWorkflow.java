package commons.workflows.houzeagent;

import commons.AbstractPage;
import commons.AbstractPageMobile;
import commons.pageobjects.houzeagent.app.agent.AgentLoginScreenObject;
import commons.pageobjects.houzeagent.app.agent.AgentPersonalScreenObject;
import commons.pageobjects.houzeagent.web.EmployeePageObject;
import commons.pageobjects.houzeagent.web.HomePageObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.function.Consumer;

public class EmployeeWorkflow {
    public static EmployeeWorkflow employeePage(WebDriver driver, Consumer<EmployeePageObject> admin) {
        EmployeePageObject employeePage = new EmployeePageObject(driver);
        admin.accept(employeePage);
        return new EmployeeWorkflow();
    }

    public EmployeeWorkflow homePage(WebDriver driver, Consumer<HomePageObject> admin) {
        HomePageObject homePage = new HomePageObject(driver);
        this.waitForPageLoad(homePage);
        admin.accept(homePage);
        return this;
    }

    public EmployeeWorkflow agentLoginScreen(AndroidDriver<AndroidElement> appDriver, Consumer<AgentLoginScreenObject> employee) {
        AgentLoginScreenObject agentLoginScreen = new AgentLoginScreenObject(appDriver);
        this.waitForScreenLoad(agentLoginScreen);
        employee.accept(agentLoginScreen);
        return this;
    }

    public void agentPersonalScreen(AndroidDriver<AndroidElement> appDriver, Consumer<AgentPersonalScreenObject> employee) {
        AgentPersonalScreenObject agentPersonalScreen = new AgentPersonalScreenObject(appDriver);
        this.waitForScreenLoad(agentPersonalScreen);
        employee.accept(agentPersonalScreen);
    }

    private void waitForPageLoad (AbstractPage page) {
        Assert.assertTrue(page.isAt());
    }
    private void waitForScreenLoad (AbstractPageMobile screen) {
        Assert.assertTrue(screen.isAt());
    }

}
