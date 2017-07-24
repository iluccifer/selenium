package Selenide;

import selenium.core.WebDriverTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static junit.framework.TestCase.assertTrue;

public class  GoogleSearchTest extends WebDriverTestBase {

    private String googleSearch = "https://www.google.com.ua/";
    private String searchText = "Selenium";

    @Test
    public void searchTest() {
        webDriver.get(googleSearch);
        By searchLocator = By.id("lst-ib");
        WebElement searchField = webDriver.findElement(searchLocator);
        searchField.sendKeys(searchText);
        searchField.submit();

        By linkLocator = By.xpath(".//*[@id='rso']/div[2]/div/div[1]/div/div/h3/a");
        WebElement link = webDriver.findElement(linkLocator);
        assertTrue(link.getText().contains(searchText));


        List <WebElement> elements;
        elements = webDriver.findElements(By.xpath(".//*[@id='rso']/div/div/div/div/div/h3/a"));
       for (WebElement i : elements) {
           System.out.println(i);
           assertTrue(i.isDisplayed());
           assertTrue(i.getText().contains(searchText));
       }
       webDriver.quit();
    }
}
