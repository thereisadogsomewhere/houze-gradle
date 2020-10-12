package commons;

import houzeagent.pageUIs.EmployeePageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage {
    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    protected void setImplicitWait(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void back(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    protected void sendkeysToAlert(WebDriver driver, String text) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    protected void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(windowID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title))
                break;
        }
    }

    protected void closeAllWindowsWithoutID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    protected String castToObject(String locator, String... values){
        return String.format(locator, (Object[]) values);
    }

    protected By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    protected WebElement find(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    protected List<WebElement> finds(WebDriver driver, String xpathValue) {
        return driver.findElements(byXpath(xpathValue));
    }

    protected void clickToElement(WebDriver driver, String xpathValue) {
        highlightElement(driver, xpathValue);
        find(driver,xpathValue).click();
    }

    protected void clickToElement(WebDriver driver, String xpathValue, String... values) {
        highlightElement(driver, castToObject(xpathValue, values));
        find(driver,castToObject(xpathValue, values)).click();
    }

    protected void sendkeyToElement(WebDriver driver, String xpathValue, String text) {
        highlightElement(driver, xpathValue);
        element = find(driver,xpathValue);
        element.clear();
        element.sendKeys(text);
    }

    protected void sendkeyToElement(WebDriver driver, String xpathValue, String text, String... values) {
        highlightElement(driver, castToObject(xpathValue, values));
        element = find(driver,castToObject(xpathValue, values));
        element.clear();
        element.sendKeys(text);
    }

    protected void selectInDropdownByText(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.selectByVisibleText(itemValue);
    }

    protected void selectInDropdownByText(WebDriver driver, String xpathValue, String itemText, String... values) {
        select = new Select(find(driver,castToObject(xpathValue, values)));
        select.selectByVisibleText(itemText);
    }

    protected void selectInDropdownByValue(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.selectByValue(itemValue);
    }

    protected void selectInDropdownByValue(WebDriver driver, String xpathValue, String itemValue, String... values) {
        select = new Select(find(driver,castToObject(xpathValue, values)));
        select.selectByValue(itemValue);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.getFirstSelectedOption().getText();
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String xpathValue, String... values) {
        select = new Select(find(driver, castToObject(xpathValue, values)));
        return select.getFirstSelectedOption().getText();
    }

    protected Boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.isMultiple();
    }

    protected void selectItemsInCustomDropdown(WebDriver driver, String parentLocator, String itemLocator, String expectedItem) {
        find(driver, parentLocator).click();

        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(itemLocator)));

        elements = finds(driver, itemLocator);

        for (WebElement item : elements) {
            String actualItem = item.getText();
            if (actualItem.equals(expectedItem)) {
                js.executeScript("arguments[0]. scrollIntoView(true);", item);
                wait.until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                break;
            }
        }
    }

    protected String getElementAttribute(WebDriver driver, String xpathValue, String attributeName) {
        return find(driver, xpathValue).getAttribute(attributeName);
    }

    protected String getElementAttribute(WebDriver driver, String xpathValue, String attributeName, String... values) {
        return find(driver, castToObject(xpathValue, values)).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String xpathValue) {
        highlightElement(driver, xpathValue);
        return find(driver, xpathValue).getText();
    }

    protected String getElementText(WebDriver driver, String xpathValue, String... values) {
        highlightElement(driver, castToObject(xpathValue, values));
        return find(driver, castToObject(xpathValue, values)).getText();
    }

    protected int countElementsNumber(WebDriver driver, String xpathValue) {
        return finds(driver, xpathValue).size();
    }

    protected int countElementsNumber(WebDriver driver, String xpathValue, String... values) {
        return finds(driver, castToObject(xpathValue, values)).size();
    }

    protected void checkTheCheckbox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkTheCheckbox(WebDriver driver, String xpathValue, String... values) {
        element = find(driver, castToObject(xpathValue, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckTheCheckbox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue) {
        try {
            highlightElement(driver, xpathValue);
            element = find(driver, xpathValue);
            return element.isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
            return false;
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
        try {
            highlightElement(driver, castToObject(xpathValue, values));
            return find(driver, castToObject(xpathValue, values)).isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.getMessage();
            return false;
        }

    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected Boolean isElementUndisplayed(WebDriver driver, String xpathValue) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        elements = finds(driver, xpathValue);

        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return !elements.get(0).isDisplayed();
        }
    }

    protected Boolean isElementUndisplayed(WebDriver driver, String xpathValue, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        elements = finds(driver, castToObject(xpathValue, values));

        if (elements.size() == 0) {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return true;
        } else {
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
            return !elements.get(0).isDisplayed();
        }
    }

    protected Boolean isElementSelected(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isSelected();
    }

    protected Boolean isElementSelected(WebDriver driver, String xpathValue, String... values) {
        return find(driver, castToObject(xpathValue, values)).isSelected();
    }

    protected Boolean isElementEnabled(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isEnabled();
    }

    protected void switchToFrameOrIframe(WebDriver driver, String xpathValue) {
        driver.switchTo().frame(find(driver, xpathValue));
    }

    protected void switchToDefaultContents(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String xpathValue) {
        actions = new Actions(driver);
        actions.moveToElement(find(driver, xpathValue)).perform();
    }

    protected void hoverToElement(WebDriver driver, String xpathValue, String... values) {
        actions = new Actions(driver);
        actions.moveToElement(find(driver,castToObject(xpathValue, values))).perform();
    }

    protected void doubleClickToElement(WebDriver driver, String xpathValue) {
        actions = new Actions(driver);
        actions.doubleClick(find(driver, xpathValue)).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys keys) {
        actions = new Actions(driver);
        actions.sendKeys(find(driver, xpathValue), keys).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys keys, String... values) {
            actions = new Actions(driver);
            actions.sendKeys(find(driver, castToObject(xpathValue,values)),
                    keys).perform();
        }

    protected void highlightElement(WebDriver driver,String xpathValue) {
        element = find(driver, xpathValue);
        String originalStyle = element.getAttribute("style");
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element, "style", "border: 5px solid red; border-style: dashed;");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, xpathValue));
    }

    protected void scrollToElement(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String xpathValue, String attributeName) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + attributeName + "')",
                find(driver, xpathValue));
    }

    protected void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeName) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attributeName + "');",
                find(driver, xpathValue));
    }

    protected boolean isImageLoaded(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        boolean status = (boolean) js.executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth >0",
                find(driver, xpathValue));
        return status;
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                byXpath(castToObject(xpathValue, values))));
    }

    protected void waitElementsVisible(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(
                castToObject(xpathValue, values))));
    }

    protected void waitAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    protected void waitElementsInvisible(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        elements = finds(driver, xpathValue);
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(xpathValue, values))));
    }

    protected Boolean isElementsSortedInAlphabetAscending(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        ArrayList<String> elementTextList = new ArrayList<>();
        elements = finds(driver, xpathValue);
        for (WebElement element: elements) {
            elementTextList.add(element.getText());
        }

        ArrayList<String> sortedElementTextList = new ArrayList<>(elementTextList);

        Collections.sort(sortedElementTextList);
        return sortedElementTextList.equals(elementTextList);
    }

    public boolean isPageIsDisplayedByPageTitle(WebDriver driver, String pageTitle) {
        return getPageTitle(driver).equals(pageTitle);
    }

    protected Boolean isElementsSortedInAlphabetDescending(WebDriver driver, String xpathValue) {
        ArrayList<String> elementTextList = new ArrayList<>();
        elements = finds(driver, xpathValue);
        for (WebElement element: elements) {
            elementTextList.add(element.getText());
        }

        ArrayList<String> sortedElementTextList = new ArrayList<>(elementTextList);

        Collections.sort(sortedElementTextList);
        Collections.reverse(sortedElementTextList);
        return sortedElementTextList.equals(elementTextList);
    }

    protected void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        StringBuilder fullFileName = new StringBuilder();
        for (String file : fileNames) {
            fullFileName.append(GlobalConstants.UPLOAD_FOLDER).append(file).append("\n");
        }
        fullFileName = new StringBuilder(fullFileName.toString().trim());
        sendkeyToElement(driver, AbtractPageUI.UPLOAD_FILE_TYPE, fullFileName.toString());
    }

    public void clickToActionDropdownButton(WebDriver driver, String rowNumber) {
        waitElementClickable(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_BUTTON, rowNumber);
        clickToElement(driver, AbtractPageUI.DYNAMIC_ACTION_DROPDOWN_BUTTON, rowNumber);
    }

    public void clickToDynamicActionDropdownItem(WebDriver driver, String action) {
        waitElementClickable(driver, EmployeePageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
        clickToElement(driver, EmployeePageUI.DYNAMIC_ACTION_DROPDOWN_ITEM, action);
    }

//    protected Boolean areFilesUploadedDisplayed(WebDriver driver, String... fileNames) {
//        boolean status = false;
//        int number = fileNames.length;
//        waitElementsInvisible(driver, MediaPageUI.PROGRESS_BAR_UPLOAD);
//        waitElementsVisible(driver, MediaPageUI.ALL_UPLOADED_IMG);
//        elements = finds(driver, MediaPageUI.ALL_UPLOADED_IMG);
//        List<String> imgValue = new ArrayList<>();
//
//        int i = 0;
//        for(WebElement image: elements) {
//            imgValue.add(image.getAttribute("src"));
//            i++;
//            if(i == number) break;
//        }
//
//        for (String fileName: fileNames) {
//            String[] files = fileName.split("\\.");
//            fileName = files[0].toLowerCase();
//            for (i = 0; i < imgValue.size(); i++) {
//                if (!imgValue.get(i).contains(fileName)) {
//                    status = false;
//                    if (i == imgValue.size() - 1) {
//                        return status;
//                    }
//                } else {
//                    status = true;
//                    break;
//                }
//            }
//        }
//        return status;
//    }

    protected void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Alert alert;
    private WebElement element;
    private Select select;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private List<WebElement> elements;
    private Actions actions;
}
