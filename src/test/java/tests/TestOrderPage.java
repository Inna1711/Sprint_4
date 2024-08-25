package tests;

import locators.MainPage;
import locators.OrderPage;
import models.OrderInput;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utils.CookieKiller;
import utils.TestSetup;


@RunWith(Parameterized.class)
public class TestOrderPage {
    private final String name;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;

    public TestOrderPage(
            String name,
            String lastName,
            String address,
            String metroStation,
            String phoneNumber,
            String date,
            String duration,
            String colour,
            String comment
    ){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getImportantItems() {
        return new Object[][]{
                {"Кек", "Лол", "Бульвар рофлов д.1", "Бульвар Рокоссовского", "111111111111", "25.08.2024", "сутки", "black", "Трололо"},
                {"Лол", "Кек", "Бульвар рофлов д.101", "Охотный Ряд", "211111111112", "27.08.2024", "двое суток", "grey", "Олололо"},
        };
    }
    public void orderTest(WebDriver driver){
        OrderInput input = new OrderInput(
                name,
                lastName,
                address,
                metroStation,
                phoneNumber,
                date,
                duration,
                colour,
                comment
        ) ;
        OrderPage orderPOM = new OrderPage(driver);
        orderPOM.fillClientPage(input);
        orderPOM.clickNextButton();
        orderPOM.fillOrderPage(input);
        orderPOM.clickOrderButton();
    }

    @Test
    public void checkOrderCreate(){
        WebDriver driver = TestSetup.setupDriver("https://qa-scooter.praktikum-services.ru/order");
        CookieKiller.CloseCookie(driver);
        orderTest(driver);
        driver.quit();
    }

    @Test
    public void checkOrderFromLowerButton(){
        WebDriver driver = TestSetup.setupDriver("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPOM = new MainPage(driver);
        CookieKiller.CloseCookie(driver);
        mainPOM.clickLowerOrderButton();
        orderTest(driver);
        driver.quit();
    }

    @Test
    public void checkCreateOrderFromUpperButton(){
        WebDriver driver = TestSetup.setupDriver("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPOM = new MainPage(driver);
        CookieKiller.CloseCookie(driver);
        mainPOM.clickUpperOrderButton();
        orderTest(driver);
        driver.quit();
    }
}
