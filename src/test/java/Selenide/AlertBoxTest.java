package advancedinteractions.Selenide;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selenide.core.SelenideTestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AlertBoxTest extends SelenideTestBase {
    String webPageForW3 = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm";
    private String cancelled = "You pressed Cancel!";

    @Test
    public void testToAlertTest() {
        open(webPageForW3);
        switchTo().frame("iframeResult");
        $(By.xpath("html/body/button")).click();
        switchTo().alert().dismiss();
        $(By.id("demo")).shouldHave(text(cancelled));

        //drag and drop
        //$(By.id("demo")).dragAndDropTo($(By.xpath("34ds")));
    }
}