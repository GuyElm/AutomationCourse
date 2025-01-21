package Avengers_Lesson21;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AvengersLoginPage {
    WebDriver driver;
    public AvengersLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy (id = "password")
    private WebElement passwordField;

    @FindBy(id="submit")
    private WebElement submitButton;

    @Step("Login using provided credentials ")
    public void signIn(String userName,String password){
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        submitButton.click();
    }

}
