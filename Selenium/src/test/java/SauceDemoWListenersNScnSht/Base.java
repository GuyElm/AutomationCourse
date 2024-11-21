package SauceDemoWListenersNScnSht;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Base {
   public static WebDriver driver;
   public static SoftAssert softAssert;
   final String myUserName = "standard_user";
   final String myPassword = "secret_sauce";

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

    @Step("Sign in")
    public static void login(String userName,String password){
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

}
