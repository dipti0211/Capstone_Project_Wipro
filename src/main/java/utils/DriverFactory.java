package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver() {
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default to Chrome

            switch (browser) {
                case "chrome":
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}





//package utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import java.util.Scanner;
//
//public class DriverFactory {
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver initializeDriver() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the browser (chrome/firefox/edge): ");
//        String browser = scanner.nextLine().toLowerCase();
//
//        switch (browser) {
//            case "chrome":
//                driver.set(new ChromeDriver());
//                break;
//            case "firefox":
//                driver.set(new FirefoxDriver());
//                break;
//            case "edge":
//                driver.set(new EdgeDriver());
//                break;
//            default:
//                throw new IllegalArgumentException("Browser not supported: " + browser);
//        }
//
//        return driver.get();
//    }
//
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    public static void quitDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}
//
//
