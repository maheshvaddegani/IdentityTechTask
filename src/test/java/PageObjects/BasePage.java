package PageObjects;
import DriverFactory.DriverFactory;
import Utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

        public static WebDriver driver;
        WebDriverWait webDriverWait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(WaitUtil.IMPLICIT_WAIT));

        protected BasePage() {
            driver = DriverFactory.getInstance().getDriver();
        }

        public void getURL(String appURL) {
            driver = DriverFactory.getInstance().getDriver();
            driver.get(appURL);

        }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void clickElement(By element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void enterTextByElement(By element, String text)
    {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);
    }

    public String getElementText(By element){
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }
}
