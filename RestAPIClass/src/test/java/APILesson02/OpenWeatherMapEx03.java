package APILesson02;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class OpenWeatherMapEx03 {
    RequestSpecification httpRequest;
    Response response;
    SoftAssert soft;

    //WEB
    final String WEB_URL="https://openweathermap.org/";
    WebDriver driver;
    //API
    final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    final String CITY = "Jerusalem, IL";
    final String API_KEY = "32f46113876afeec31db7766e532bf1c";
    final String UNITS = "metric";
    final String expectedCountry = "IL";

    @BeforeClass
    public void startSession() {
        //WEB
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //API
        RestAssured.baseURI = API_URL;
        httpRequest = RestAssured.given();
        soft = new SoftAssert();
    }

    @AfterClass
    public void closeSession(){
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    public void test01VerifyWeatherData() {

        //API
        response = httpRequest.get("?q=" + CITY + "&units=" + UNITS + "&appid=" + API_KEY);
        response.prettyPrint();
        JsonPath jp = response.jsonPath();
        String temperature = jp.getString("main.temp");
        System.out.println("Temperature: " + temperature);
        String country = jp.getString("sys.country");
        System.out.println("Country: " + country);
        soft.assertEquals(country, expectedCountry, "Country is not"+expectedCountry);
        String humidityFromApi = jp.getString("main.humidity");
        System.out.println("Humidity From API: "+humidityFromApi);
        //WEB
        driver.get(WEB_URL);
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(CITY);
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
        driver.findElement(By.partialLinkText(CITY)).click();
        String valueBeforeSplit = driver.findElement(By.xpath("//span[text()='Humidity:']/..")).getText();
        String humidityFromWeb = valueBeforeSplit.split("\n")[1].replace("%","");
        System.out.println("Humidity From WEB: "+humidityFromWeb);
        soft.assertEquals(humidityFromApi,humidityFromWeb,"Humidity from WEB not Equal to Humidity From API");
        soft.assertAll();
    }


}