package Hooks;
import DriverFactory.DriverFactory;
import io.cucumber.java.After;


public class Hooks {
    @After
    public void tearDown(){
        DriverFactory.getInstance().quit();
    }
}
