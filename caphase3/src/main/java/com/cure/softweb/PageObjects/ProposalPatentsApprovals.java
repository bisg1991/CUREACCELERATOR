package com.cure.softweb.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalPatentsApprovals {
	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalPatentsApprovals(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo=new ProposalProjectOverview(driver);
	}

	public static final Logger lpatent= Logger.getLogger(ProposalPatentsApprovals.class.getName());
	
    //Patents and approvals section
	//Is there any issued patent on the subject matter of the proposal? 
	By radioyes1= By.xpath("//input[@id='intellectual_property_protected_yes']");  //for YES radio
	By radiono1= By.xpath("//input[@id='intellectual_property_protected_no']"); //for NO radio
	By txtpatentsubjmatter= By.id("steps_to_protect_ifr"); //IFRAME id for patent on the subject matter field.


	//Have you, or anyone else to your knowledge, filed an application with the EMA, FDA or any other regulatory agency for orphan drug designation for this repurposed therapy? 
	By radioyes2= By.xpath("//input[@id='has_fda_orphan_drug_application_yes']");  //for YES radio
	By radiono2= By.xpath("//input[@id='has_fda_orphan_drug_application_no']"); //for NO radio
	By txtorphandrugreg= By.id("filed_with_fda_orphan_drug_ifr"); //IFRAME id for orphan drug registration field.


    //Have you, or anyone else to your knowledge, filed an application with the EMA, FDA or any other regulatory agency for a new use for this repurposed therapy?
	By radioyes3= By.xpath("//input[@id='has_fda_application_yes']");  //for YES radio
	By radiono3= By.xpath("//input[@id='has_fda_application_no']"); //for NO radio
	By txtrepurposedtherapy= By.id("filed_with_fda_ifr"); //IFRAME id for repurposed therapy field.
    

    public void enterpatentsdetails(String Patentissued, String OrphanDrugRegistration, String EMAFDA){
    	
    	lpatent.info("----PATENTS AND APPROVAL SECTION----");
    	driver.findElement(radioyes1).click();
    	driver.findElement(txtpatentsubjmatter).sendKeys(Patentissued);
    	lpatent.info("Entered Is there any issued patent on the subject matter of the proposal? for the proposal");
    	driver.findElement(radioyes2).click();
    	driver.findElement(txtorphandrugreg).sendKeys(OrphanDrugRegistration);
    	lpatent.info("Entered orphan drug designation for this repurposed therapy? for the proposal");
    	driver.findElement(radioyes3).click();
    	driver.findElement(txtrepurposedtherapy).sendKeys(EMAFDA);
    	lpatent.info("Entered filed an application with the EMA, FDA for the proposal");
    	driver.findElement(ppo.btnNext).click();
    }

    public void enterpatentdetailsregular(String Patentissued, String OrphanDrugRegistration){
    	driver.findElement(radioyes1).click();
    	driver.findElement(txtpatentsubjmatter).sendKeys(Patentissued);
    	driver.findElement(radioyes2).click();
    	driver.findElement(txtorphandrugreg).sendKeys(OrphanDrugRegistration);
    	driver.findElement(ppo.btnNext).click();
    }


}
