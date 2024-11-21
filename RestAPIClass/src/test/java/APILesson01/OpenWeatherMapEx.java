package APILesson01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenWeatherMapEx {

    RequestSpecification httpRequest;
    Response response;
    SoftAssert soft;

    final String BASE_URL="https://api.openweathermap.org/data/2.5/weather";
    final String DEFAULT_CITY="Jerusalem,IL";
    final String API_KEY="32f46113876afeec31db7766e532bf1c";
    final String DEFAULT_UNITS="metric";

    @BeforeClass
    public void startSession(){
        RestAssured.baseURI=BASE_URL;
        httpRequest=RestAssured.given();
        soft = new SoftAssert();
    }

    @Test
    public void verifyWeatherData(){
        response = httpRequest.get("?q="+ DEFAULT_CITY + "&units="+ DEFAULT_UNITS +"&appid="+ API_KEY);
        response.prettyPrint();
        System.out.println(response.statusLine());
        System.out.println(response.contentType());
        System.out.println(response.getHeader("Date"));
        System.out.println(response.statusCode());
        soft.assertTrue(response.contentType().contains("json"));
        soft.assertEquals(response.statusCode(), 200);
        soft.assertAll();
    }
}
