package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class ShowFullResult extends WebOptimizerBaseView{
	private By showFullReportLink = By.xpath(".//*[@id='showFullResults']");
	private By Heroes = By.xpath(".//*[contains(@class,'pm-heroes-container')]//*[@class='pm-hero-index ng-binding']");
//	private By listHerosNames = By.xpath(".//*[@id='pmHeroes']//*[text()='1']");
	List<WebElement> heroes;

	
	public ShowFullResult(RemoteWebDriver driver){
		super(driver);
		heroes = driver.findElements(Heroes);
		System.out.println(heroes.size());
		try{
			System.out.println("there are only "+heroes.size()+" heroes on page");	
			Reporter.log("there are only "+heroes.size()+" heroes on page");
			Assert.assertEquals(heroes.size(), 5);
		
		
			}catch(Exception e){
			System.out.println("dropbox did not load");
			Reporter.log("dropbox did not load");
			PerfectoUtils.getScreenshotOnError(driver);
			e.printStackTrace();
			}
	}
	public  WebOptimizerBaseView toFullReport() throws IOException{
//		retryClick(driver.findElement(showFullReportLink));
		WebElement element = driver.findElement(showFullReportLink);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
//		this will open the form to enter users mail , the submit will actually return the full report,
//		need to create a page for the form, and change the function name toFullReport() to toForm
		return new WebOptimizerBaseView(driver);
	}
	public List<String> HeroesList(){
		List<String> heroesList = new ArrayList<String>();
		for(int i=1; i < (heroes.size()+1); i++) {
		  String deviceName = driver.findElementByXPath(".//*[@id='pmHeroes']//*[@class='pm-hero ng-scope']/*[text()='"+i+"']/following-sibling::label").getText();
		    heroesList.add(deviceName);
		    System.out.println(deviceName);
			}
		return heroesList;
	}

}
