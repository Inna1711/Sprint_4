package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieKiller {
    public static void CloseCookie(WebDriver driver){
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }
}
