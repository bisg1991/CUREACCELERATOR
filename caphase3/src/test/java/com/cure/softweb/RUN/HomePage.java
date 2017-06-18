package com.cure.softweb.RUN;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cure.softweb.Main.TestBase;
import com.cure.softweb.PageObjects.HOMEPAGE;

public class HomePage  extends TestBase {
	
	HOMEPAGE hp;
	WebDriver driver;
	@Parameters({ "browser", "URL" })
	@BeforeTest
	public void initiateBrowsers(String browser, String URL){
		selectBrowser(browser, URL);
	}

    @Parameters({"emails","pass"})
	@Test
	public void login(String emails, String pass) throws InterruptedException{
		hp= new HOMEPAGE(driver);
		Thread.sleep(2000);
		hp.clickgoogleicon();
		try {
			hp.loginviagooglebutton(emails, pass);
			hp.verifyafterloginmessage();
		} catch (Exception e) {
			hp.verifyafterloginmessage();
			System.out.println("Already logged in with social account Gmail");
		}
		
	}







}
