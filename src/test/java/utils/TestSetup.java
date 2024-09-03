package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestSetup {
    public static WebDriver setupDriver(String url, String webBrowser){
        WebDriver driver;
        if (webBrowser.equals("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
            }
        else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            }
        driver.get(url);
        return driver;
    }
}
