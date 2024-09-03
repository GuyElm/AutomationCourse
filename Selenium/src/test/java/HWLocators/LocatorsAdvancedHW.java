package HWLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsAdvancedHW {
    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void FindElementsInAtidWeb(){
        WebElement firstLocator = driver.findElement(By.id("locator_id"));
        System.out.println(firstLocator);

        WebElement secondLocator = driver.findElement(By.xpath("//span[@name='locator_name']"));
        WebElement thirdLocator = driver.findElement(By.xpath("//*/div[1]/div[1]/p[1]"));
        WebElement forthLocator = driver.findElement(By.className("locator_class"));
        WebElement fifthLocator = driver.findElement(By.linkText("myLocator(5)"));
        WebElement sixthLocator = driver.findElement(By.partialLinkText("(6)"));
        WebElement seventhLocator = driver.findElement(By.xpath("//input[@value='Find my locator (7)']"));
        WebElement eighthLocator = driver.findElement(By.cssSelector("button[class='btn btn-2']"));

        System.out.println(secondLocator);
        System.out.println(thirdLocator);
        System.out.println(forthLocator);
        System.out.println(fifthLocator);
        System.out.println(sixthLocator);
        System.out.println(seventhLocator);
        System.out.println(eighthLocator);
    }
}
