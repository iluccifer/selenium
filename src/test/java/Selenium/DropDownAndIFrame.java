package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class DropDownAndIFrame extends WebDriverTestBase {

    @Test
    public void testDropdown() {
        webDriver.get("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
        WebElement iFrame = webDriver.findElement(By.xpath(".//iframe[@title='3rd party ad content']"));
//        webDriver.switchTo().frame(iFrame);
//        webDriver.switchTo().frame(0);
        webDriver.switchTo().frame("iframeResult");

        //Get the Dropdown as a Select using its name attribute
        WebElement cars = webDriver.findElement(By.xpath("html/body/select"));
        Select carsDropDown = new Select(cars);
        System.out.printf("%s ", carsDropDown.getOptions().size());
        for (WebElement m : carsDropDown.getOptions()) {
            System.out.print(m.getText());
        }

        //Verify Dropdown does not support multiple selection
        assertFalse(carsDropDown.isMultiple());
        Assert.assertEquals(4, carsDropDown.getOptions().size());
        carsDropDown.selectByVisibleText("Volvo");
        Assert.assertEquals("Volvo", carsDropDown.getFirstSelectedOption().getText());
        carsDropDown.selectByValue("opel");
        Assert.assertEquals("Opel", carsDropDown.getFirstSelectedOption().getText());
        carsDropDown.selectByIndex(2);
        Assert.assertEquals("Opel", carsDropDown.getFirstSelectedOption().getText());

        //вернутся в основной HTML
        webDriver.switchTo().defaultContent();
        assertTrue(webDriver.getTitle().equals("Frameset Example Title (Replace this section with your own title)"));
    }
}