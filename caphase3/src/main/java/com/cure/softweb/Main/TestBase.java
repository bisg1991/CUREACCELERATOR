package com.cure.softweb.Main;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import customreports.Reports;
import emailReports.SendMailSSLWithAttachment;
import listener.TestNGCustomReportListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;


public class TestBase {
	
	public WebDriver driver;
    public WebActions act;
    public CommonFunctionsLib cfl;
    public Xls_Reader xl;
    public Reports rp;
    public SendMailSSLWithAttachment email;
	public TestNGCustomReportListener tncrl;
	public static final Logger log=Logger.getLogger(TestBase.class.getName());
	public ExtentReports extent;
	ExtentTest test;
	
	public void loginit(){
		String path="/caphase3/Log4j.properties";
		BasicConfigurator.configure();
		PropertyConfigurator.configure(path);
	}
    
	public void selectBrowser(String browser, String URL) {
		   if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				getUrl(URL);
				log.info("===========Browser launched and URL has been entered=============");
				act = new WebActions(driver);
				rp=new Reports(driver);
				email=new SendMailSSLWithAttachment(driver);
				
				
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BROWSERDRIVERS/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				getUrl(URL);
				log.info("===========Browser launched and URL has been entered=============");
				act = new WebActions(driver);
				rp=new Reports(driver);
				email=new SendMailSSLWithAttachment(driver);
				
			}
		}
	
	   // Start Extent Report Method 
		public void startReport()
	    {
			extent =new ExtentReports(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
	 
	        extent.addSystemInfo("OS", "Windows 7 Professional (x64) Service Pack 1 (build 7601)");
	        extent.addSystemInfo("Host Name", "BISWAJIT");
	        extent.addSystemInfo("Environment", "QA");
	        extent.addSystemInfo("User Name", "Biswajit Ghosh");
	        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	    }
		
		// Get Extent Report Method 
		public void getResult(ITestResult result)
		{
			if(result.getStatus() == ITestResult.FAILURE)
	        {
	            
	            test.log(LogStatus.FAIL,result.getName()+" Testcase is Fail ");
	            
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	test.log(LogStatus.PASS,result.getName()+" Testcase is Pass ");
	        }
	        else
	        {
	        	test.log(LogStatus.SKIP,result.getName()+" Testcase is Skipped ");
	        }
		   
		}
		
		
		@BeforeMethod
		public void beforeMehtod(Method method){
			
			
			 test=extent.startTest(method.getName());   
		     test.log(LogStatus.INFO, method.getName()+ "---Test Started---");
		}
		
		
		@AfterMethod
		public void afterMethod(ITestResult result){
			getResult(result);
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
