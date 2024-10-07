package HWLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LocatorsBasicHW {
    WebDriver driver;

    @BeforeClass
            public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
    }

    @AfterClass
    public void endSession() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void findWikipediaElements(){
        List<WebElement> webElements = new ArrayList<WebElement>();
        WebElement ballLogo =driver.findElement(By.className("central-featured-logo"));  // LOGO
        WebElement searchBar = driver.findElement(By.id("searchInput"));  //Search Bar
        WebElement languageBar = driver.findElement(By.xpath("//div[@class=' styled-select js-enabled']"));  // Language in search bar
        WebElement supportButton = driver.findElement(By.className("footer-sidebar-content"));  // Support content the whole thing

        webElements.add(ballLogo);
        webElements.add(searchBar);
        webElements.add(languageBar);
        webElements.add(supportButton);

        for (int i = webElements.size() - 1; i >= 0 ; i--){
            System.out.println(webElements.get(i));
        }
    }



}
