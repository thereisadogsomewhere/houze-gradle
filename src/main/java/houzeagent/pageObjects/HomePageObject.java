package houzeagent.pageObjects;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import houzeagent.pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDynamicPageIsDisplayed(String pageTitle) {
        return isPageIsDisplayedByPageTitle(driver, pageTitle);
    }

    public void clickToLanguageDropdownButtonInNavBar() {
        waitElementClickable(driver, HomePageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
        clickToElement(driver, HomePageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
    }

    public void clickToDynamicLanguageButton(String language) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_LANGUAGE_BUTTON, language);
        clickToElement(driver, HomePageUI.DYNAMIC_LANGUAGE_BUTTON, language);
    }

    public void clickToDynamicMenu(String menuName) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_MENU, menuName);
        clickToElement(driver, HomePageUI.DYNAMIC_MENU, menuName);
    }

    public void clickToLogo() {
        waitElementClickable(driver, HomePageUI.LOGO);
        clickToElement(driver, HomePageUI.LOGO);
    }
}
