package com.cure.softweb.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cure.softweb.Main.WebActions;

public class ProposalProjectSettings {

	WebDriver driver;
	WebActions webs;
	ProposalProjectOverview ppo;
	
	public ProposalProjectSettings(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;
        webs=new WebActions(driver);
        ppo=new ProposalProjectOverview(driver);
	}

    //Project Settings section
   
	//Invite People to review this Proposal

	//ROW-1
    By txtfirstnamerow1= By.xpath("//input[@id='json_invited_reviewers_0_first_name']");
    By txtlastnamerow1= By.xpath("//input[@id='json_invited_reviewers_0_last_name']");
    By txtemailrow1= By.xpath("//input[@id='json_invited_reviewers_0_email']");
  
    //ROW-2
    By txtfirstnamerow2= By.xpath("//input[@id='json_invited_reviewers_1_first_name']");
    By txtlastnamerow2= By.xpath("//input[@id='json_invited_reviewers_1_last_name']");
    By txtemailrow2= By.xpath("//input[@id='json_invited_reviewers_1_email']");

    //ROW-3
    By txtfirstnamerow3= By.xpath("//input[@id='json_invited_reviewers_2_first_name']");
    By txtlastnamerow3= By.xpath("//input[@id='json_invited_reviewers_2_last_name']");
    By txtemailrow3= By.xpath("//input[@id='json_invited_reviewers_2_email']");

   
    //Save button
    By btnSave= By.xpath("//form[@id='loiform']/div[2]/div[8]/div[4]/input[@name='save_only_form_button']");


    public void enteremail(String FIRSTNAME1, String LASTNAME1, String EMAIL1,String FIRSTNAME2,String LASTNAME2,String EMAIL2,String FIRSTNAME3,String LASTNAME3,String EMAIL3){
    	driver.findElement(txtfirstnamerow1).sendKeys(FIRSTNAME1);
    	driver.findElement(txtlastnamerow1).sendKeys(LASTNAME1);
        driver.findElement(txtemailrow1).sendKeys(EMAIL1);
        
        driver.findElement(txtfirstnamerow2).sendKeys(FIRSTNAME2);
    	driver.findElement(txtlastnamerow2).sendKeys(LASTNAME2);
        driver.findElement(txtemailrow2).sendKeys(EMAIL2);
        
        driver.findElement(txtfirstnamerow3).sendKeys(FIRSTNAME3);
    	driver.findElement(txtlastnamerow3).sendKeys(LASTNAME3);
        driver.findElement(txtemailrow3).sendKeys(EMAIL3);
        
        driver.findElement(btnSave).click();
        
    }
   
    public void proposalsuccessmsg() throws InterruptedException{
    	Thread.sleep(2000);
    	System.out.println("The proposal has been created successfully and the URL is: "+driver.getCurrentUrl());
    }

}
