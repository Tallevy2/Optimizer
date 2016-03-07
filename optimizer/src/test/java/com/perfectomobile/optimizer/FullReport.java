package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class FullReport extends WebOptimizerBaseView{
	private By changePreference = By.xpath("//*[text()='Change Preferences?']");
	private By share = By.xpath("//*[text()='SPREAD THE WORD']");
	List<WebElement> fullReportlist;
	public FullReport(RemoteWebDriver driver) {
		super(driver);
		PerfectoUtils.fluentWait(changePreference, driver, 20);
		fullReportlist = driver.findElements(By.xpath(".//*[contains(@id, 'uiGrid-0005-cell')]"));
		System.out.println(fullReportlist.size());
//		check if it reports the size of the element list
		 Reporter.getOutput().size();
		try{
			System.out.println("there are "+fullReportlist.size()+" Devices on page");	
			Reporter.log("there are "+fullReportlist.size()+" Devices on page");
			Assert.assertTrue(fullReportlist.size()>5, "list is not empty");
		
		
			}catch(Exception e){
			System.out.println("list is empty");
			 Reporter.log("list is empty");
			    PerfectoUtils.getScreenshotOnError(driver);
			e.printStackTrace();
			}
		
	}
	public static List<String> FullReportlist(){
		List<String> fullReportlist = new ArrayList<String>();
		for(int i=0; i < 5; i++) {
		    String deviceName =driver.findElementByXPath(".//*[contains(@id, '-"+i+"-uiGrid-0005-cell')]/label").getText();
		    fullReportlist.add(deviceName);
		    System.out.println(deviceName);
		    Reporter.log(deviceName);
		    PerfectoUtils.getScreenShot(driver, deviceName);
			}
		return fullReportlist;
		
		
	}
//	public  FullReport toWebOptimizerBaseView() throws IOException{
//		retryClick(driver.findElement(changePreference));
//		return new FullReport(driver);
//	}

	public  WebOptimizerBaseView toFullReport() throws IOException{
		retryClick(driver.findElement(changePreference));
		return new WebOptimizerBaseView(driver);
	}
}
