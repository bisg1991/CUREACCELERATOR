package com.cure.softweb.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalDiseaseDetail {
	
	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalDiseaseDetail(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo=new ProposalProjectOverview(driver);
	}

	public static final Logger ldiedetail= Logger.getLogger(ProposalDiseaseDetail.class.getName());
	
    //Disease detail section PAGE OBJECTS.
	
	//Estimated prevalence of the disease(s) in the country where the research will be carried out
	By txtestimatedprevalance= By.id("disease_details_prevalence_ifr"); // IFRAME id for estimated prevalance text field

	//Current treatment(s) of the disease(s)
	By txtcurrenttreatment= By.id("disease_details_current_treatment_ifr"); // IFRAME id for current treatment of the disease text field

    //Estimated annual direct healthcare cost of care for an average patient with the disease(s)
	By txtannualdirecthealthcare= By.xpath("//input[@id='disease_details_annual_cost']");
	
	//Estimated annual non-healthcare societal costs for an average patient with the disease(s)
	By annualnonhealthcare= By.xpath("//input[@id='disease_details_non_healthcare_societal_annual_cost']");
	
	//Are there ‘Omic datasets available about the disease. 
	By radioyes= By.xpath("//input[@id='disease_details_omic_datasets_yes']");  //for YES radio
	By radiono= By.xpath("//input[@id='disease_details_omic_datasets_no']"); //for NO radio
	By txtukbased= By.id("disease_details_omic_datasets_available_ifr"); //IFRAME id for omic data sets field.
	
	
	public void enterdieseasedetail(String EstimatedPrevalance, String CurrentTreatment, String AnnualDirectHealthcare, String AnnualNonHealthcare) throws InterruptedException{
		
		ldiedetail.info("---DISEASE DETAIL SECTION---");
		webs.iframeswitch(txtestimatedprevalance, EstimatedPrevalance);
		ldiedetail.info("Entered the estimated prevalance details for the proposal");
		webs.iframeswitch(txtcurrenttreatment, CurrentTreatment);
		ldiedetail.info("Entered the current treatment details for the proposal");
		driver.findElement(txtannualdirecthealthcare).sendKeys(AnnualDirectHealthcare);
		ldiedetail.info("Entered the estimated annual direct healthcare amount for the proposal");
		driver.findElement(annualnonhealthcare).sendKeys(AnnualNonHealthcare);
		ldiedetail.info("Entered the estimated annual non-healthcare societal amount for the proposal");
		driver.findElement(ppo.btnNext).click();
	}
	
	public void enterdiseasedetailregular(String EstimatedPrevalance, String CurrentTreatment, String AnnualDirectHealthcare, String AnnualNonHealthcare) throws InterruptedException{
		webs.iframeswitch(txtestimatedprevalance, EstimatedPrevalance);
		webs.iframeswitch(txtcurrenttreatment, CurrentTreatment);
		driver.findElement(txtannualdirecthealthcare).sendKeys(AnnualDirectHealthcare);
		driver.findElement(annualnonhealthcare).sendKeys(AnnualNonHealthcare);
		driver.findElement(ppo.btnNext).click();
	}

}
