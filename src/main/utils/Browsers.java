package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Browser manager
 * NOTE: if you want to run test on Windows please add .exe extension to src/Browsers/chromedriver path (code needs to be added to automatically check for OS)
 */

public class Browsers {

    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
            case "Chrome":
                driver = drivers.get("Chrome");
                if (driver == null) {
                    System.setProperty("webdriver.chrome.driver", "src/Browsers/chromedriver");
                    driver = new ChromeDriver();
                    drivers.put("Chrome", driver);
                }
                break;
            case "OtherDrivers":
                break;
        }
        return driver;
    }
}
