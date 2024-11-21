package PageObjectNoScnSht;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AtidButtonPage {
    WebDriver driver;
    public AtidButtonPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(xpath = "//button[@type='button']")
    private WebElement buttonElement;

    @Step("Get Button Text:")
    public String getButtonText(){
        String buttonText=buttonElement.getText();
        System.out.println("Button Text: "+buttonText);
        return buttonText;
    }
    @Step("Is Button Displayed")
    public boolean isButtonDisplayed(){
        return buttonElement.isDisplayed();
    }







}
