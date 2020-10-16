package commons;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    private WebDriver driver;
    private AndroidDriver<AndroidElement> appDriver;
    protected final Log log;

    protected AbstractTest() {
        log = LogFactory.getLog(getClass());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public AndroidDriver<AndroidElement> getADriver() {
        return appDriver;
    }

    protected WebDriver getBrowserDriver(String browserName, String appURL) {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else throw new RuntimeException("Please input your browser name!");

        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    protected AndroidDriver<AndroidElement> getAppDriver(String appPackage, String deviceName) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("platform", "Android");
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appPackage + ".MainActivity");

        URL remoteAddress = new URL("http://127.0.0.1:4723/wd/hub/");
        appDriver = new AndroidDriver<>(remoteAddress, cap);
        appDriver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
        return appDriver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Exception e) {
            pass = false;
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Exception e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected int randomNumber() {
        Random rd = new Random();
        return rd.nextInt(99999);
    }

    protected String convertPhoneNumber(String num) {
        String first = String.valueOf(num.charAt(0));
        String converted;
        if (!first.equals("0")) {
            converted = num;
        } else {
            converted = num.substring(1);
        }
        double index = Math.ceil((float) converted.length() / 3);
        int i = 0;
        List<String> a = new ArrayList<>();
        while (i < converted.length()) {
            a.add(converted.substring(i, Math.min(i + 3, converted.length())));
            i = i + 3;
        }
        int j = 0;
        StringBuilder text = new StringBuilder("(+84)");
        if (index >= 4) {
            while (j < 3) {
                text.append(" ").append(a.get(j));
                j++;
            }
            text.append(a.get(j));
        } else {
            while (j < index) {
                text.append(" ").append(a.get(j));
                j++;
            }
        }
        return String.valueOf(text);
    }
}
