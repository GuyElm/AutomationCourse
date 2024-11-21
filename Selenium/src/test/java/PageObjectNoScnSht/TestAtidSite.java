package PageObjectNoScnSht;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestAtidSite {
    WebDriver driver;
    //Page Objects
    AtidLoginPage loginPage;
    AtidFormPage atidFormPage;
    AtidButtonPage atidButtonPage;
    //Variables
    final String myUserName = "selenium";
    final String myPassword = "webdriver";
    final String myOccupation = "QA Automation";
    final String myLocation = "Tel Aviv";
    final String myAge = "99";


    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        //Init page objects
        loginPage = new AtidLoginPage(driver);
        atidFormPage = new AtidFormPage(driver);
        atidButtonPage = new AtidButtonPage(driver);
    }
    @AfterClass
    public void closeSession(){
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        driver.quit();
    }
    @Test(description = "Test - Verify Button is displayed")
    @Description("This test verify button is displayed after filling form")
    public void test01VerifyButtonDisplayed(){
        loginPage.signIn(myUserName,myPassword);
        atidFormPage.fillForm(myOccupation,myLocation,myAge);
        atidFormPage.submitForm();
        atidButtonPage.getButtonText();
        Assert.assertTrue(atidButtonPage.isButtonDisplayed());
    }

}
