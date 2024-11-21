package APILesson02.Project;

import org.testng.annotations.Test;

public class TestOpenWeatherMap extends BaseWeather {

    @Test
    public void test01VerifyWeatherData() {

        //API
        WeatherApi.getTemperature(CITY);
        String country = WeatherApi.getCountryName(CITY);
        soft.assertEquals(country, expectedCountry, "Country is not"+expectedCountry);
        String humidityFromApi = WeatherApi.getHumidityFromApi(CITY);

        //WEB
        driver.get(WEB_URL);
        homePage.searchWeather(CITY);
        searchPage.chooseResult(CITY);
        String humidityFromWeb = resultPage.getHumidityFromWeb();
        soft.assertEquals(humidityFromApi,humidityFromWeb,"Humidity from WEB not Equal to Humidity From API");
        soft.assertAll();
    }


}