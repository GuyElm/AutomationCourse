package FinalTest;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PizzaOrderPage {
    WebDriver driver;
    public PizzaOrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(xpath = "//span[@class='ginput_total ginput_total_5']")
    private  static WebElement priceLabel;

    @FindBy(xpath = "//select[@id='input_5_21']")
    private static WebElement generalComboBox;

    @FindBy(xpath = "//option[@value='Delivery|3']")
    private static WebElement deliveryOption;

    @FindBy(xpath = "//input[@id='input_5_22_3']")
    private static WebElement firstNameField;

    @FindBy(xpath = "//input[@id='input_5_22_6']")
    private static WebElement lastNameField;

    @FindBy(tagName = "iframe")
    private WebElement couponIframe;

    @FindBy(id = "coupon_Number")
    private static WebElement couponNumberDiv;

    @FindBy(xpath = "//textarea[@id='input_5_20']")
    private static WebElement CommentsField;

    @FindBy(xpath = "//input[@id='gform_submit_button_5']")
    private static WebElement submitButton;

    @Step
    public double getPrice() {
        String priceText = priceLabel.getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    @Step
    public void selectDeliveryOption() {
        generalComboBox.click();
        deliveryOption.click();
    }

    @Step
    public void enterName(String firstName, String lastName) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
    }

    @Step
    public String getCouponCode() {
        driver.switchTo().frame(couponIframe); // Go Inside The Iframe
        String couponCode = couponNumberDiv.getText(); // Get the coupon code
        driver.switchTo().defaultContent(); // Go Outside The Iframe
        return couponCode;
    }

    @Step
    public void enterComments(String comments) {
        CommentsField.sendKeys(comments);
    }

    @Step
    public void submitOrder() {
        submitButton.click();
    }

    @Step
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Step
    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
