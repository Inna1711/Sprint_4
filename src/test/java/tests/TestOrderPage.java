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

import static org.junit.Assert.assertTrue;


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
    private final String webBrowser;

    public TestOrderPage(
            String name,
            String lastName,
            String address,
            String metroStation,
            String phoneNumber,
            String date,
            String duration,
            String colour,
            String comment,
            String webBrowser
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
        this.webBrowser = webBrowser;
    }

    private void setup(String url){
        driver = TestSetup.setupDriver(url, webBrowser);
        MainPage.closeCookie(driver);
    }

    @Parameterized.Parameters
    public static String[][] getImportantItems() {
        return new String[][]{
                {"Кек", "Лол", "Бульвар рофлов д.1", "Бульвар Рокоссовского", "111111111111", "25", "сутки", "black", "Трололо", "firefox"},
                {"Лол", "Кек", "Бульвар рофлов д.101", "Охотный Ряд", "211111111112", "27", "двое суток", "grey", "Олололо", "firefox"},
                {"Кек", "Лол", "Бульвар рофлов д.1", "Бульвар Рокоссовского", "111111111111", "25", "сутки", "black", "Трололо", "chrome"},
                {"Лол", "Кек", "Бульвар рофлов д.101", "Охотный Ряд", "211111111112", "27", "двое суток", "grey", "Олололо", "chrome"},
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
        assertTrue("Заказ не был создан!", orderPOM.isOrderCreated());
    }

    @Test
    public void checkOrderCreate(){
        setup(Constants.TEST_WEBSITE_ORDER_URL);
        orderTest();
    }

    @Test
    public void checkOrderFromLowerButton(){
        setup(Constants.TEST_WEBSITE_URL);
        MainPage mainPOM = new MainPage(driver);
        mainPOM.clickLowerOrderButton();
        orderTest();
    }

    @Test
    public void checkCreateOrderFromUpperButton(){
        setup(Constants.TEST_WEBSITE_URL);
        MainPage mainPOM = new MainPage(driver);
        mainPOM.clickUpperOrderButton();
        orderTest();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
