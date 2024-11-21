package PageObjectNoScnSht;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AtidFormPage {
    WebDriver driver;
    public AtidFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(id = "occupation")
    private WebElement occupationField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "location")
    private WebElement locationField;

    @FindBy(css = "input[type='button']")
    private WebElement submitButton;

    @Step("Filling form:")
    public void fillForm(String occupation,String location,String age){
        occupationField.sendKeys(occupation);
        locationField.sendKeys(location);
        ageField.sendKeys(age);
    }
    @Step("Submitting form:")
    public void submitForm(){
        submitButton.click();
    }




}
