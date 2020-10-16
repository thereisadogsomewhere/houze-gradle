package commons.workflows.houzeagent;

import commons.pageobjects.houzeagent.web.TestPageObject;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

public class TestWorkflow {
    public static TestWorkflow testPage(WebDriver driver, Consumer<TestPageObject> admin) {
        TestPageObject testPage = new TestPageObject(driver);
        admin.accept(testPage);
        return new TestWorkflow();
    }
}
