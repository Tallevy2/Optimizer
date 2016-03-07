package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.perfectomobile.utils.PerfectoUtils;

public class SelectLocation extends WebOptimizerBaseView {
	
	private By selectionBox = By.xpath("//button[contains(@class,'pm-select-country-btn')]");
//	private By selectButton = By.xpath(".//button[text()='Select']");
	private By cancelButton = By.xpath(".//button[text()='Cancel']");
	private By closeButton = By.xpath(".//*[text()='Close']/parent::*");
	private By locationSelect = By.xpath(".//*[@type='checkbox']/following-sibling::label");
	private By changeLocation = By.xpath("//geography-filter//*[text()='Change']");
	private String  selectString ="//*[@type='checkbox' and following-sibling::label[text()=";
	
	public SelectLocation(RemoteWebDriver driver) throws IOException {
		super(driver);
		SelectLocationButton();
//		validate select location box opened.
		 try{
	        	PerfectoUtils.fluentWait(closeButton, driver, 30);
	        	
	        }
	        catch(Exception e){
	        	throw new IllegalStateException();
	        }
		
	}
	
	public String selectCountry(String location){
//		the xpath here defines the input with the specific sibling location
		String xpath = selectString + "'"+location + "']]";
		
		WebElement select = driver.findElementByXPath(xpath);
//		retryClick(select);
		retryClick(select);
		try{
			PerfectoUtils.fluentWait(changeLocation, driver, 30);
		}
		  catch(Exception e){
	        	throw new IllegalStateException();
	        }
		boolean selected = select.isSelected();
//				isSelected();
		System.out.println(location +" is "+selected);
		
		return location;
		
	}
	
	public List<WebElement> SelectedLocations(){
		List<WebElement> selectedLocations = driver.findElements(locationSelect);
		selectedLocations.size();
		for(int i=0; i <selectedLocations.size(); i++) {
			boolean selected = selectedLocations.get(i).isSelected();
			String location = selectedLocations.get(i).getText();
			System.out.println(location +" is "+selected);
		}
		return selectedLocations;
		
	}
	
//		
//	}
	public WebOptimizerBaseView SelectLocationButton() throws IOException{
//		driver.findElement(selectionBox).click();
		WebElement element = driver.findElement(selectionBox);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		return new WebOptimizerBaseView(driver);
	}
	public WebOptimizerBaseView CloseButton() throws IOException{
		WebElement close = driver.findElement(closeButton);
		retryClick(close);
		return new WebOptimizerBaseView(driver);
	}
	

	}



