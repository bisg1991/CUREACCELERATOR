package com.cure.softweb.Main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	public WebDriver driver;
    
	
	public void selectBrowser(String browser, String URL) {
		   if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				getUrl(URL);
				
			} else if (browser.equals("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				getUrl(URL);

			}
		}

	public void getUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}
