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

public class DragNDropHW {

        WebDriver driver;
        Actions action;
        JavascriptExecutor js;
        @BeforeClass
        public void startSession(){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://marcojakob.github.io/dart-dnd/basic/");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            action = new Actions(driver);
            js = (JavascriptExecutor)driver;
        }

        @AfterClass
        public void closeSession(){
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            driver.quit();
        }

    @Test
    public void DragNDrop(){
          //Draggable (First File)
        WebElement target = driver.findElement(By.xpath("//div[@class='trash']"));                  //Droppable (Trash)
        for (int i=0 ; i<4 ;i++) {
            WebElement source = driver.findElement(By.xpath("(//img[@class='document'])[1]"));      //Draggable Docs (I Want All Of Them To Be In The Trash - Gets The First Place In The List Every Time)
            action.dragAndDrop(source, target).build().perform();
        }
        String actualText = target.getAttribute("class");
        Assert.assertEquals(actualText,"trash full");
    }


}
