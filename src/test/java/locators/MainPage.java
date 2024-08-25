package locators;

import org.openqa.selenium.By;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final By upperOrderButton = By.className("Button_Button__ra12g");
    private final By lowerOrderButton = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By importantItemsLocation = By.className("accordion__button");
    private final By importantItemsLocationContainer = By.xpath("//div[@class='accordion__panel']");
    private final By importantItemsLocationText = By.xpath("//div[@class='accordion__panel']/child::p");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickUpperOrderButton(){
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton(){
        driver.findElement(lowerOrderButton).click();
    }

    public String getHiddenImportantItemTextByOrderNum(int orderNumber){
        WebElement importantItem = driver.findElements(importantItemsLocation).get(orderNumber);
        importantItem.click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(String.format("accordion__panel-%d", orderNumber))));
        return driver.findElements(importantItemsLocationText).get(orderNumber).getText();
    }
}
