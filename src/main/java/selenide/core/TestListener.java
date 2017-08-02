package selenium.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;

import static com.google.common.io.Files.toByteArray;
import static selenide.util.PropertiesCache.getProperty;

public class TestListener implements ITestListener {
    private WebDriver driver;
    private String testScreenshot = getProperty("folder.screenshot");

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    // @Override
// public void onTestFailure(ITestResult iTestResult) {
// driver = ((WebDriverTestBase) iTestResult.getInstance()).webDriver;
// File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// try {
// FileUtils.copyFile(scrFile,
// new File(testScreenshot
// + iTestResult.getMethod().getMethodName() + ".png"));
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        driver = ((WebDriverTestBase) iTestResult.getInstance()).webDriver;
        saveScreenshot(iTestResult.getMethod().getMethodName());
    }

    @Attachment(value = "{0}")
    public byte[] saveScreenshot(String screenshotName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

// для сохранения файлов локально
// FileUtils.copyFile(scrFile,
// new File(testScreenshot + screenshotName + ".png"));

            return toByteArray(scrFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}