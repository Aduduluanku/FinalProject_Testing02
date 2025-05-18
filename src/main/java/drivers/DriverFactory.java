package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    public ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public void setDriver(String browser) {
        WebDriver driver = null;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-features=PasswordLeakDetection");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(options);

                ChromeOptions chromeOptions = new ChromeOptions();

                // ✅ Tắt các cảnh báo liên quan đến mật khẩu, bảo mật
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("--disable-features=AutofillServerCommunication,PasswordManagerOnboarding,SafetyTipUI");

                // ✅ Dùng chế độ ẩn danh (không dùng profile lưu mật khẩu)
                chromeOptions.addArguments("--incognito");

                // ✅ Ẩn cảnh báo "Chrome is being controlled by automated software"
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                driver = new ChromeDriver(chromeOptions);

                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("This browser is not supported");
        }

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            threadLocalDriver.set(driver);
        }
    }

    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }
    public WebDriver getDriver() {
        return driver;
    }

}
