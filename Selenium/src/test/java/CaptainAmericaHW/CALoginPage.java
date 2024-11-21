package CaptainAmericaHW;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CALoginPage {
    WebDriver driver;
    public CALoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(id = "username")
    private static WebElement userNameField;

    @FindBy(id = "password")
    private static WebElement passwordField;

    @FindBy(id = "submit")
    private static WebElement submitButton;

    @Step("Sign in using provided username and password")
    public static void signIn(String userName, String password) {
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        submitButton.click();
    }
}
