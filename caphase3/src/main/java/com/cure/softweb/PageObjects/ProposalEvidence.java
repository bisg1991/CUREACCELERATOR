package com.cure.softweb.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalEvidence {
	
	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalEvidence(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo=new ProposalProjectOverview(driver);
	}

	public static final Logger levidence= Logger.getLogger(ProposalEvidence.class.getName());
	
	
	//Evidence section
	
	//Have you or anyone else published (or have a manuscript submitted on) this research idea or disclosed it to the public in any way? 
	By radioyes1= By.xpath("//input[@id='has_research_idea_public_yes']");  //for YES radio
	By radiono1= By.xpath("//input[@id='has_research_idea_public_no']"); //for NO radio
	By txtresearchidea= By.id("research_idea_public_ifr"); //IFRAME id for research idea field.

    //Is there any published or unpublished observational data in humans or in model systems of this disease using this or a similar repurposed therapy?
	By radioyes2= By.xpath("//input[@id='has_observational_data_yes']");  //for YES radio
	By radiono2= By.xpath("//input[@id='has_observational_data_no']"); //for NO radio
	By txtobservationalidea= By.id("observational_data_ifr"); //IFRAME id for Observational data field.


    public void enterevidence(String ResearchIdea, String Observational) throws InterruptedException{
    	
    	levidence.info("---EVIDENCE SECTION---");
    	driver.findElement(radioyes1).click();
    	webs.iframeswitch(txtresearchidea, ResearchIdea);
    	levidence.info("Entered the Have you or anyone else published this research idea or disclosed it to the public in any way? details");
    	driver.findElement(radioyes2).click();
    	webs.iframeswitch(txtobservationalidea, Observational);
    	levidence.info("Entered the published or unpublished observational data in humans details for the proposal");
    	driver.findElement(ppo.btnNext).click();
    }

    public void enterevidencewithyes(){
    	
    }
    
    public void enterevidenceregular(String ResearchIdea, String Observational) throws InterruptedException{
    	driver.findElement(radioyes1).click();
    	webs.iframeswitch(txtresearchidea, ResearchIdea);
    	driver.findElement(radioyes2).click();
    	webs.iframeswitch(txtobservationalidea, Observational);
    	driver.findElement(ppo.btnNext).click();
    }

}
