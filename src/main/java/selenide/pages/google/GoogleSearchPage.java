package selenide.pages.google;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage {

   private SelenideElement search = $(By.name("q"));

   public GoogleSearchPage searchFor(String searchText){
       search.val(searchText).pressEnter();
       return this;
   }

   public GoogleSearchPage clickSearch(){
       search.pressEnter();
       return this;
   }
}
