package commons;


import houzeagent.pageObjects.TrainingPageObject;
import org.openqa.selenium.WebDriver;
import houzeagent.pageObjects.EmployeePageObject;
import houzeagent.pageObjects.HomePageObject;
import houzeagent.pageObjects.LoginPageObject;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPageWebAdminHouzeAgent(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static HomePageObject getHomePageWebAdminHouzeAgent(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static EmployeePageObject getEmployeePageHouzeAgent(WebDriver driver) {
        return new EmployeePageObject(driver);
    }

    public static TrainingPageObject getTrainingPageHouzeAgent(WebDriver driver) {
        return new TrainingPageObject(driver);
    }
}
