package com.cure.softweb.Main;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

public class CommonFunctionsLib {

	public static void log(String log) {
		System.out.println(log);
	}

	public WebDriver driver;
	WebDriverWait wait;
	Properties properties;
	DesiredCapabilities objCapabilities;
	ChromeOptions chromeoptions;
	public Set<String> arrKnownBrowserHwnd;
	public String hwndFirstWindow;
	public String hwndMostRecentWindow;
	public Boolean locationServiceEnabled;
	public Boolean doFullReset;
	public static int imageCount = 0;
	public static String userDir = System.getProperty("user.dir");
	public static int productId = 0;
	public static int catId = 1;
	public static int chromeStartCount = 0;

	public static void selectElementByNameMethod(WebElement element, String Name) {
		Select selectitem = new Select(element);
		selectitem.selectByVisibleText(Name);
	}

	/**
	 * Purpose : Constructor with WebDriver argument
	 * 
	 * @param driver
	 */
	public CommonFunctionsLib(WebDriver driver) {
		this.driver = driver;
		locationServiceEnabled = Boolean
				.parseBoolean(properties.getProperty("locationServiceEnabled").trim().toLowerCase());
		doFullReset = true;
	}

	/**
	 * Purpose : Constructor with no argument
	 */
	public CommonFunctionsLib() {

	}

	public static void sleep(int secounds) {
		try {
			Thread.sleep(secounds * 1000);
		} catch (InterruptedException e) {
		}
	}

	public static void assertTrue(boolean condition, String msg, WebDriver driver) throws Exception, IOException {
		if (!condition) {
			log("Failed log: " + msg, driver);
			throw new Exception("Functional Fail: " + msg);
		}
	}

	public static void assertEquals(String actual, String expected, String msg, WebDriver driver)
			throws Exception, IOException {
		if (!actual.equals(expected)) {
			log("Failed log: " + msg, driver);
			throw new Exception("Functional Fail: " + msg);
		}
	}

	/**
	 * Purpose : This function injects file path into Windows File Upload dialog
	 * 
	 * @param filePath
	 * @throws AWTException
	 */
	public static Robot robot;

	public static void UploadFile(String filePath) throws AWTException {

		StringSelection stringSelection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
	}

	public static void keyPress(int keycode, int count) {
		for (int i = 0; i < count; i++) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			robot.keyPress(keycode);
			sleep(1);
			robot.keyRelease(keycode);
		}
	}

	public static void copyToClipboard(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

	
	/***
	 * 
	 * @param location
	 */
	public void CreateFolder(String location) {
		File Log = new File(location);
		if (Log.exists()) {
		} else {
			new File(location).mkdir();
		}
	}

	/*
	 * public static void tryAgain(ErrorHandalingMethod method, int seconds) {
	 * int count = 0; while (true) { try { method.errorHandlingMethod(); break;
	 * } catch (Exception e) { CommonFunctionsLib.sleep(1); count++; if (count
	 * == seconds) { count = 0; break; } } } }
	 */

	// method for add log to report
	public static void log(String log, WebDriver driver) throws Exception, IOException {

		if (driver != null) {
			System.out.println(driver + ": " + log);
			
		}

	}

	public static void skip(String testName) {
		throw new SkipException("Skipping " + testName + " as browser is not selected from test execution platform");
	}

	public static int randInt(int min, int max) {
		// Usually this can be a field rather than a method variable
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static void confirmSecurityException(WebActions action, WebDriver driver) throws Exception, IOException {
		CommonFunctionsLib.sleep(1);
		try {
			action.click(driver.findElement(By.id("advancedButton")));
			CommonFunctionsLib.log("Click on 'Advance' button", driver);

			action.click(driver.findElement(By.id("exceptionDialogButton")));
			CommonFunctionsLib.log("Click on 'Add Exception' button", driver);

			CommonFunctionsLib.keyPress(KeyEvent.VK_TAB, 4);
			CommonFunctionsLib.keyPress(KeyEvent.VK_ENTER, 1);
			CommonFunctionsLib.log("Confirm Secutity Exception", driver);
		} catch (Exception e) {
		}
	}

}
