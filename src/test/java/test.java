import commons.DataHelper;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
//        DataHelper dataHelper;
//        dataHelper = DataHelper.getData();
//        for (int i = 0; i < 50; i++) {
//            System.out.println(dataHelper.getPhone());
//        }
        String locator, text;
        String[] b;
        By by;
        By result;
        text = "dynamic";
        locator = "testXpath + %s";
        locator = String.format(locator, text);
        b = By.id(locator).toString().split(": ");
        result = By.id(b[1]);
        System.out.println(b[1]);
        System.out.println(result);
    }
}
