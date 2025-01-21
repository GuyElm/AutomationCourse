package Avengers_Lesson21;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestAvengers {
    WebDriver driver;
    AvengersHomePage homePage;
    AvengersLoginPage loginPage;
    AvengerMoviePage moviePage;

    final int expectedTotalAvengers = 4;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/workshops/avengers/exercise/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Init page objects:
        homePage = new AvengersHomePage(driver);
        loginPage = new AvengersLoginPage(driver);
        moviePage = new AvengerMoviePage(driver);
    }
    @AfterClass
    public void closeSession(){
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    public void test01VerifyTotalAvengers(){
        homePage.printAvengers();
        Assert.assertEquals(homePage.getTotalAvengers(),expectedTotalAvengers);
    }



    @Test(dataProvider = "avengers-data",dataProviderClass = MyData.class)
    public void test02VerifyAvengersScore(int id,String userName,String password,String expectedMovieScore){
        driver.get("https://atidcollege.co.il/workshops/avengers/exercise/");
        homePage.navigateToAvenger(id);
        loginPage.signIn(userName,password);
        moviePage.getMovieTitle();
        Assert.assertEquals(moviePage.getMovieScore(),expectedMovieScore);
    }



}
