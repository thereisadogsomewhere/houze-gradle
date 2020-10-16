package commons;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageMobile {
    private WebDriverWait wait;
    private AndroidElement element;
    private Actions actions;

    protected void clickToElement(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        find(driver, xpathLocator).click();
    }

    protected void sendKeyToElement(AndroidDriver<AndroidElement> driver, String xpathLocator, String text) {
        element = find(driver, xpathLocator);
        element.clear();
        element.click();
        waitElementAttributeContains(driver, xpathLocator, "focused", "true");
        actions = new Actions(driver);
        actions.sendKeys(text).perform();
    }

    protected AndroidElement find(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        return driver.findElement(byXpath(xpathLocator));
    }

    protected void waitElementClickable(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathLocator)));
    }

    protected void waitElementVisible(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathLocator)));
    }

    protected void waitElementVisible(AndroidDriver<AndroidElement> driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                byXpath(castToObject(xpathValue, values))));
    }

    protected void waitElementAttributeContains(AndroidDriver<AndroidElement> driver, String xpathLocator, String attribute, String value) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(byXpath(xpathLocator),
                attribute, value));
    }

    protected boolean isElementEnabled(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        return find(driver, xpathLocator).isEnabled();
    }

    protected boolean isElementDisplayed(AndroidDriver<AndroidElement> driver, String xpathLocator) {
        return find(driver, xpathLocator).isDisplayed();
    }

    protected boolean isElementDisplayed(AndroidDriver<AndroidElement> driver, String xpathLocator, String... values) {
        return find(driver, castToObject(xpathLocator, values)).isDisplayed();
    }

    protected String getAttributeElement(AndroidDriver<AndroidElement> driver, String xpathLocator, String attribute) {
        return find(driver, xpathLocator).getAttribute(attribute);
    }

    protected By byXpath(String xpathLocator) {
        return By.xpath(xpathLocator);
    }

    protected String castToObject(String locator, String... values){
        return String.format(locator, (Object[]) values);
    }

    public abstract boolean isAt();
}
