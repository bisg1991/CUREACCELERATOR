package com.cure.softweb.PageObjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalProjectOverview{
	
	WebDriver driver;
	WebActions webs;
	
	
	public ProposalProjectOverview(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
	}

	public static final Logger loverview= Logger.getLogger(ProposalProjectOverview.class.getName());
	
	
	// Click on the special proposal button
	
	@FindBy(id="btnspecialproject") //click on the special proposal button
	public WebElement btnspecialproposal;
	
	By btnokproceednote = By.xpath("//html/body/main/div/section/div[3]/div/div[2]/a"); //Click on the proceed button of the note pop up
	
	// Click on the Regular proposal button
	@FindBy(xpath="//main[@id='site-main']/div/section/header/div[1]/div[2]/div[2]/a")
	public WebElement btnregularproject;
	
	By btnBeginProposal= By.xpath("//main[@id='site-main']/div/section/div/div[3]/a");
	
	//Project Overview section.
	
	//Enter the project title (for special)
	By txtprojecttitle = By.xpath("//html/body/main/div/section/div[3]/form/div[2]/div[1]/div[1]/div[2]/div[1]/textarea");
	
	//Enter the project title (for Regular)
	By txtprojecttitlereg= By.xpath("//html/body/main/div/section/div[2]/form/div[2]/div[1]/div[1]/div[2]/div[1]/textarea");
	
	//Enter the public title for the project (for special)
	By txtpublictitle = By.xpath("//html/body/main/div/section/div[3]/form/div[2]/div[1]/div[1]/div[2]/div[2]/textarea");
	
	//Enter the public title for the project (for regular)
	By txtpublictitlereg = By.xpath("//html/body/main/div/section/div[2]/form/div[2]/div[1]/div[1]/div[2]/div[2]/textarea");
	
	// IFRAME id for the public abstract field
    By txtpublicabstractiframe= By.id("public_abstract_ifr"); 
    
	
	//Enter the details for the public abstract field
	By txtpublicabstract =By.xpath("//div[@class='field-wrapper length-limit']/div/div/div[@id='mceu_21']"); 
	By txtpublicabstract2 =By.xpath("//body[@id='tinymce']/p");
	
	//Enter principal investigator name (for special)
	By txtprincipalinvestigator= By.xpath("//html/body/main/div/section/div[3]/form/div[2]/div[1]/div[2]/div/input");
	
	//Enter principal investigator name (for regular)
	By txtprincipalinvestigatorreg= By.xpath("//html/body/main/div/section/div[2]/form/div[2]/div[1]/div[2]/div/input");
	
	// IFRAME id for the institution involved field
	By txtinstitutioninvolvediframe= By.id("institutions_involved_ifr"); 
	
	// Enter the details for the institution involved field
	By txtinstitutioninvolvedfield= By.xpath("//div[@class='col--half col--left']/div/div[1]/div/div[@id='mceu_46']");
	
	// Disease category field.
	By txtdiseasecategory= By.xpath("//input[@data-tag-container-id='#diseases-category-container' and @type='text']");
	
	// Disease/condition text field
	By txtdiseasecondition= By.xpath("//input[@data-tag-container-id='#diseases-tag-container' and @type='text']");
	
	// IFRAME id for disease background text field
	By txtdiseasebackground= By.id("disease_background_ifr");
	
	// Treatments field
	By txttreatments= By.xpath("//input[@data-tag-container-id='#treamtments-tag-container' and @type='text']");
	
	// IFRAME id for research description text field
	By txtresearchdescription= By.id("research_description_ifr");
	
	//Are you in active contact with a patient support group or charity regarding this project, or treatment of this condition?
	By radioyes1= By.xpath("//input[@id='has_active_contact_yes']");  //for YES radio
	By radiono1= By.xpath("//input[@id='has_active_contact_no']"); //for NO radio
	By txtactivecontact= By.id("active_contact_with_patient_support_ifr"); //IFRAME id for active contact with patient field.
	
	//Are you UK based? 
	By radioyes2= By.xpath("//input[@id='has_uk_based_yes']");  //for YES radio
	By radiono2= By.xpath("//input[@id='has_uk_based_no']"); //for NO radio
	By txtukbased= By.id("potential_uk_partner_ifr"); //IFRAME id for active contact with patient field.
	
	//Button NEXT
	By btnNext= By.xpath("//button[@id='controlButtonRight']");
	
	
	//Method to create special proposal
	public void enterprojectoverview(String protitle, String pubtitle, String pubabs, String principal, String institution, String diecat, String diecond, String diebackgrnd, String treatment, String ResearchDescription) throws Exception{
		
		
		driver.findElement(btnokproceednote).click();
		loverview.info("***Entered the special proposal details page form*******");
		loverview.info("---PROJECT OVERVIEW SECTION---");
		driver.findElement(txtprojecttitle).sendKeys(protitle);
		loverview.info("Entered the Proposal Project Title");
		driver.findElement(txtpublictitle).sendKeys(pubtitle);
		loverview.info("Entered the Proposal Public Title");
		webs.iframeswitch(txtpublicabstractiframe, pubabs);
		loverview.info("Entered the Proposal Public abstract details");
		driver.findElement(txtprincipalinvestigator).sendKeys(principal);
		loverview.info("Entered the Principal investigator name");
		webs.iframeswitch(txtinstitutioninvolvediframe, institution);
		loverview.info("Entered the Institution involved");
		driver.findElement(txtdiseasecategory).sendKeys(diecat);
		Thread.sleep(3000);
		loverview.info("Entered the Disease category");
		driver.findElement(txtdiseasecategory).sendKeys(Keys.ENTER);
		driver.findElement(txtdiseasecondition).sendKeys(diecond);
		Thread.sleep(3000);
		driver.findElement(txtdiseasecondition).sendKeys(Keys.ENTER);
		loverview.info("Entered the Disease condition");
		webs.iframeswitch(txtdiseasebackground, diebackgrnd);
		loverview.info("Entered the Disease Back ground details");
		driver.findElement(txttreatments).sendKeys(treatment);
		Thread.sleep(3000);
		driver.findElement(txttreatments).sendKeys(Keys.ENTER);
		loverview.info("Entered the Treatments");
		webs.iframeswitch(txtresearchdescription, ResearchDescription);
		loverview.info("Entered the Research description details");
		driver.findElement(btnNext).click();
	}
	
	// Method to create Regular proposal
	public void enterprojectoverviewregular(String protitle, String pubtitle, String pubabs, String principal, String institution, String diecat, String diecond, String diebackgrnd, String treatment, String ResearchDescription) throws InterruptedException{
		
		driver.findElement(btnBeginProposal).click();
		driver.findElement(txtprojecttitlereg).sendKeys(protitle);
		driver.findElement(txtpublictitlereg).sendKeys(pubtitle);
		webs.iframeswitch(txtpublicabstractiframe, pubabs);
		driver.findElement(txtprincipalinvestigatorreg).sendKeys(principal);
		webs.iframeswitch(txtinstitutioninvolvediframe, institution);
		driver.findElement(txtdiseasecategory).sendKeys(diecat);
		Thread.sleep(3000);
		driver.findElement(txtdiseasecategory).sendKeys(Keys.ENTER);
		driver.findElement(txtdiseasecondition).sendKeys(diecond);
		Thread.sleep(3000);
		driver.findElement(txtdiseasecondition).sendKeys(Keys.ENTER);
		webs.iframeswitch(txtdiseasebackground, diebackgrnd);
		driver.findElement(txttreatments).sendKeys(treatment);
		Thread.sleep(4000);
		driver.findElement(txttreatments).sendKeys(Keys.ENTER);
		webs.iframeswitch(txtresearchdescription, ResearchDescription);
		driver.findElement(btnNext).click();
		
		
	}
}
