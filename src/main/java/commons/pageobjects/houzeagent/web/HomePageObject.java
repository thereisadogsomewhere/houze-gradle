package commons.pageobjects.houzeagent.web;

import commons.AbstractPage;
import commons.AbtractPageUI;
import org.openqa.selenium.WebDriver;
import commons.pageuis.houzeagent.web.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDynamicPageIsDisplayed(String pageTitle) {
        return isPageIsDisplayedByPageTitle(driver, pageTitle);
    }

    public HomePageObject clickToLanguageDropdownButtonInNavBar() {
        waitElementClickable(driver, AbtractPageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
        clickToElement(driver, AbtractPageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
        return this;
    }

    public HomePageObject clickToDynamicLanguageButton(String language) {
        waitElementClickable(driver, AbtractPageUI.DYNAMIC_LANGUAGE_BUTTON, language);
        clickToElement(driver, AbtractPageUI.DYNAMIC_LANGUAGE_BUTTON, language);
        return this;
    }

    public HomePageObject clickToDynamicMenu(String menuName) {
        waitElementClickable(driver, AbtractPageUI.DYNAMIC_MENU, menuName);
        clickToElement(driver, AbtractPageUI.DYNAMIC_MENU, menuName);
        return this;
    }

    public HomePageObject clickToLogo() {
        waitElementClickable(driver, HomePageUI.LOGO);
        clickToElement(driver, HomePageUI.LOGO);
        return this;
    }

    @Override
    public boolean isAt() {
        waitElementClickable(driver, AbtractPageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
        return isElementDisplayed(driver, AbtractPageUI.NAV_BAR_LANGUAGE_DROPDOWN_BUTTON);
    }
}
