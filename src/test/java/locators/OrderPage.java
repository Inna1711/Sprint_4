package locators;

import models.OrderInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.Optional;

public class OrderPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneNumberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    private final By orderDateInput = By.className("react-datepicker__input-container");
    private final By orderPeriodInput = By.className("Dropdown-arrow");
    private final By colourBlackCheckbox = By.xpath("//*[@id='black']");
    private final By colourGreyCheckbox = By.xpath("//input[@class='Checkbox_Input__14A2w' and @id='grey']");
    private final By orderCommentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");

    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By orderNavigationButtons = By.className("Button_Middle__1CSJM");

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
        driver.findElement(orderDateInput).click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", date))).click();
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

    private WebElement getOrderButton() {
        Optional<WebElement> orderButton = driver.findElements(orderNavigationButtons).stream().filter(e -> e.getText().equals("Заказать")).findFirst();
        return orderButton.orElse(null);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(getOrderButton()));
    }

    public void clickOrderButton(){
        getOrderButton().click();
        driver.findElement(By.xpath("//button[text()='Да']")).click();
    }

    public boolean isOrderCreated(){
        try {
            return driver.findElement(By.xpath("//div[text()='Заказ оформлен']")).getText().contains("Заказ оформлен");
        }
        catch (WebDriverException e){
            return false;
        }
    }

    public void fillClientPage(OrderInput input){
        fillName(input.getName());
        fillLastName(input.getLastName());
        fillAddress(input.getAddress());
        fillMetro(input.getMetroStation());
        fillPhone(input.getPhoneNumber());
    }

    public void fillOrderPage(OrderInput input){
        fillOrderDate(input.getDate());
        fillOrderPeriod(input.getDuration());
        fillColour(input.getColour());
        fillComments(input.getComment());
    }

}
