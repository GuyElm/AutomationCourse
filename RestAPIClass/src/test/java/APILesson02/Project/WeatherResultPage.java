package APILesson02.Project;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherResultPage {
    WebDriver driver;

    public WeatherResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Humidity:']/..")
    WebElement humidityElement;

    @Step("Get Humidity Value From Web:")
    public String getHumidityFromWeb(){
        String valueBeforeSplit = humidityElement.getText();
        String humidityFromWeb = valueBeforeSplit.split("\n")[1].replace("%","");
        System.out.println("Humidity From WEB: "+humidityFromWeb);
        return  humidityFromWeb;
    }


}
