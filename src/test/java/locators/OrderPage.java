package locators;

import models.OrderInput;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class OrderPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneNumberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final By orderDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By orderPeriodInput = By.xpath("//div[@class='Dropdown-root Order_FilledDate__1pb8n']");
    private final By colourBlackCheckbox = By.xpath("//*[@id='black']");
    private final By colourGreyCheckbox = By.xpath("//input[@class='Checkbox_Input__14A2w' and @id='grey']");
    private final By orderCommentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");



    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    private void fillName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    private void fillLastName(String lastName){
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    private void fillAddress(String address){
        driver.findElement(addressInput).sendKeys(address);
    }

    private void fillMetro(String metroStation){
        WebElement metroInputElement = driver.findElement(metroInput);
        metroInputElement.click();
        WebElement metroStationDropdown = driver.findElement(By.xpath(String.format(".//div[text()='%s']", metroStation)));
        metroStationDropdown.click();
    }

    private void fillPhone(String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    private void fillOrderDate(String date){
        driver.findElement(orderDateInput).sendKeys(date);
    }

    private void fillOrderPeriod(String period){
        driver.findElement(orderPeriodInput).click();
        driver.findElement(By.xpath(String.format(".//div[text()='%s']", period))).click();
    }

    private void fillColour(String colour){
        if (Objects.equals(colour, "black")){
            driver.findElement(colourBlackCheckbox).click();
        } else {
            driver.findElement(colourGreyCheckbox).click();
        }
    }

    private void fillComments(String comment){
        driver.findElement(orderCommentInput).sendKeys(comment);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderButton));
    }

    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    public void fillClientPage(OrderInput input){
        fillName(input.Name);
        fillLastName(input.LastName);
        fillAddress(input.Address);
        fillMetro(input.MetroStation);
        fillPhone(input.PhoneNumber);
    }

    public void fillOrderPage(OrderInput input){
        fillOrderDate(input.Date);
        //fillOrderPeriod(input.Duration);
        fillColour(input.Colour);
        fillComments(input.Comment);
    }

}
