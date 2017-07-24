package selenide.pages.google;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Selenide.$$;


public class GoogleSearchResultPage {

    private ElementsCollection linkResult = $$("#ires.g");

    public ElementsCollection getLinkResults(){
        return linkResult;
    }
}
