package commons.workflows.houzeagent;

import commons.AbstractPage;
import commons.pageobjects.houzeagent.web.HomePageObject;
import commons.pageobjects.houzeagent.web.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.function.Consumer;

public class LoginWebAdminWorkFlow {
    public static LoginWebAdminWorkFlow loginPage(WebDriver driver, Consumer<LoginPageObject> admin) {
        LoginPageObject loginPage = new LoginPageObject(driver);
        admin.accept(loginPage);
        return new LoginWebAdminWorkFlow();
    }

    public LoginWebAdminWorkFlow homePage(WebDriver driver, Consumer<HomePageObject> admin) {
        HomePageObject homePage = new HomePageObject(driver);
        this.waitForPageLoad(homePage);
        admin.accept(homePage);
        return this;
    }

    private void waitForPageLoad (AbstractPage page) {
        Assert.assertTrue(page.isAt());
    }
}
