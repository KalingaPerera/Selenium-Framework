package com.sf.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties properties;
	String browser;
	FileInputStream fis;
	ChromeOptions chromeOptions;
	FirefoxOptions firefoxOptions;
	EdgeOptions edgeOptions;

	public BaseTest() {

		try {
			properties = new Properties();
			fis = new FileInputStream(
					System.getProperty("user.dir", "\\src\\main\\java\\com\\sf\\qa\\config\\config.properties"));
			properties.load(fis);
			browser = System.getLogger("browser") != null ? System.getenv("browser")
					: properties.getProperty("browser");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeDriver() throws IOException {

		browser = properties.getProperty("browser");

		if (browser.contains("chrome")) {
			chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browser.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();

			if (browser.contains("headless")) {
				firefoxOptions.addArguments("headless");
			}
			driver = new EdgeDriver();
		} else if (browser.contains("edge")) {
			edgeOptions = new EdgeOptions();
			WebDriverManager.edgedriver().setup();

			if (browser.contains("headless")) {
				edgeOptions.addArguments("headless");
			}
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get(properties.getProperty("url"));
		return driver;
	}
}
