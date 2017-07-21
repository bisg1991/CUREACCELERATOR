package com.cure.softweb.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalTrialDetails {
	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalTrialDetails(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo= new ProposalProjectOverview(driver);
	}

	
	public static final Logger ltrialdetail= Logger.getLogger(ProposalTrialDetails.class.getName());
	
	
	// Trial Details Section
	
	//Project Type checkbox
	By checkbxprojtype= By.xpath("//input[@id='json_project_type_0' and @value='Human Clinical Trial' and @type='checkbox']");
	
	//Lab checkbox
	By checkbxlab= By.xpath("//input[@id='json_project_type_3' and @value='Lab']");
	
	//Number of patients to be treated.
	By txttreatmentarm1=By.xpath("//input[@id='json_treatment_arm_0' and @name='json_treatment_arm[0]']");
	
	//Estimated Trial Length (FOR SPECIAL PROPOSAL & REGULAR PROPOSAL)
	By txtestimatedtrialLength= By.xpath("//input[@id='project_length' and @type='number']");
	
	//Length of Active Care
	By txtactivecare= By.xpath("//input[@id='length_of_active_care_duration']");
	
	//Length of Follow up
	By txtfollowup= By.xpath("//input[@id='length_of_follow_up_duration']");
	
	//What are the known safety concerns of the treatment(s), and are the concerns different for the proposed repurposed indication?
	By txtknownsafetyconcerns= By.id("treatment_details_safety_concerns_ifr"); //IFRAME Id for known safety concerns field
	
	//What is the expected heath impact on patients if this treatment is successful?
	By txtexphealthimpact= By.id("treatment_details_health_impact_ifr"); //IFRAME Id for expected health impact.
	
	//What reduction in healthcare and societal costs would you expect if this repurposed treatment regimen is effective?
	By txtreductioninhealthcare= By.id("treatment_details_cost_reduction_ifr"); //IFRAME Id for text field reduction in healthcare
	
	//File Attachments
	// Upload file-1
	By btnuploadfile1= By.xpath("//div[@id='file_attachment_1_fileuploader']/div/div/form/input");
	
	// Upload file-2
	By btnuploadfile2= By.xpath("//div[@id='file_attachment_2_fileuploader']/div/div/form/input");
	
	//Upload file-3
	By btnuploadfile3= By.xpath("//div[@id='file_attachment_3_fileuploader']/div/div/form/input");
	
	//Upload Bio-sketch
	By btnuploadbiosketch= By.xpath("//div[@id='biosketch_attachment_fileuploader']/div/div/form/input");
	
	
	public void entertrialdetails(String TreatmentArm1,String EstimatedTrialLength,String ActiveCare, String FollowUp, String SafetyConcerns, String HealthImpact, String ReductionInHealthCare, String Upload1, String Upload2, String Upload3, String BioSketch) throws InterruptedException{
		
		ltrialdetail.info("----TRIAL DETAILS SECTION----");
		driver.findElement(checkbxprojtype).click();
		ltrialdetail.info("Checked the Project Type checkbox for the proposal");
		driver.findElement(txttreatmentarm1).sendKeys(TreatmentArm1);
		ltrialdetail.info("Entered the Treatment Arm #1 details");
		driver.findElement(txttreatmentarm1).sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB);
		Thread.sleep(1000);
		driver.findElement(txtestimatedtrialLength).sendKeys(EstimatedTrialLength);
		ltrialdetail.info("Entered the estimated trial length details");
		driver.findElement(txtactivecare).sendKeys(ActiveCare);
		ltrialdetail.info("Entered the length of active care details");
		driver.findElement(txtfollowup).sendKeys(FollowUp);
		ltrialdetail.info("Entered the length of follow up details");
		webs.iframeswitch(txtknownsafetyconcerns, SafetyConcerns);
		ltrialdetail.info("Entered the Known safety concerns details for the proposal");
		webs.iframeswitch(txtexphealthimpact, HealthImpact);
		ltrialdetail.info("Entered the expected health impact details for the proposal");
		webs.iframeswitch(txtreductioninhealthcare, ReductionInHealthCare);
		ltrialdetail.info("Entered the erduction in health care details for the proposal");
		uploadfile1(Upload1);
		ltrialdetail.info("1st File Attachment uploaded");
		uploadfile2(Upload2);
		ltrialdetail.info("2nd File Attachment uploaded");
		uploadfile3(Upload3);
		ltrialdetail.info("3rd File Attachment uploaded");
		uploadbiosketch(BioSketch);
		ltrialdetail.info("Bio-Sketch Attachment uploaded");
		driver.findElement(ppo.btnNext).click();
	}
	
	public void uploadfile1(String Upload1) throws InterruptedException{
		Thread.sleep(1000);
		//driver.findElement(btnuploadfile1).click();
		driver.findElement(btnuploadfile1).sendKeys(Upload1);
		Thread.sleep(1000);
	}

	public void uploadfile2(String Upload2) throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(btnuploadfile2).sendKeys(Upload2);
		Thread.sleep(1000);
	}
	
	public void uploadfile3(String Upload3) throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(btnuploadfile3).sendKeys(Upload3);
		Thread.sleep(1000);
	}
	
	public void uploadbiosketch(String BioSketch) throws InterruptedException{
		Thread.sleep(1000);		
		driver.findElement(btnuploadbiosketch).sendKeys(BioSketch);
		Thread.sleep(1000);
	}
	
	public void entertrialdetailsregular(String EstimatedTrialLength, String SafetyConcerns, String HealthImpact, String ReductionInHealthCare, String Upload1, String Upload2, String Upload3, String BioSketch) throws InterruptedException{
		driver.findElement(checkbxlab).click();
		driver.findElement(txtestimatedtrialLength).sendKeys(EstimatedTrialLength);
		webs.iframeswitch(txtknownsafetyconcerns, SafetyConcerns);
		webs.iframeswitch(txtexphealthimpact, HealthImpact);
		webs.iframeswitch(txtreductioninhealthcare, ReductionInHealthCare);
		uploadfile1(Upload1);
		uploadfile2(Upload2);
		uploadfile3(Upload3);
		uploadbiosketch(BioSketch);
		driver.findElement(ppo.btnNext).click();
	}
}
