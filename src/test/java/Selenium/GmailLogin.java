package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;


public class GmailLogin {

    private String gmailLoginLink = "https://mail.google.com/mail/u/0/#inbox";
    private String userEmail = "enter your email";
    private String userPass = "Enter your pass";

    private WebDriver webDriver;

    By emailFieldLocator = By.cssSelector("#identifierId");
    By nextEmailButtonLocator = By.xpath(".//*[@id='identifierNext']");
    By passFieldLocator = By.xpath(".//*[@autocomplete='current-password']");
    By nextPassButtonLocator = By.xpath(".//*[@id='passwordNext']");
    By checkLoginLocatorClick = By.cssSelector(".gb_8a.gbii");
    By checkLoginEmailLocator = By.xpath(".//*[@class='gb_wb']");




    @Test
    public void gmailLoginTest() {

        //Preconditions
        System.setProperty("webdriver.chrome.driver",
                "D:\\WebDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        Steps:
//        1.Open gmailLoginLink in Chrome
//        2.In the email field enter userEmail and click next button
        webDriver.get(gmailLoginLink);
        WebElement emailField = webDriver.findElement(emailFieldLocator);
        emailField.sendKeys(userEmail);
        WebElement nextEmailButton = webDriver.findElement(nextEmailButtonLocator);
        nextEmailButton.click();

//        Steps:
//        3.In the pass field enter userPass and click next button
        WebElement passField = webDriver.findElement(passFieldLocator);
        passField.sendKeys(userPass);
        WebElement nextPassButton = webDriver.findElement(nextPassButtonLocator);
        nextPassButton.click();

//        Step:
//        4.Verify that you logged in succefully and u have your mails.
        WebElement loginlocator = webDriver.findElement(checkLoginLocatorClick);
        loginlocator.click();
        WebElement loginEmail = webDriver.findElement(checkLoginEmailLocator);

        if (loginEmail.getText().contains(userEmail)) {

            System.out.println(userEmail);
        }
        else {
            System.out.println("Login is failed");
        }

        List <WebElement> elements;
        elements = webDriver.findElements(By.xpath(".//*[@jsmodel='nXDxbd']"));
        for (WebElement i : elements) {
            assertTrue(i.isDisplayed());
            System.out.println(i);
        }

//        Step:
//        5.Browser quit
        webDriver.quit();
    }
}