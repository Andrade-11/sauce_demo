package saucedemo.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public final class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("start-maximized");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriverWait getDefaultWait() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized, call getDriver() first.");
        }
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
