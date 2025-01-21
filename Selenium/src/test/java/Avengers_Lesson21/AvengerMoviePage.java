package Avengers_Lesson21;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AvengerMoviePage {
    WebDriver driver;
    public AvengerMoviePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@data-testid='hero__primary-text']")
    public WebElement movieTitleElement;

    @FindBy(xpath = "(//span[@class='sc-d541859f-1 imUuxf'])[1]")
    public WebElement movieScoreElement;

    @Step("Get Movie Score:")
    public String getMovieScore(){
        String movieScore = movieScoreElement.getText();
        System.out.println("Movie Score:"+movieScore);
        return movieScore;
    }

    @Step("Get Movie Title:")
    public String getMovieTitle(){
        String movieTitle = movieTitleElement.getText();
        System.out.println("Movie Title:"+movieTitle);
        return movieTitle;
    }

}
