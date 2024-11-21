package CaptainAmericaHW;


import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class CABase {

    public static WebDriver driver;
    //Page Objects
    public static CALoginPage CAPage;
    public static CAFormPage CAForm;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://atidcollege.co.il/workshops/avengers/exercise/loginCaptainAmerica.html");

        //Init page objects
        CAPage = new CALoginPage(driver);
        CAForm = new CAFormPage(driver);
    }

    @AfterClass
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }

}

