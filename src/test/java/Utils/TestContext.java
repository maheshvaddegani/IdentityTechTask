package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class TestContext {

    private static TestContext instance = null;
    private Properties envProperties = null;

    public static TestContext getInstance(){
        if(instance == null){
            return new TestContext();
        }else {
            return instance;
        }
    }

    public Properties getEnvironmentProperties(){
        if(envProperties == null){
            envProperties = new Properties();
            FileInputStream ip = null;
            try {
                ip = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\"+"runtime_test" + ".properties");
                envProperties.load(ip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return envProperties;
    }

}