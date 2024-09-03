package Lesson01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverExample {

    WebDriver driver;
    String currentPageTitle;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void endSession(){
        driver.quit();
    }

    @Test
    public void test01() throws InterruptedException {


        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.get("https://Bing.com");
        Thread.sleep(3000);
        driver.navigate().back();
        System.out.println("The Web Page Title Is: " + driver.getTitle());
        if(driver.getPageSource().contains("bubble")){
            System.out.println("Exist!");
        }
        else {
            System.out.println("Not Exist!");
        }

        Thread.sleep(3000);

    }
}
