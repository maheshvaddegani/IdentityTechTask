package DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import Utils.WaitUtil;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;


public class DriverFactory {

    private static WebDriver driver = null;

    private static DriverFactory instance = null;
    private DriverFactory()
    {

    }

    public static DriverFactory getInstance() {
        if(instance == null)
            return new DriverFactory();
        else
            return instance;
    }

    public WebDriver getDriver() {
        if(driver == null){
            Properties prop = new Properties();
            FileInputStream ip = null;
            try {
                ip = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\runtime_test.properties");
                prop.load(ip);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String browserName = prop.getProperty("driver");

            switch (browserName.toUpperCase()) {

                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "SAFARI":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new SafariDriver();
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WaitUtil.PAGE_LOAD_TIMEOUT));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitUtil.IMPLICIT_WAIT));

        }

        return driver;
    }

    public void quit(){
        if(driver!= null){
            driver.quit();
            driver = null;
        }
    }

}