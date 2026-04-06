package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    @BeforeMethod
    public void setup() {
        prop = ConfigReader.initProp();
        driver = DriverFactory.initDriver();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
