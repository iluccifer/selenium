package Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class StylusSearchTestSelenide {

    private String stylusSite = "https://stylus.ua/";
    private String targetUrl = "https://stylus.ua/smartfony/sony-xperia-z2-black-198717.html";
    private String searchText = "Смартфон Sony Xperia Z2";


    @Test

    public void stylusSearchSelenide() {
        ChromeDriverManager.getInstance().version("2.30").setup();
        Configuration.browser = WebDriverRunner.CHROME;
        open(stylusSite);
        $(By.xpath(".//*[@method='get']/*[@name='q']")).val(searchText).pressEnter();
        $(By.cssSelector(".item.out-of-stock")).shouldBe(visible).shouldHave(
                text("Смартфон Sony Xperia Z2"));
        System.out.println(searchText);
        $(By.cssSelector(".item.out-of-stock")).click();

        Assert.assertEquals(targetUrl, url());
        System.out.println(url());


    }
}
