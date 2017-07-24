package Selenide;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selenide.core.SelenideTestBase;
import selenide.pages.google.GoogleSearchPage;
import selenide.pages.google.GoogleSearchResultPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;



public class GoogleTestSelenide extends SelenideTestBase {

    private String google = "http://google.com/ncr";
    private String searchText = "selenide";



    @Test

    public void searchInGoogleTest() {

        open(google);
//        By searchLocator = By.name ("q"); // это тоже самое что ниже
//        $(searchLocator).val(searchText).pressEnter(); // это тоже самое что ниже
        $(By.name("q")).val(searchText).pressEnter();
        $$(By.xpath("//*[@class='rc']")).shouldHave(size(10));
        $(By.xpath("//*[@class='rc']")).shouldBe(visible).shouldHave(
                text("Selenide: concise UI tests in Java"),
                text("selenide.org"));
    }
    @Test

    public void searchInGoogleWithPageObjectTest(){
        open(google);
        GoogleSearchPage googleSearch = new GoogleSearchPage();
        googleSearch.searchFor(searchText);
        GoogleSearchResultPage googleResult = new GoogleSearchResultPage();
        googleResult.getLinkResults().shouldHave
                (size(10), texts("Selenide: concise UI tests in Java"));
    }
}
