package com.cure.softweb.Main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import customreports.Reports;
import emailReports.SendMailSSLWithAttachment;
import listener.TestNGCustomReportListener;



public class TestBase {
	
	public WebDriver driver;
    public WebActions act;
    public CommonFunctionsLib cfl;
    public Xls_Reader xl;
    public Reports rp;
    public SendMailSSLWithAttachment email;
	public TestNGCustomReportListener tncrl;
    
	public void selectBrowser(String browser, String URL) {
		   if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				getUrl(URL);
				act = new WebActions(driver);
				rp=new Reports(driver);
				email=new SendMailSSLWithAttachment(driver);
				
				
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				getUrl(URL);
				act = new WebActions(driver);
				rp=new Reports(driver);
				email=new SendMailSSLWithAttachment(driver);
				
			}
		}

	public void getUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public String[][] getData(String excelname, String sheetname){
		String path= System.getProperty("user.dir")+"/src/test/java/com/softweb/cure/excels/"+excelname;
		xl= new Xls_Reader(path);
		String[][] data= xl.getDataFromSheet(sheetname, excelname);
	    return data;
	}
	
	
}
