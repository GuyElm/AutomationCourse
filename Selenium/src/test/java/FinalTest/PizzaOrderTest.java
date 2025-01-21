package FinalTest;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PizzaOrderTest {
    public static WebDriver driver;
    PizzaOrderPage pizzaOrderPage;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://atidcollege.co.il/Xamples/pizza/");
        pizzaOrderPage = new PizzaOrderPage(driver);
    }

    @AfterClass
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test(priority = 1)
    public void testFirstPrice() {
        double initialPrice = pizzaOrderPage.getPrice();
        Assert.assertEquals(initialPrice,7.5);
    }

    @Test(priority = 2)
    public void testDeliveryPriceChange() {
        pizzaOrderPage.selectDeliveryOption();
        double updatedPrice = pizzaOrderPage.getPrice();
        Assert.assertEquals(updatedPrice, 10.5);
    }

    @Test(priority = 3)
    public void testOrderAndAlert() {
        String firstName = "Guy";
        String lastName = "Elmakias";
        String couponCode = pizzaOrderPage.getCouponCode(); //IT WAS IN IFRAME!!!!!!!
        pizzaOrderPage.enterName(firstName, lastName);
        pizzaOrderPage.enterComments(couponCode);
        pizzaOrderPage.submitOrder();

        String actualAlertText = pizzaOrderPage.getAlertText();
        String expectedAlertText = firstName + " " + lastName + " " + couponCode;
        Assert.assertEquals(actualAlertText, expectedAlertText);
        pizzaOrderPage.closeAlert();
    }
}
