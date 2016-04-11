package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.perfectomobile.utils.*;
import com.perfectomobile.test.*;
import com.perfectomobile.dataDrivers.*;
import com.perfectomobile.dataDrivers.excelDriver.*;

public class WebOptimizerBaseView  {

	
	protected static RemoteWebDriver driver; 
	/** The url of the local optimizer. */
//	private String url = "http://coverage-optimizer-shortterm-elb-913786431.eu-west-1.elb.amazonaws.com";
	private String url = "http://editor.deveu.com/";
	/** The device properties. */
	private HashMap<String, String> deviceProperties;
	/** The menu panel at the top of the page. */
	protected WebUpperLogoPannel LogoPanel;
	private By spreadTheWord	= By.xpath("//*[@ng-click='shell.spreadTheWord()']");
	private By selected	= By.xpath("//selected-country");
	
//	private By selectButton	= By.xpath("//button[@class='pm-select-country-btn']");
//	private By showFullReportLink = By.xpath(".//*[@id='showFullResults']");
	 /**********************************************************************
 	 * 		Constructor
 	 * ********************************************************************.
 	 *
 	 * @param driver the driver
 	 */
	public WebOptimizerBaseView(RemoteWebDriver driver) {
		if (driver!= null) {
			this.driver = driver;
		}
	
	}

	
	/**
	 *  
	 * 		init: initializes the driver.
	 * 		Navigates to Optimizer 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public WebOptimizerBaseView init() throws IOException{
		
		//open url

		driver.get(url);
		Cookie cookie = new Cookie("PM_MARKETO","123");
		driver.manage().addCookie(cookie );
//		("document.cookie=PM_MARKETO=123");
		driver.get(url);

		
		return this;
	}
	
 public boolean isElementVisible(String by) {
		 
		 By xpath = By.xpath(by);
		    try {
		        if (driver.findElement(xpath).isDisplayed()) {
		        	System.out.println("Element is Displayed: " + by);
		        	Reporter.log("Element is Displayed: ");
		        	PerfectoUtils.takeScreenshot(driver);
		            return true;
		        }
		    }
		    catch(Exception e) {       
		    	System.out.println("Element is Not Displayed: " + by);
		    	Reporter.log("Element is Not Displayed: " + by);
		    	PerfectoUtils.getScreenshotOnError(driver);
		        return false;
		    }       

		    return false;

	 }
 public boolean isElementSelected(String by) {
	 
	 By xpath = By.xpath(by);
	    try {
	        if (driver.findElement(xpath).isSelected()) {
	        	System.out.println("Element is Displayed: " + by);
	        	Reporter.log("Element is Displayed: ");
	        	PerfectoUtils.takeScreenshot(driver);
	            return true;
	        }
	    }
	    catch(Exception e) {       
	    	System.out.println("Element is Not Displayed: " + by);
	    	Reporter.log("Element is Not Displayed: " + by);
	    	PerfectoUtils.getScreenshotOnError(driver);
	        return false;
	    }       

	    return false;

 }

	public boolean isCountryDisplayed(String location){
		By xpath = By.xpath("//selected-country[@country='"+location+"']");
		try{
			if(driver.findElement(xpath).isDisplayed()){
				System.out.println(location+" is Displayed \n");
				Reporter.log(location+" is Displayed \n");
				PerfectoUtils.getScreenshotOnError(driver);
				return true;
			}
		}
		  catch(Exception e) {       
		    	System.out.println(location+" is not Displayed");
		    	Reporter.log(location+" is not Displayed");
		    	PerfectoUtils.getScreenshotOnError(driver);
		        return false;
		    }       
		
		return false;
		
	}
	public void deselectLocation(String location){
		By selected = By.xpath("//*[@class='pm-selected-countries']/*[@country='"+location+"']//img");
		if(driver.findElement(selected).isDisplayed()){
			WebElement select = driver.findElement(selected);
			retryClick(select);
		}
		else{
			PerfectoUtils.getScreenshotOnError(driver);
			throw new RuntimeException("elemnt not displayed");
			
		}
		
	}
	
	public List<WebElement> SelectedAudiance(){
		List<WebElement> selectedAudiance = driver.findElements(selected);
		selectedAudiance.size();
		for(int i=0; i <selectedAudiance.size(); i++) {
			String location = selectedAudiance.get(i).getAttribute("country");
			System.out.println(location);
		}
		return selectedAudiance;
		
	}
	
//	public String Weighs (int value, String name, String filter){
//		By xpath = By.xpath("//" +filter + "//priority-section//*[@name='"+ name + "' and @type='radio']");
//		System.out.println(xpath);
//		List<WebElement> radioButtnos = driver.findElementsByXPath("//" +filter + "//priority-section//*[@name='"+ name + "' and @type='radio']");
////		added sleep because the radio button was not available to click only after 1/2 second, the element is visible but not clickable
//
//		PerfectoUtils.fluentWait(xpath, driver, 10);
//		retryClick(value, radioButtnos);
//		String radioText = "";
//		  for (WebElement radioButton : radioButtnos) {
//			  if (radioButton.isSelected()) {
//			//add screenshot
//				   radioText = radioButton.getAttribute("id");
//					String radioTextName = radioButton.getAttribute("name");
//				  	Assert.assertEquals(radioTextName, name);
//					System.out.println(radioText);
//			  }
//		   }
//		return radioText;
//	}
//
//	private void retryClick(int value, List<WebElement> radioButtnos) {
//		boolean click =false;
//		while (!click) {
//			try {
//				radioButtnos.get(value).click();
//				click = true;
//			} catch (Exception e) {
//				System.out.println("*********************qwerty");
//				e.printStackTrace();
//			} 
//		}
//	}	
	public void retryClick( WebElement webElement) {
		boolean click =false;
		while (!click) {
			try {
				webElement.click();
				click = true;
			} catch (Exception e) {
				System.out.println(webElement+" is not clickable");
				Reporter.log(webElement+" is not clickable");
				PerfectoUtils.getScreenshotOnError(driver);
				e.printStackTrace();
			} 
		}
	}
	
}
