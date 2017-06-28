package com.cure.softweb.RUN;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cure.softweb.Main.TestBase;
import com.cure.softweb.PageObjects.HOMEPAGE;
import com.cure.softweb.PageObjects.ProposalDiseaseDetail;
import com.cure.softweb.PageObjects.ProposalProjectFunding;
import com.cure.softweb.PageObjects.ProposalProjectOverview;
import com.cure.softweb.PageObjects.ProposalTrialDetails;

public class HomePage  extends TestBase {
	
	HOMEPAGE hp;
	ProposalProjectOverview sp;
	ProposalProjectFunding ppf;
	ProposalDiseaseDetail pdd;
	ProposalTrialDetails ptd;
	public int indexSI = 1;
	
	// Using Parameters passing the data
	@Parameters({ "browser", "URL" })
	@BeforeTest
	public void initiateBrowsers(String browser, String URL){
		 selectBrowser(browser, URL);
	}

	// DATA PROVIDER for Login credentials
	@DataProvider(name="Login")
	public String[][] getdata1(){
	return getData("LoginData.xlsx", "Sheet1");
	}
    @Test (priority=1, dataProvider="Login")
	public void login(String emails, String pass) throws Exception{
    	
		hp= new HOMEPAGE(driver);
		System.out.println("The driver for the priority test 1 is "+ driver);
		//act.click(hp.ele1);
		//act.performClick(hp.ele1, driver);
		hp.ele1.click();
		try {
			hp.loginviagooglebutton(emails, pass);
			//hp.verifyafterloginmessage();
			rp.updateResult(1, "HomePage", "PASS", "login");
		} catch (Exception e) {
			hp.verifyafterloginmessage();
			System.out.println("Already logged in with social account Gmail");
			rp.updateResult(1, "HomePage", "FAIL", "login");
		}
	}
   
    // DATA PROVIDER for Project Overview
    @DataProvider(name="Project Overview database")
	public String[][] getdata2(){
		return getData("ProjectOverviewData.xlsx", "Sheet1");
	}
    @Test (priority=2, dataProvider="Project Overview database") 
    public void createsp1(String ProjectTitle, String PublicTitle, String PublicAbstract, String Investigator, String Institutions, String Category, String Conditions, String Background, String Treatments) throws Exception{
    	sp=new ProposalProjectOverview(driver);
    	System.out.println("The driver for the priority test 2 is "+ driver);
    	act.clickByJS(sp.btnspecialproposal);
    	sp.enterprojectoverview(ProjectTitle, PublicTitle, PublicAbstract, Investigator, Institutions, Category, Conditions, Background, Treatments);
    	rp.updateResult(1, "HomePage", "PASS", "createsp1");
    }
    
    //DATA PROVIDER for Project Funding
    @DataProvider(name="Project funding data")
    public String[][] getdata3(){
    	return getData("ProjectFundingData.xlsx", "Sheet1");
    }
    @Test(priority=3, dataProvider="Project funding data")
    public void createsp2(String ExistingProjFund,String ReqProjFund,String BudgetPersonnel, String BudgetPatientCosts, String BudgetCoreFacilities, String BudgetSupplies, String BudgetOmicData, String BudgetOther) throws InterruptedException, IOException{
    	ppf=new ProposalProjectFunding(driver);
    	System.out.println("The driver for the priority test 3 is "+ driver);
    	ppf.enterprojectfunding(ExistingProjFund, ReqProjFund, BudgetPersonnel, BudgetPatientCosts, BudgetCoreFacilities, BudgetSupplies, BudgetOmicData, BudgetOther);
    	rp.updateResult(1, "HomePage", "PASS", "createsp2");
    }
    
    //DATA PROVIDER for Disease Details
    @DataProvider(name="Disease detail data")
    public String[][] getdata4(){
    	return getData("DiseaseDetailsData.xlsx", "Sheet1");
    }
    @Test(priority=4, dataProvider="Disease detail data")
    public void createsp3(String EstimatedPrevalance, String CurrentTreatment, String AnnualDirectHealthcare, String AnnualNonHealthcare) throws InterruptedException{
    	pdd=new ProposalDiseaseDetail(driver);
    	System.out.println("The driver for the priority test 4 is "+ driver);
        pdd.enterdieseasedetail(EstimatedPrevalance, CurrentTreatment, AnnualDirectHealthcare, AnnualNonHealthcare);    	
    }
   
    @DataProvider(name="Trial details data")
    public String[][] getdata5(){
    	return getData("TrialDetailsData.xlsx", "Sheet1");
    }
    @Test(priority=5, dataProvider="Trial details data")
    public void createsp4(String TreatmentArm1,String EstimatedTrialLength,String ActiveCare, String FollowUp, String SafetyConcerns, String HealthImpact, String ReductionInHealthCare, String Upload1, String Upload2, String Upload3, String BioSketch) throws InterruptedException{
    	ptd =new ProposalTrialDetails(driver);
    	System.out.println("The driver for the priority test 5 is "+ driver);
    	ptd.entertrialdetails(TreatmentArm1,EstimatedTrialLength,ActiveCare, FollowUp,SafetyConcerns, HealthImpact, ReductionInHealthCare, Upload1, Upload2, Upload3, BioSketch);
    }
    
    
    /* @AfterTest
     public void terminate(){
    	 
    	
     }*/


}
