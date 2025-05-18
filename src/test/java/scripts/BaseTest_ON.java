package scripts;

import drivers.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest_ON {
    protected WebDriver driver;
    private DriverFactory driverFactory;

    static Logger logger = LogManager.getLogger(BaseTest.class);

    private static final String DEFAULT_BROWSER = "chrome";

    @BeforeMethod
    @Parameters({"browser"})
    public void setupTest(@Optional(DEFAULT_BROWSER) String browser) {
        logger.info("Setting up WebDriver...");
        driverFactory = new DriverFactory();
        driverFactory.setDriver(browser);
        driver = driverFactory.getDriver();
        driver.get("https://olms.codedao.io.vn/login");
        logger.info("Navigated to test site: https://olms.codedao.io.vn/login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing browser and quitting WebDriver...");
            driver.quit();
        }
    }
}