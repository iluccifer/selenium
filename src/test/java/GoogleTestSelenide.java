import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;



public class GoogleTestSelenide {

    private String google = "http://google.com/ncr";
    private String searchText = "selenide";



    @Test

    public void searchInGoogle() {
        ChromeDriverManager.getInstance().version("2.30").setup();
//        System.setProperty("selenide.browser", "Chrome");
        Configuration.browser = WebDriverRunner.CHROME;
        open(google);
//        By searchLocator = By.name ("q"); // это тоже самое что ниже
//        $(searchLocator).val(searchText).pressEnter(); // это тоже самое что ниже
        $(By.name("q")).val(searchText).pressEnter();
        $$(By.xpath("//*[@class='rc']")).shouldHave(size(10));
        $(By.xpath("//*[@class='rc']")).shouldBe(visible).shouldHave(
                text("Selenide: concise UI tests in Java"),
                text("selenide.org"));
    }
}
