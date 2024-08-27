package tests;

import locators.MainPage;
import locators.OrderPage;
import models.Constants;
import models.OrderInput;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;


@RunWith(Parameterized.class)
public class TestOrderPage {
    private WebDriver driver;
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

    private void setup(String url){
        driver = TestSetup.setupDriver(url);
        MainPage.closeCookie(driver);
    }

    @Parameterized.Parameters
    public static String[][] getImportantItems() {
        return new String[][]{
                {"Кек", "Лол", "Бульвар рофлов д.1", "Бульвар Рокоссовского", "111111111111", "25.08.2024", "сутки", "black", "Трололо"},
                {"Лол", "Кек", "Бульвар рофлов д.101", "Охотный Ряд", "211111111112", "27.08.2024", "двое суток", "grey", "Олололо"},
        };
    }

    public void orderTest(){
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
        setup(Constants.testWebsiteOrderUrl);
        orderTest();
    }

    @Test
    public void checkOrderFromLowerButton(){
        setup(Constants.testWebsiteUrl);
        MainPage mainPOM = new MainPage(driver);
        mainPOM.clickLowerOrderButton();
        orderTest();
    }

    @Test
    public void checkCreateOrderFromUpperButton(){
        setup(Constants.testWebsiteUrl);
        MainPage mainPOM = new MainPage(driver);
        mainPOM.clickUpperOrderButton();
        orderTest();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
