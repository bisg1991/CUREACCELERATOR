package com.cure.softweb.PageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalProjectFunding {
	
	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalProjectFunding(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo= new ProposalProjectOverview(driver);
	}

	public static final Logger lfund= Logger.getLogger(ProposalProjectFunding.class.getName());
	
	// Project Funding:
	
	// Existing Project Funding
	By txtexistingprojfunding= By.xpath("//input[@id='existing_project_funding' and @type='text']");
	
	//Requested Project Funding
	By txtrequestedprojfunding= By.xpath("//input[@id='requested_project_funding' and @type='text']");
	
	//Budget Breakdown 
	By txtpersonnel= By.xpath("//div[@class='budget']/div[1]/div[2]/input[@id='budget_breakdown_personnel']");
	By txtpatientcost= By.xpath("//div[@class='budget']/div[2]/div[2]/input[@id='budget_breakdown_patient_costs']");
	By txtcorefacilities= By.xpath("//div[@class='budget']/div[3]/div[2]/input[@id='budget_breakdown_core_facilities']");
	By txtsupplies= By.xpath("//div[@class='budget']/div[4]/div[2]/input[@id='budget_breakdown_supplies']");
	By txtomicdata= By.xpath("//div[@class='budget']/div[5]/div[2]/input[@id='budget_breakdown_generation_of_omic_data']");
	By txtothercost= By.xpath("//div[@class='budget']/div[6]/div[2]/input[@id='budget_breakdown_other_costs']");
	
	//Total calculation of the Budget Breakdown
	By total=By.xpath("//div[@class='budget__amount']/input[@id='estimated_overall_project_cost']");
	
	// IFRAME id for What would you be able to do to speed up, strengthen....
	By txtspeedupandstrengthen= By.id("budget_breakdown_speed_up_ifr");
	
	//Co-funding agreement
	By chkboxcofunding= By.xpath("//input[@id='understand_required_to_fund' and @type='checkbox']");
	
	
    public void enterprojectfunding(String a,String b,String personnel, String patientcost, String corefacilities, String supply, String omic, String othercost) throws InterruptedException{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		lfund.info("---PROJECT FUNDING SECTION----");
		driver.findElement(txtexistingprojfunding).sendKeys(a);
		lfund.info("Entered exisitng project funding amount");
		driver.findElement(txtrequestedprojfunding).sendKeys(b);
		lfund.info("Entered requested project funding amount");
		driver.findElement(txtpersonnel).sendKeys(personnel);
		lfund.info("Entered personnel funding amount");
		driver.findElement(txtpatientcost).sendKeys(patientcost);
		lfund.info("Entered Patient costs funding amount");
		driver.findElement(txtcorefacilities).sendKeys(corefacilities);
		lfund.info("Entered Core Facilities funding amount");
		driver.findElement(txtsupplies).sendKeys(supply);
		lfund.info("Entered Supplies funding amount");
		driver.findElement(txtomicdata).sendKeys(omic);
		lfund.info("Entered omic data funding amount");
		driver.findElement(txtothercost).sendKeys(othercost);
		lfund.info("Entered other costs funding amount");
		calculatetotalvalue();
		driver.findElement(chkboxcofunding).click();
		lfund.info("Checked the Co-Funding Agreement");
		driver.findElement(ppo.btnNext).click();
		
	}
	
	public void calculatetotalvalue(){
		System.out.println("The total value for the Budget Breakdown is "+ driver.findElement(total).getAttribute("value"));
	}
	
	public void enterprojectfundingregular(String a,String b,String personnel, String patientcost, String corefacilities, String supply, String omic, String othercost, String SpeedUp) throws InterruptedException{
		
		driver.findElement(txtexistingprojfunding).sendKeys(a);
		driver.findElement(txtrequestedprojfunding).sendKeys(b);
		driver.findElement(txtpersonnel).sendKeys(personnel);
		driver.findElement(txtpatientcost).sendKeys(patientcost);
		driver.findElement(txtcorefacilities).sendKeys(corefacilities);
		driver.findElement(txtsupplies).sendKeys(supply);
		driver.findElement(txtomicdata).sendKeys(omic);
		driver.findElement(txtothercost).sendKeys(othercost);
		calculatetotalvalue();
		webs.iframeswitch(txtspeedupandstrengthen, SpeedUp);
		driver.findElement(chkboxcofunding).click();
		driver.findElement(ppo.btnNext).click();
	}
	
	
	
	
}
