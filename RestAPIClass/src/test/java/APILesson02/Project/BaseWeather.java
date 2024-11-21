package APILesson02.Project;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class BaseWeather {

    //Assertions
    public static SoftAssert soft;

    //WEB
    public static final String WEB_URL = "https://openweathermap.org/";
    public static WebDriver driver;
    public static WeatherHomePage homePage;
    public static WeatherSearchPage searchPage;
    public static WeatherResultPage resultPage;

    //API
    public static RequestSpecification httpRequest;
    public static Response response;
    public static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    public static final String CITY = "Jerusalem, IL";
    public static final String API_KEY = "ad48510a9aed1ff96b51557d94bc5964";
    public static final String UNITS = "metric";
    public static final String expectedCountry = "IL";

    @BeforeClass
    public void startSession() {
        //WEB
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Init Page Objects:
        homePage = new WeatherHomePage(driver);
        searchPage = new WeatherSearchPage(driver);
        resultPage = new WeatherResultPage(driver);

        //API
        RestAssured.baseURI = API_URL;
        httpRequest = RestAssured.given();

        //Verifications
        soft = new SoftAssert();
    }

    @AfterClass
    public void closeSession() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.quit();
    }
}
