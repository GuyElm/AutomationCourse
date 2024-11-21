package APILesson02.Project;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WeatherSearchPage {
    WebDriver driver;

    public WeatherSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Choose The First Result:")
    public void chooseResult(String city) {
        driver.findElement(By.partialLinkText(city)).click();

    }


}
