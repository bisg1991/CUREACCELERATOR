package com.cure.softweb.RUN;

import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cure.softweb.Main.TestBase;
import com.cure.softweb.PageObjects.HOMEPAGE;
import com.cure.softweb.PageObjects.ProposalDiseaseDetail;
import com.cure.softweb.PageObjects.ProposalEvidence;
import com.cure.softweb.PageObjects.ProposalPatentsApprovals;
import com.cure.softweb.PageObjects.ProposalProjectFunding;
import com.cure.softweb.PageObjects.ProposalProjectOverview;
import com.cure.softweb.PageObjects.ProposalProjectSettings;
import com.cure.softweb.PageObjects.ProposalTrialDetails;

public class RegularProposal extends TestBase {
	
	HOMEPAGE hp;
	ProposalProjectOverview sp;
	ProposalProjectFunding ppf;
	ProposalDiseaseDetail pdd;
	ProposalTrialDetails ptd;
	ProposalEvidence pe;
	ProposalPatentsApprovals ppa;
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
			}
		}

	    // DATA PROVIDER for Project Overview
	    @DataProvider(name="Project Overview database")
		public String[][] getdata2(){
			return getData("ProjectOverviewData.xlsx", "Sheet1");
		}
	    @Test (priority=2, dataProvider="Project Overview database") 
	    public void createsp1(String ProjectTitle, String PublicTitle, String PublicAbstract, String Investigator, String Institutions, String Category, String Conditions, String Background, String Treatments, String ResearchDescription) throws Exception{
	    	sp=new ProposalProjectOverview(driver);
	    	System.out.println("The driver for the priority test 2 is "+ driver);
	    	act.clickByJS(sp.btnregularproject);
	    	sp.enterprojectoverviewregular(ProjectTitle, PublicTitle, PublicAbstract, Investigator, Institutions, Category, Conditions, Background, Treatments, ResearchDescription);
	    	
	    }

	  //DATA PROVIDER for Project Funding
	    @DataProvider(name="Project funding data")
	    public String[][] getdata3(){
	    	return getData("ProjectFundingData.xlsx", "Sheet2");
	    }
	    @Test(priority=3, dataProvider="Project funding data")
	    public void createsp2(String ExistingProjFund,String ReqProjFund,String BudgetPersonnel, String BudgetPatientCosts, String BudgetCoreFacilities, String BudgetSupplies, String BudgetOmicData, String BudgetOther, String SpeedUp) throws InterruptedException, IOException{
	    	ppf=new ProposalProjectFunding(driver);
	    	System.out.println("The driver for the priority test 3 is "+ driver);
	    	ppf.enterprojectfundingregular(ExistingProjFund, ReqProjFund, BudgetPersonnel, BudgetPatientCosts, BudgetCoreFacilities, BudgetSupplies, BudgetOmicData, BudgetOther, SpeedUp);
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
	        pdd.enterdiseasedetailregular(EstimatedPrevalance, CurrentTreatment, AnnualDirectHealthcare, AnnualNonHealthcare);   	
	    }

	    // DATA PROVIDER for Trial Details
	    @DataProvider(name="Trial details data")
	    public String[][] getdata5(){
	    	return getData("TrialDetailsData.xlsx", "Sheet2");
	    }
	    @Test(priority=5, dataProvider="Trial details data")
	    public void createsp4(String EstimatedTrialLength, String SafetyConcerns, String HealthImpact, String ReductionInHealthCare, String Upload1, String Upload2, String Upload3, String BioSketch) throws InterruptedException{
	    	ptd =new ProposalTrialDetails(driver);
	    	System.out.println("The driver for the priority test 5 is "+ driver);
	    	String EstimatedTrial=act.stringtointeger(EstimatedTrialLength);
	        ptd.entertrialdetailsregular(EstimatedTrial, SafetyConcerns, HealthImpact, ReductionInHealthCare, Upload1, Upload2, Upload3, BioSketch);
	   
	    }

	    //DATA PROVIDER for Evidence details
	    @DataProvider(name="Evidence details data")
	    public String[][] getdata6(){
	    	return getData("EvidenceData.xlsx", "Sheet1");
	    }
	    @Test(priority=6, dataProvider="Evidence details data")
	    public void createsp5(String ResearchIdea, String Observational) throws InterruptedException{
	    	pe=new ProposalEvidence(driver);
	    	System.out.println("The driver for the priority test 6 is "+ driver);
	    	pe.enterevidenceregular(ResearchIdea, Observational);
	    }


	    //DATA PROVIDER for Patents and approvals details
	    @DataProvider(name="Patents and approval details data")
	    public String[][] getdata7(){
	    	return getData("PatentsAndApproval.xlsx", "Sheet1");
	    }
	    @Test(priority=7, dataProvider="Patents and approval details data")
	    public void createsp6(String Patentissued,String OrphanDrugRegistration,String EMAFDA){
	    	ppa= new ProposalPatentsApprovals(driver);
	    	System.out.println("The driver for the priority test 7 is "+ driver);
	    	ppa.enterpatentdetailsregular(Patentissued, OrphanDrugRegistration);
	    }
	    
	    //DATA PROVIDER for Project Settings
	    @DataProvider(name="Project Settings details data")
	    public String[][] getdata8(){
	    	return getData("ProjectSettingData.xlsx", "Sheet1");
	    }
	    @Test(priority=8,dataProvider="Project Settings details data")
	    public void createsp7(String FIRSTNAME1, String LASTNAME1, String EMAIL1,String FIRSTNAME2,String LASTNAME2,String EMAIL2,String FIRSTNAME3,String LASTNAME3,String EMAIL3) throws InterruptedException{
	    	pps= new ProposalProjectSettings(driver);
	    	System.out.println("The driver for the priority test 8 is "+ driver);
	    	pps.enteremail(FIRSTNAME1, LASTNAME1, EMAIL1, FIRSTNAME2, LASTNAME2, EMAIL2, FIRSTNAME3, LASTNAME3, EMAIL3);
	        pps.proposalsuccessmsg();
	    }
	    
	    
	    @AfterTest
	     public void terminate() throws EmailException, InterruptedException{
	    	 Thread.sleep(3000);
	    	 email.sendTheEmail();
	    	
	     }
	    
}
