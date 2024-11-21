package CaptainAmericaHW;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CAFormPage {
    WebDriver driver;
    public CAFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath = "//span[@class='hero__primary-text']")
    private static WebElement HeaderTitle;

    @FindBy(xpath = "//div[@class='sc-3a4309f8-0 jJkxPn sc-70a366cc-1 kUfGfN']//span[@class='sc-d541859f-1 imUuxf'][normalize-space()='6.9']")
    private static WebElement MovieScore;

    @Step("Used To return The Movie Header")
    public static String getHeader(){
        return HeaderTitle.getText();
    }

    @Step("Used To retuen The Movie Score")
    public static String getScore(){
        return MovieScore.getText();
    }
}
