package DataDrivenEx;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DataDrivenExample {
    WebDriver driver;
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterClass
    public void closeSession(){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }


    @Test(dataProvider = "urls-data",dataProviderClass = GlobalProvider.class)
    public void testVerifyNavigation(String url,String expectedTitle){
        driver.get(url);
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: "+actualTitle);
        Assert.assertEquals(actualTitle,expectedTitle);
    }

}
