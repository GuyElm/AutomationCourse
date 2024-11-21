package SauceDemoWListenersNScnSht;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(AutomationListenersWithScreenShots.class)
public class TestSauceDemoWithListeners extends Base {
    final String expectedHeader = "Products";
    final int expectedTotalProducts = 6;
    final int expectedMinimumPrice = 5;
    final String expectedBadge = "6";


    @Test(description = "Test01 - Verify Login")
    @Description("This test verify login is successful by verifying header")
    public void test01VerifyLogin() {
        verifyHeader(expectedHeader);
    }


    @Step("Verify header is as expected")
    public void verifyHeader(String expected){
        String actualHeader = driver.findElement(By.className("title")).getText();
        logToReport("Actual Header: " + actualHeader);
        Assert.assertEquals(actualHeader, expected+"KUKU");
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
