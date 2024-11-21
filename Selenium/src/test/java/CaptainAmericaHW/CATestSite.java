package CaptainAmericaHW;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CAListeners.class)
 public class CATestSite extends CABase{
    //Variables
    final String myUserName = "CaptainAmerica";
    final String myPassword = "CaptainAmericaRocks";
    final String expectedHeader = "Captain America: The First Avenger";
    final String expectedScore = "6.9";

    @Test(description = "Test - Verify Captain America Title")
    @Description("This test verify the title")
    public void test01VerifyTitle() {
        CALoginPage.signIn(myUserName, myPassword);
        //Passing Regular
        verifyHeader(expectedHeader);
        printHeaderTitle();
        verifyMovieScore();
    }

    @Step("Verify header is as expected")
    public void verifyHeader(String expected){
        String headerResult = CAFormPage.getHeader();
        logToReport("Movie Title: " + headerResult);
        Assert.assertEquals(headerResult, expectedHeader);
    }

    @Step("Print the header")
    public void printHeaderTitle(){
        String actualHeader = CAFormPage.getHeader();
        System.out.println(actualHeader);
    }

    @Step("Verify that the score equals to 6.9")
    public void verifyMovieScore(){
        String actualScore = CAFormPage.getScore();
        logToReport("Actual Movie Score: " + actualScore);
        Assert.assertEquals(actualScore, expectedScore);
    }


    @Step("{0}")
    public void logToReport(String message){
        System.out.println(message);
    }
}
