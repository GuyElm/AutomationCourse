package Avengers_Lesson21;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AvengersHomePage {
    WebDriver driver;
    public AvengersHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tr[1]/td")
    private List<WebElement> avengersTitles;

    @FindBy(xpath = "//tr[2]/td")
    private List<WebElement> avengersAvatars;




    @Step("Print Avengers")
    public void printAvengers(){
        for(int i =0; i<avengersTitles.size();i++){
            System.out.println("["+(i+1)+"] - "+avengersTitles.get(i).getText());
        }
    }

    @Step("Calculate total Avengers")
    public int getTotalAvengers(){
        return avengersTitles.size();
    }


    @Step("Navigate to avenger:")
    public void navigateToAvenger(int id){
        avengersAvatars.get(id).click();
    }




}
