package APILesson01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenWeatherMapEx0102 {
    RequestSpecification httpRequest;
    Response response;
    SoftAssert soft;

    final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    final String CITY = "Jerusalem,IL";
    final String API_KEY = "ad48510a9aed1ff96b51557d94bc5964";
    final String UNITS = "metric";

    @BeforeClass
    public void startSession() {
        RestAssured.baseURI = BASE_URL;
        httpRequest = RestAssured.given();
        soft = new SoftAssert();
    }

    @Test
    public void test01VerifyWeatherData() {
        response = httpRequest.get("?q=" + CITY + "&units=" + UNITS + "&appid=" + API_KEY);
        response.prettyPrint();
        System.out.println("Status Line: " + response.statusLine());
        System.out.println("Response's Date:" + response.getHeader("Date"));
        System.out.println("Content-Type:" + response.contentType());
        System.out.println("Status Code: " + response.statusCode());
        soft.assertTrue(response.contentType().contains("json"));
        soft.assertEquals(response.statusCode(), 200);
        soft.assertAll();
    }


}