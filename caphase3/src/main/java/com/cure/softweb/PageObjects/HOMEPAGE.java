package com.cure.softweb.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HOMEPAGE {

	WebDriver driver;

	public HOMEPAGE(WebDriver driver) {

		PageFactory.initElements(driver, this);
        this.driver = driver;

	}

	// Login via social page objects
	By textemail = By.xpath("//input[@id='identifierId']"); // Enter email field for gmail application
	By btnNext = By.xpath("//div[@id='identifierNext']/content/span"); //Next button in gmail application
	By textpass = By.xpath("//div[@id='password']/div[1]/div/div[1]/input"); //Enter password field for gmail application
	By btnNext2 = By.xpath("//html/body/div[1]/div[1]/div[2]/div[2]/form/div[2]/div/div/div[2]/div[1]/content/span"); // Next button2 for gmail application
	By verifyafterlogin = By.xpath("//main[@id='site-main']/div/section/header/div[2]/div[1]/h6");// Verification text
																									

	// Standard xpath used by webactions class

    // public WebElement gmailicon= driver.findElement(By.xpath("//main[@id='site-main']/div/section/div[1]/div[2]/form/ul/li[3]/a"));

	@FindBy(xpath = "//main[@id='site-main']/div/section/div[1]/div[2]/form/ul/li[3]/a")
	public WebElement ele1;
	
	// Element to be found and click on the button
	@FindBy(xpath="html/body/header/div/div/a/img[contains(@src,'/assets/images/ca-logo--navbar@2x.png')]")
	public WebElement elegoogle;

	// Login via email and password text field manually.
	By txtemailfield = By.xpath("//input[@id='email']"); // Email address field
	By txtpass = By.xpath("//input[@id='password']"); // Password txt field
	By btnlogin = By.xpath("//main[@id='site-main']/div/section/div[1]/div[2]/form/p[1]/input"); // Login Button

	public void verifyafterloginmessage() {
		String actualmessage = driver.findElement(verifyafterlogin).getText();
		String expectedmessage = "Total Funding for All Projects On CureAccelerator";
		Assert.assertEquals(actualmessage, expectedmessage);
	}



	public void loginviagooglebutton(String emails, String pass) throws InterruptedException {

		driver.findElement(textemail).sendKeys(emails);
		driver.findElement(btnNext).click();
		driver.findElement(textpass).sendKeys(pass);
		Thread.sleep(1000);
		driver.findElement(btnNext2).click();
	}

	public void loginmanually(String emails, String pass) {
		driver.findElement(txtemailfield).sendKeys(emails);
		driver.findElement(txtpass).sendKeys(pass);
		driver.findElement(btnlogin).click();
	}

}
