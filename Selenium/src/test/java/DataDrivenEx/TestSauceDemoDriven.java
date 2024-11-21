package DataDrivenEx;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestSauceDemoDriven {
    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterClass
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }
    @DataProvider(name = "users-data")
    public Object[][] getUsersDataProvider(){
        return new Object[][] {
                {"locked_out_user","secret_sauce","fail"},
                {"standard_user","secret_sauce","pass"},
                {"visual_user","secret_sauce","pass"}
        };

    }


    @Test(dataProvider = "users-data")
    public void testVerifyValidLogin(String userName,String password,String expected) {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("user-name")).sendKeys( userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);

        if (expected.equals("fail")){
            String actualErrorMessage =  driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            System.out.println("Actual Error Message: " + actualErrorMessage);
            Assert.assertTrue(actualErrorMessage.contains("Epic sadface"));
        }
        else{
            String actualHeader = driver.findElement(By.className("title")).getText();
            System.out.println("Actual Header: " + actualHeader);
            Assert.assertEquals(actualHeader, "Products");

        }



    }



}
