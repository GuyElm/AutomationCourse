package HWAsserts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import static org.testng.Assert.assertEquals;

public class SauceDemoAssertHW {

    WebDriver driver;


    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getData("URL"));
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    public String getData (String nodeName){
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmFile = new File("src/test/java/HWAsserts/LoginPage.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try{
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmFile);
        }

        catch (Exception e){
            System.out.println("Exception in reading XML file: "+e);
        }

        doc.getDocumentElement().normalize();
        return  doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    @Test
    public void ExternalFilesHW(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));    //Just adding it here, so I could practice the wait condition more
        driver.findElement(By.id("user-name")).sendKeys(getData("UserName"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));     //Just adding it here, so I could practice the wait condition more
        driver.findElement(By.id("password")).sendKeys(getData("Password"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-button"))); //Just adding it here, so I could practice the wait condition more
        driver.findElement(By.id("login-button")).click();

        String ExpectedHeader = getData("PageHeader");
        String ActualHeader = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();

        assertEquals(ActualHeader, ExpectedHeader);
    }

}
