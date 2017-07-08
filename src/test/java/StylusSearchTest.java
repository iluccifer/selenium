import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

public class StylusSearchTest {

    private String stylusSite = "https://stylus.ua/";
    private String targetUrl = "https://stylus.ua/smartfony/sony-xperia-z2-black-198717.html";
    private String searchText = "Смартфон Sony Xperia Z2";

    private WebDriver webDriver;

    By searchField = By.xpath(".//*[@method='get']/*[@name='q']");
    By selectSmartphoneFrame = By.cssSelector(".item.out-of-stock");


    @Test
    public void stylusSearch() {

        //Preconditions
        System.setProperty("webdriver.chrome.driver",
                "D:\\WebDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        Steps:
//        1.Open https://stylus.ua/ in Chrome
//        2.In the search field enter "Смартфон Sony Xperia Z2" and click find button
        webDriver.get(stylusSite);
        WebElement searchFieldStylus = webDriver.findElement(searchField);
        searchFieldStylus.sendKeys(searchText);
        searchFieldStylus.submit();

//        Steps:
//        3.On the result page verify that Смартфон Sony Xperia Z2 found (choose firs link with Смартфон Sony Xperia Z2 and verify by it's content)
//        4.Click on the first Смартфон Sony Xperia Z2 link
        WebElement link = webDriver.findElement(selectSmartphoneFrame);
        assertTrue(link.getText().contains(searchText));
        System.out.println(searchText);
        link.click();

//        Step:
//        5.Verify that Смартфон Sony Xperia Z2 page is opened
        Assert.assertEquals(targetUrl, webDriver.getCurrentUrl());
        System.out.println(webDriver.getCurrentUrl());

//        Step:
//        6.Browser quit
        webDriver.quit();
    }
}
