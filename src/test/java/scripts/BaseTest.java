package scripts;

import drivers.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    static WebDriver driver;

    static Logger logger = LogManager.getLogger(BaseTest.class); // Use class reference

    @BeforeMethod
    @Parameters({"browser"})
<<<<<<< HEAD
    public void setupTest(@Optional("chrome") String browser) {

        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setDriver(browser);
        driver = driverFactory.getDriver();
=======
    public void setupTest(String browser) {
        logger.info("Setting up WebDriver...");

//        switch (browser){
//            case "chrome":
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
//            default:
//                System.out.println("This browser is not support");
//        }

        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setDriver("chrome");
        driver = driverFactory.getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
>>>>>>> master
        driver.get("https://olms.codedao.io.vn/login");

        logger.info("Navigated to test site: https://olms.codedao.io.vn/login");
    }


    public WebDriver getDriver() {
        return driver;
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            logger.info("Closing browser and quitting WebDriver...");
//            driver.quit();
//        }
<<<<<<< HEAD
//    }
}
=======
    }
//}
>>>>>>> master
