package TestSauceDemoNScnShts;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSauceDemo {
    WebDriver driver;
    SoftAssert softAssert;
    final String myUserName = "standard_user";
    final String myPassword = "secret_sauce";
    final String expectedHeader = "Products";
    final int expectedTotalProducts = 6;
    final int expectedMinimumPrice = 5;
    final String expectedBadge = "6";
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        softAssert = new SoftAssert();

        login(myUserName,myPassword);

    }

    @AfterClass
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }





    @Test(description = "Test01 - Verify Login")
    @Description("This test verify login is successful by verifying header")
    public void test01VerifyLogin() {
        verifyHeader(expectedHeader);
    }

    @Step("Sign in")
    public void login(String userName,String password){
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Step("Verify header is as expected")
    public void verifyHeader(String expected){
        String actualHeader = driver.findElement(By.className("title")).getText();
        logToReport("Actual Header: " + actualHeader);
        Assert.assertEquals(actualHeader, expected);
    }


    @Step("{0}")
    public void logToReport(String message){
        System.out.println(message);
    }

    @Test(description = "Test02 - Verify Products")
    @Description("This test verify the total products are as expected")
    public void test02VerifyProducts() {
        List<WebElement> productsElements = driver.findElements(By.cssSelector("div[class*='inventory_item_name']"));
        List<WebElement> productsPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (int i = 0; i < productsElements.size(); i++) {
            String productTitle = productsElements.get(i).getText();
            double productPrice = Double.parseDouble(productsPrices.get(i).getText().replace("$", ""));
            logToReport((i + 1) + ". " + productTitle + " " + productPrice);
            String errorMessage = productTitle+" "+productPrice +" is less than "+expectedMinimumPrice;
            softAssert.assertTrue(expectedMinimumPrice < productPrice,errorMessage);
        }
        int actualTotalProducts = productsElements.size();
        System.out.println("Total Products:" + actualTotalProducts);
        softAssert.assertEquals(actualTotalProducts, expectedTotalProducts);
        softAssert.assertAll();
    }
    @Test(description = "Test03 - Verify Purchase")
    @Description("This test verify the badge is as expcted")
    public void test03VerifyPurchase(){
        List<WebElement> buttons = driver.findElements(By.cssSelector("button[id*='add-to-cart']"));
        for (WebElement button:buttons){
            button.click();
        }
        String actualBadge = driver.findElement(By.className("shopping_cart_badge")).getText();
        softAssert.assertEquals(actualBadge,expectedBadge);
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> productsReceipt = driver.findElements(By.className("inventory_item_name"));
        softAssert.assertEquals(productsReceipt.size(),Integer.parseInt(expectedBadge));
        softAssert.assertAll();
    }

}
