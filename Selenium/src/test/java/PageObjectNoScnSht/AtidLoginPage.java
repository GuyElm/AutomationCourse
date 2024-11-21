package PageObjectNoScnSht;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AtidLoginPage {
    WebDriver driver;
    public AtidLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(name = "username2")
    private WebElement userNameField;

    @FindBy(name = "password2")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @Step("Sign in using provided username and password")
    public void signIn(String userName, String password) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        submitButton.click();
    }

}
