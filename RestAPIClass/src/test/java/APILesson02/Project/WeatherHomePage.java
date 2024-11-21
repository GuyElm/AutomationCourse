package APILesson02.Project;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherHomePage {
    WebDriver driver;
    public WeatherHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchElement;

    @Step("Search Weather in a given City or Country")
    public void searchWeather(String city){
        searchElement.sendKeys(city);
        searchElement.sendKeys(Keys.ENTER);
    }


}
