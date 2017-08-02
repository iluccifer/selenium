package selenium.core;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import static selenide.util.PropertiesCache.getProperty;
@Listeners({selenium.core.TestListener.class})
public class WebDriverTestBase {
    protected WebDriver webDriver;
    private long implicitWait = Long.parseLong(getProperty("wait.implicit"));
    private long pageWait = Long.parseLong(getProperty("wait.page"));
    private long scriptWait = Long.parseLong(getProperty("wait.script"));
    public DesiredCapabilities setDesiredCapabilities(String platform, String remoteBrowser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (platform.equalsIgnoreCase("WIN10")) {
            caps.setPlatform(Platform.WIN10);
            caps.setBrowserName(remoteBrowser);
        }
        return caps;
    }
    @Parameters(
            {"platform", "remoteBrowser"})
    @BeforeClass
    public void setUp(@Optional String platform, @Optional String remoteBrowser) throws MalformedURLException {
        switch (remoteBrowser) {
            case "remote":
                DesiredCapabilities caps = setDesiredCapabilities(platform, remoteBrowser);
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                break;
            case BrowserType.CHROME:
                ChromeDriverManager.getInstance().setup();
                webDriver = new ChromeDriver();
                break;
            case BrowserType.FIREFOX:
                FirefoxDriverManager.getInstance().setup();
                webDriver = new FirefoxDriver();
                break;
        }

        webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(scriptWait, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown() {

    }
}