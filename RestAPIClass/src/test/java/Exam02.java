import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exam02 {
    RequestSpecification httpRequest;
    Response response;
    Response BarackResponse;
    SoftAssert soft;

    WebDriver driver;
    final String API_URL = "https://api.chucknorris.io/jokes/";
    final String WEB_URL = "https://api.chucknorris.io/jokes/sudkgw_tr_ejehjag7cqwq";
    final String EXPECTED_VALUE = "The opening scene of the movie \"Saving Private Ryan\" is loosely based on games of dodgeball Chuck Norris played in second grade.";
    int count = 1;


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
    public void VerifyChuckNorrisAPI() {

        //API
        response = httpRequest.get("categories");           //JSON Path Handling
        JsonPath jp = response.jsonPath();

        List<String> categories = jp.getList("$");      //Making a list from the json into categories
        System.out.println("Categories: ");

        for(String category: categories){
            System.out.println(count + ". " + category);
            count++;
        }
        System.out.println("\n");

        soft.assertEquals(categories.size(),16);                                    //Asserting Count Of Categories (needs to be 16)
        System.out.println("There Are Total Of: " + categories.size() + " Categories");     //Printing the count to console to check

        BarackResponse = httpRequest.get("search?query=Barack Obama");                  //Making new json path for Barack Obama
        JsonPath BarackJP = BarackResponse.jsonPath();

        soft.assertEquals(BarackJP.getString("total"),"8");                   //Asserting numbers of jokes on Obama (needs to be 8)




        //Reference
//    "url": "https://api.chucknorris.io/jokes/sudkgw_tr_ejehjag7cqwq",
//    "value": "The opening scene of the movie \"Saving Private Ryan\" is loosely based on games of dodgeball Chuck Norris played in second grade."



        // WEB
        driver.get(WEB_URL);
        WebElement chuckValue = driver.findElement(By.xpath("//p[@id='joke_value']"));
        String chuckResponse = chuckValue.getText();
        soft.assertEquals(chuckResponse,EXPECTED_VALUE);                            //Got the value in EXPECTED_VALUE as string from the dev tool respond and checking equality to the xpath URL
        soft.assertAll();
    }
}
