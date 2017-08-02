package selenide.util;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;

public class PropertiesCache {

    private final Properties configProp = new Properties();
    private static final PropertiesCache INSTANCE = new PropertiesCache();

    private PropertiesCache() {
//Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static String getProperty(String key) {
        return INSTANCE.configProp.getProperty(key);
    }

    public static SelenideElement getSeledineElementProperty(String key, String type) {
        switch (type) {
            case "css": {
                return $(INSTANCE.configProp.getProperty(key));
            }
            default:
                return $(By.xpath(INSTANCE.configProp.getProperty(key)));
        }
    }
}