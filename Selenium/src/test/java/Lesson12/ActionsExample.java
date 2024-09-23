package Lesson12;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ActionsExample {

    WebDriver driver;
    Actions action;
    JavascriptExecutor js;
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        action = new Actions(driver);
        js = (JavascriptExecutor)driver;
    }

    @AfterClass
    public void closeSession(){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    public void test01VerifyDragAndDrop(){
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        action.dragAndDrop(source,target).build().perform();
        String actualText = target.getText();
        System.out.println("Actual Text: "+actualText);
        Assert.assertEquals(actualText,"Dropped!");
    }
    
    @Test
    public void test02VerifyScrolling(){
        WebElement scrolledElement = driver.findElement(By.id("scrolled_element"));
        js.executeScript("arguments[0].scrollIntoView(true)",scrolledElement);
        Assert.assertTrue(scrolledElement.isDisplayed());
    }


}
