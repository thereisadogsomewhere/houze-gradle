package commons.workflows.houzeagent;

import commons.pageobjects.houzeagent.web.TestManagementPageObject;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

public class TestManagementWorkflow {
    public static TestManagementWorkflow testManagementPage(WebDriver driver, Consumer<TestManagementPageObject> admin) {
        TestManagementPageObject testManagementPage = new TestManagementPageObject(driver);
        admin.accept(testManagementPage);
        return new TestManagementWorkflow();
    }
}
