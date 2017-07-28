package com.cure.softweb.RUN;

import org.sikuli.script.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cure.softweb.Main.TestBase;
import com.cure.softweb.PageObjects.HOMEPAGE;
import com.cure.softweb.PageObjects.ProposalProjectSettings;

public class RegularProposalPublish extends TestBase {

	HOMEPAGE hp;
	ProposalProjectSettings pps;
	
		 // Using Parameters passing the data
		@Parameters({ "browser", "URL" })
		@BeforeTest
		public void initiateBrowsers(String browser, String URL){
			 selectBrowser(browser, URL);
			 System.out.println("Chrome browser has been initiated");
		}
		
		// DATA PROVIDER for Login credentials
		@DataProvider(name="Login")
		public String[][] getdata1(){
		return getData("LoginData.xlsx", "Sheet2");
		}
		@Test (priority=1, dataProvider="Login")
		public void login(String EmailAddress, String Password) throws Exception{
			
		hp= new HOMEPAGE(driver);
		System.out.println("The driver for the priority test 1 is "+ driver);
		hp.loginmanually(EmailAddress, Password);
}

		
		@Test(priority=2)
		public void publishnormalproposal() throws FindFailed, InterruptedException{
		
			String m="/SikuliScreens/projects.jpg";
			act.clickonimage(m);
			pps=new ProposalProjectSettings(driver);
			String newurl=pps.proposalsuccessmsg();
			driver.get(newurl);
			
}
		


}
