package com.cure.softweb.Main;



import java.awt.AWTException;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

	private WebDriver driver = null;
	protected WebDriver webDriver = null;
	final int waitvalue = 10;
	final int timeout = 10;
	Actions builder = null;

	public static final int CLICK_TIMEOUT_SECONDS = 15;
	public static final int PRESENCE_TIMEOUT_SECONDS = 5;
	public static final int POLLING_WITH_REFRESH_TIMEOUT_SECONDS = 30;
	public static final int REFRESH_TIMEOUT_SECONDS = 5;
	public static final int SHORT_TIMEOUT_SECONDS = 1;
	public static final int MEDIUM_TIMEOUT_SECONDS = 5;
	public static final int LONG_TIMEOUT_SECONDS = 20;
	public static final int PAUSE_BETWEEN_KEYS_MILLIS = 50;
	public static final int PAUSE_BETWEEN_TRIES_MILLIS = 200;
	public static final int PAUSE_BETWEEN_REFRESH_SECONDS = 5;
	public static final int PAGE_LOAD_TIMEOUT_SECONDS = 80;
	public static final int PAGE_READY_TIMEOUT_SECONDS = 10;
	public static final int IMPLICIT_WAIT_TIMEOUT_MILLIS = 2000;

	/**
	 * Constructor to initialize WebAction class objects
	 * 
	 * @param driver
	 */
	public WebActions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		builder = new Actions(this.driver);
	}

	
	
	public WebElement isElementLoaded(WebElement elementToBeLoaded) {
	    WebDriverWait wait = new WebDriverWait(driver, 15);
	    WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	    return element;
	}
	
	
	
	/**
	 * @param el
	 * @return
	 * @throws Exception
	 */
	public WebElement clearText(WebElement el) throws Exception {
		String tag = el.getTagName();
		try {
			waitUntilClickable(el, CLICK_TIMEOUT_SECONDS);
			el.clear();
		} catch (Exception e) {
			throw new Exception("WebAction -> clearText(WebElement el) - Error clearing text from element <%s>: %s"
					+ tag + e.getMessage());
		}
		return el;
	}

	/**
	 * 
	 * @param el
	 * @param value
	 * @throws Exception
	 */
	public void inputText(WebElement el, String value) throws Exception {
		String tag = el.getTagName();
		try {
			isClickable(el);
			clearText(el);
			el.sendKeys(value);
		} catch (Exception e) {
			throw new Exception(
					"WebAction -> inputText(WebElement el, String value) - Error entering text into element <%s>: %s"
							+ tag + e.getMessage());
		}
	}

	/**
	 * Purpose : Parameterized version, This method Type special key like
	 * Shift,Backspace, Enter, Tab, Shift along with text
	 * 
	 * @param object
	 * @param input
	 */
	public void inputText(WebElement el, Keys theKey, String input) throws Exception {
		try {
			el.sendKeys(Keys.chord(theKey, input));
		} catch (IllegalArgumentException e) {
			throw new Exception("WebAction -> EnterValueText(WebElement object,Keys theKey, String input)" + e);
		}
	}

	/*public boolean waitForPageLoad() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return 	(boolean) executor.executeScript("return typeof(window.TestReady) !== 'undefined' && window.TestReady === true");
	}*/

	public void clickByJS(WebElement el) throws Exception {
		String tag = el.getTagName();
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", el);
		} catch (Exception e) {
			throw new Exception("WebAction -> clickByJS(WebElement el) - Error in clicking on the element <%s>: %s"
					+ tag + e.getMessage());
		}
	}

	/**
	 * 
	 * @param el
	 * @return
	 * @throws Exception
	 */
	public WebElement click(WebElement el) throws Exception {
		String tag = el.getTagName();
		try {
			waitUntilClickable(el, CLICK_TIMEOUT_SECONDS);
			el.click();
		} catch (NoSuchElementException e) {
			throw new Exception(
					"WebAction -> click() - Error in click operation on element <%s>: %s" + tag + e.getMessage());
		}
		return el;
	}

	public void performClick(WebElement el, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		el = wait.until(ExpectedConditions.elementToBeClickable(el));
		Actions a = new Actions(driver);
		a.moveToElement(el).click().perform();
		//CommonFunctionsLib.sleep(3);
	}

	public boolean isDisplayed(WebElement el) {
		if (el == null) {
			return false;
		}
		try {
			if (!el.isDisplayed()) {
				return false;
			}
			if (el.getSize().getHeight() <= 0 || el.getSize().getWidth() <= 0) { // If
																					// width
																					// or
																					// height
																					// is
																					// 0,
																					// element
																					// is
																					// not
																					// clickable
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/***
	 * check if weblement is clickable
	 * 
	 * @param el
	 * @return
	 */
	public boolean isClickable(WebElement el) {
		if (el == null) {
			return false;
		}
		try {
			if (!el.isDisplayed()) {
				return false;
			}
			if (!el.isEnabled()) {
				return false;
			}
			if (el.getSize().getHeight() <= 0 || el.getSize().getWidth() <= 0) { // If
																					// width
																					// or
																					// height
																					// is
																					// 0,
																					// element
																					// is
																					// not
																					// clickable
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public WebElement waitUntilClickable(final WebElement el, int timeout) {
		int waitSeconds = timeout;
		final String message = "Element never became clickable after '%d' seconds" + waitSeconds;
		WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
		wait.withMessage(message).ignoring(StaleElementReferenceException.class);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				if (isClickable(el)) {
					return el;
				}
				return null;
			}
		});
		return el;
	}

	/**
	 * @param el
	 * @return
	 * @throws Exception
	 */
	public String getPageTitle() throws Exception {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			throw new Exception("WebAction -> clearText(WebElement el) - Error clearing text from element <%s>: %s"
					+ e.getMessage());
		}
		return title;
	}

	/**
	 * @param el
	 * @return
	 * @throws Exception
	 */
	public String getUrl() throws Exception {
		String url = null;
		try {
			url = driver.getCurrentUrl();
		} catch (Exception e) {
			throw new Exception("WebAction -> clearText(WebElement el) - Error clearing text from element <%s>: %s"
					+ e.getMessage());
		}
		return url;
	}

	/**
	 * Purpose : This is used to fetch the CSS properties' values of the given
	 * element. CSS properties can be font-family, background-color, color, and
	 * so on.
	 * 
	 * @param object
	 * @param ProprertyName
	 */
	public void getCSS(WebElement object, String ProprertyName) {
		object.getCssValue(ProprertyName);
		// getCssValue("font-family"));
		// getCssValue("background-color"));
	}

	/***
	 * Purpose : This is used to get the relative position of an element where
	 * it is rendered on the web page.
	 * 
	 * @param object
	 */
	public void getLocation(WebElement object) {
		object.getLocation();
	}

	/***
	 * Purpose : It will return the width and height of the rendered WebElement.
	 * 
	 * @param object
	 */
	public void getSize(WebElement object) {
		object.getSize();
	}

	/***
	 * Purpose : It will give the visible text if the element contains any text
	 * on it or else will return nothing.
	 * 
	 * @param object
	 * @throws Exception
	 */
	public String getText(WebElement el) throws Exception {
		String tag = el.getTagName();
		try {
			waitUntilClickable(el, CLICK_TIMEOUT_SECONDS);
		} catch (Exception e) {

			throw new Exception("WebAction -> clearText(WebElement el) - Error clearing text from element <%s>: %s"
					+ tag + e.getMessage());

		}
		return el.getText();
	}

	/***
	 * Purpose : This will return the tag name of the WebElement. For example,
	 * in the following HTML code, button is the tag name of the HTML element
	 * 
	 * @param object
	 */
	public void getTagName(WebElement object) {
		object.getTagName();
	}

	/***
	 * Purpose : Verifies if an element is selected right now on the web page
	 * and can be executed only on a radio button, options in select, and
	 * checkbox WebElements.
	 * 
	 * @param object
	 */
	public void isSelected(WebElement object) {
		object.isSelected();
	}

	/***
	 * Purpose : Verifies if an element is selected right now on the web page
	 * and can be executed only on a radio button, options in select, and
	 * checkbox WebElements.
	 * 
	 * @param object
	 */
	public void select(WebElement object, String value) {
		Select select = new Select(object);
		select.selectByVisibleText(value);
	}
	
	

	public void select(WebElement object) {
		Select select = new Select(object);
		select.selectByIndex(1);
	}

	public void waitAndClick(WebElement el) throws Exception {
		int count = 0;
		while (true) {
			try {
				click(el);
				count = 0;
				break;
			} catch (Exception e) {
				count++;
				CommonFunctionsLib.sleep(1);
				if (count == 20) {
					count = 0;
					throw new Exception("Element: '" + el + "' not found!");
				}
			}
		}
	}

	public void waitAndClick(WebElement el, int secounds) throws Exception {
		int count = 0;
		while (true) {
			try {
				click(el);
				count = 0;
				break;
			} catch (Exception e) {
				count++;
				CommonFunctionsLib.sleep(1);
				if (count == secounds) {
					count = 0;
					throw new Exception("Element: '" + el + "' not found!");
				}
			}
		}
	}

	public String checkAttribute(WebElement el, String attribute) {
		return el.getAttribute(attribute);
	}

	// ################## Mouse Operation #################################
	/**
	 * Purpose : The moveByOffset() method is used to move the mouse from its
	 * current position to another point on the web page. User can specify the X
	 * distance and Y distance the mouse has to be moved. When the page is
	 * loaded, generally the initial position of a mouse would be (0, 0),
	 * 
	 * @param object
	 * @param ToXPoint
	 * @param ToYPoint
	 */
	public void mouseOffSet(WebElement object, int ToXPoint, int ToYPoint) {
		builder.moveByOffset(object.getLocation().getX() + ToXPoint, object.getLocation().getY() + ToYPoint);
		builder.perform();
	}

	/**
	 * Purpose : The click() method is used to simulate the left-click of your
	 * mouse at its current point of location. This method doesn't really
	 * realize where or on which element it is clicking. It just blindly clicks
	 * wherever it is at that point of time. Hence, this method is used in
	 * combination with some other action rather than independently, to create a
	 * composite action
	 * 
	 * @param object
	 * @param toXposition
	 * @param toYposition
	 */
	public void click(WebElement object, int toXposition, int toYposition) {
		builder.moveByOffset(object.getLocation().getX() + toXposition, object.getLocation().getY() + toXposition)
				.click();
		builder.perform();
	}

	/**
	 * Purpose : click() method to click directly on the WebElement.
	 * 
	 * @param object
	 */
	public void ClickOnWebElement(WebElement object) {
		builder.click(object);
		builder.build().perform();
	}

	/***
	 * Purpose : The clickAndHold()method is another method of the Actions class
	 * that left-clicks on an element and holds it without releasing the left
	 * button of the mouse. This method will be useful when executing operations
	 * such as drag-and-drop.
	 * 
	 * @param object
	 */
	public void clickAndHold(WebElement object) {
		builder.moveByOffset(200, 20).clickAndHold().moveByOffset(120, 0).perform();
	}

	/**
	 * Purpose : taken on a held WebElement is to release it so that the element
	 * can be dropped or released from the mouse.
	 * 
	 * @param source
	 * @param target
	 */
	public void clickAndHoldAndRelease(WebElement source, WebElement target) {
		builder.clickAndHold(source).release(target).perform();
	}

	/**
	 * Purpose : helps us to move the mouse cursor to a WebElement on the web
	 * page.
	 * 
	 * @param object
	 */
	public void moveToElement(WebElement object) {
		builder.moveToElement(object).clickAndHold().moveByOffset(120, 0).perform();
	}

	/**
	 * Purpose : have to drag-and-drop components or WebElements of a web page.
	 * 
	 * @param object
	 */
	public void dragMe(WebElement object) {
		builder.dragAndDropBy(object, 300, 200).perform();
	}

	/**
	 * Purpose : The only difference being that instead of moving the WebElement
	 * by an offset, we move it on to a target element
	 * 
	 * @param source
	 * @param target
	 */
	public void dragAndDropTo(WebElement source, WebElement target) {
		builder.dragAndDrop(source, target).perform();
	}

	/**
	 * Purpose : Moving on to another action that can be performed using mouse,
	 * doubleClick()is another out of the box method that WebDriver provides to
	 * emulate the double-clicking of the mouse.
	 * 
	 * @param object
	 */
	public void doubleClick(WebElement object) {
		builder.moveToElement(object).doubleClick().perform();
	}

	/**
	 * Purpose : The contextClick() method, also known as right-click, is quite
	 * common on many web pages these days. The context is nothing but a menu; a
	 * list of items is associated to a WebElement based on the current state of
	 * the web page.
	 * 
	 * @param object
	 * @param item4
	 */
	public void rightClick(WebElement object, String item4) {
		builder.contextClick(object).click(driver.findElement(By.name("Item 4"))).perform();
	}

	/**
	 * Purpose : returns HTTP code for a given URL
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public boolean getResponseCode(String urlString) throws Exception, IOException {
		boolean isValid = false;
		HttpURLConnection httpURLConnect = null;
		int code = 0;
		try {
			URL url = new URL(urlString);
			httpURLConnect = (HttpURLConnection) url.openConnection();
			httpURLConnect.setRequestMethod("GET");
			httpURLConnect.connect();
			code = httpURLConnect.getResponseCode();
			if (code == 200) {
				isValid = true;
			}
			if (code == HttpURLConnection.HTTP_NOT_FOUND) {
				isValid = true;
			}

		} catch (MalformedURLException e) {
			throw new Exception("MalformedURLException Error : " + e + " , " + urlString);
		} catch (IOException e) {
			isValid = true;
			throw new Exception("IOException Error : " + e + " , " + urlString);
		} catch (Exception e) {
			throw new Exception("Exception Error : " + e + " , " + urlString);
		}
		return isValid;
	}

	/**
	 * Purpose : This method is useful when you have multiple links on a page
	 * and instead of click each link and verifying the page title or what's
	 * written on the page - you can just send an http request to the link and
	 * see what the response is
	 * 
	 * @param link
	 * @return
	 * @throws Exception
	 */
	public boolean isFileDownloadable(String link) throws Exception {
		boolean isValid = false;
		try {
			if (getResponseCode(link)) {
				isValid = true;
			}
		} catch (Exception e) {
			throw new Exception("isFileDownloadable ends.." + e);
		}
		return isValid;
	}

	/**
	 * Purpose : Verifies if link on a given page is broken
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	public String isLinkBroken() throws Exception, IOException {
		String link = null;
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		for (WebElement linkElement : linksList) {
			link = linkElement.getAttribute("href");
			if (link != null) {
				if (getResponseCode(link)) {
				} else {
				}
			}
		}
		return link;
	}

	/**
	 * Purpose : Parameterize form of isLinkBroken method, verifies if link on a
	 * given page is broken
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean isLinkBroken(String urlString) throws Exception, IOException {
		boolean isValid = false;
		if (urlString != null) {
			if (getResponseCode(urlString)) {
				isValid = true;
			} else {
				isValid = false;
			}
		}
		return isValid;
	}

	// ---------------------Working on Multiple Browser/Switching Frame/Handling
	// Alert-------------------------
	/**
	 * Purpose : This method switches to browser based on browser URL
	 * 
	 * @param currentUrl
	 */
	public void switchToBrowser(String title) {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			if (driver.getTitle().contains(title)) {
				break;
			} else {
			}
		}
	}

	/**
	 * Purpose : switch target to a Frame of a browser
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(String frameIndex) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
	}

	/**
	 * Purpose : switch target to a second Frame of a browser where target is
	 * set to first frame
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(String frameIndex1, String frameIndex2) {
		driver.switchTo().frame(frameIndex1);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(frameIndex2);
	}

	
	
	public void iframeswitch(By id, String text) throws InterruptedException{
		
		Thread.sleep(1000);
		WebElement iframe1 = driver.findElement(id);
	    driver.switchTo().frame(iframe1);
	    WebElement body = driver.findElement(By.tagName("body"));
	    body.clear(); 
	    body.sendKeys(text);
		driver.switchTo().defaultContent();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean acceptAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> AcceptAlert() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> AcceptAlert() , " + e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean declineAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			return true;
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> DeclineAlert() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> DeclineAlert() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean inputTextOnAlert(String keysToSend) throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(keysToSend);
			return true;
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> inputTextOnAlert() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> inputTextOnAlert() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String getTextOfAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> getTextOfAlert() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> getTextOfAlert() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public void refresh() throws Exception {
		try {
			driver.navigate().refresh();
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> refresh() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> refresh() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public void forward() throws Exception {
		try {
			driver.navigate().forward();
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> forward() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> forward() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public void back() throws Exception {
		try {
			driver.navigate().back();
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> back() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> back() , " + e);
		}
	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public boolean openURL(String URL) throws Exception {
		boolean isValid = false;
		try {
			driver.navigate().to(URL);
			if (getResponseCode(URL)) {
				isValid = true;
			} else {
				isValid = false;
			}
		} catch (NoAlertPresentException e) {
			throw new Exception("WebAction -> openURL() , " + e);
		} catch (Exception e) {
			throw new Exception("WebAction -> openURL() , " + e);
		}
		return isValid;
	}

	/**
	 * method for execute java script on page
	 * 
	 * @param script
	 * @return
	 */

	public Object executeJavascript(String script) {
		try {
			return ((JavascriptExecutor) webDriver).executeScript(script);
		} catch (Exception e) {
			throw new RuntimeException("Exception executing Javascript '%s':" + script + e);
		}
	}

	/**
	 * method to check for particular text is present or not on web page.
	 * 
	 * @param txtValue
	 * @return
	 */
	public boolean isTextPresent(String txtValue) {
		boolean b = false;
		try {
			b = driver.getPageSource().contains(txtValue);
			return b;
		} catch (NoSuchElementException e) {
			new Exception("WebAction -> isTextPresent(String txtValue) - error" + e);
		}
		return b;
	}

	/**
	 * method to click link by its href arrtibute
	 * 
	 * @param href
	 */

	public void clickLinkByHref(String href) {
		java.util.List<WebElement> anchors = driver.findElements(By.tagName("a"));
		Iterator<WebElement> i = anchors.iterator();
		try {
			while (i.hasNext()) {
				WebElement anchor = i.next();
				if (anchor.getAttribute("href").contains(href)) {
					if (isClickable(anchor)) {
						anchor.click();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * method to find element by text and check if it is present.
	 * 
	 * @param text
	 * @return
	 */

	public boolean checkElementByText(String text) {
		if (driver.findElement(By.xpath(("//*[contains(text(), '" + text + "')]"))) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * method to get element by text.
	 * 
	 * @param text
	 * @return
	 */

	public WebElement getElementByText(String text) {
		return driver.findElement(By.xpath(("//*[contains(text(), '" + text + "')]")));
	}

	/**
	 * method for perform action 'mouse hover' on element.
	 * 
	 * @param element
	 * @return
	 */

	public boolean onMouseOver(WebElement element) {
		boolean result = false;
		try {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(mouseOverScript, element);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * method for select value from drop-down.
	 * 
	 * @param el
	 * @param index
	 */

	public void selectFromDropDown(WebElement el, int index) {
		Select select = new Select(el);
		select.selectByIndex(index);
	}

	/**
	 * method for get text of selected value of drop-down.
	 * 
	 * @param el
	 * @return
	 * @throws Exception
	 */

	public String getSelectedTextFromDropDown(WebElement el) throws Exception {
		Select select = new Select(el);
		WebElement temp = select.getFirstSelectedOption();
		return getText(temp);
	}

	/**
	 * method for scroll to particular web element.
	 * 
	 * @param scroll
	 */

	public void scrollToElenent(WebElement scroll) {
		scroll.sendKeys(Keys.PAGE_DOWN);
	}

	/**
	 * method to scroll down on web page.
	 * 
	 */
	public void scrollDown() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void scroll(int point, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, " + point + ");");
	}
	
	public void printnames(WebElement el){
		List<WebElement> drop = driver.findElements((By) el);
		java.util.Iterator<WebElement> i = drop.iterator();
		while(i.hasNext()) {
		    WebElement row = i.next();
		    System.out.println(row.getText());
		    el.click();
		}
	}
}

