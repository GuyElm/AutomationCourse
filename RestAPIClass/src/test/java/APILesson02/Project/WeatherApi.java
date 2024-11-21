package APILesson02.Project;

import io.restassured.path.json.JsonPath;


public class WeatherApi extends BaseWeather {

    public static void getTemperature(String city){
        response = httpRequest.get("?q=" + city + "&units=" + UNITS + "&appid=" + API_KEY);
        JsonPath jp = response.jsonPath();
        String temperature = jp.getString("main.temp");
        System.out.println("Temperature: " + temperature);
    }

    public static String getCountryName(String city){
        response = httpRequest.get("?q=" + city + "&units=" + UNITS + "&appid=" + API_KEY);
        JsonPath jp = response.jsonPath();
        String country = jp.getString("sys.country");
        System.out.println("Country: " + country);
        return country;
    }

    public static String getHumidityFromApi(String city){
        response = httpRequest.get("?q=" + city + "&units=" + UNITS + "&appid=" + API_KEY);
        JsonPath jp = response.jsonPath();

        String humidityFromApi = jp.getString("main.humidity");
        System.out.println("Humidity From API: "+humidityFromApi);

        return humidityFromApi;

    }


}
