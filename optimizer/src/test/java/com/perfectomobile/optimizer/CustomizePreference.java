package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.util.StringUtils;
//import org.springframework.util.Assert;
import org.testng.Assert;

import com.perfectomobile.utils.PerfectoUtils;

import bsh.StringUtil;

public class CustomizePreference extends WebOptimizerBaseView{
	private By addYourData = By.xpath(".//*[@id='addData']");
	private By closeButton = By.xpath(".//*[text()='Close']");
	private By xButton = By.xpath(".//*[@class='ngdialog-close']");
//	private By searchBox = By.xpath(".//*[contains(text(), 'Select an option')]");
	private By searchBox = By.xpath(".//pm-choice");
//			 "[1]/*[@class='pm-device-span']//*[@title='Apple iPad 3']");
	private By inputBox = By.xpath("//input[@class='select2-search__field']");
	private By dropDown = By.xpath("//*[contains(@class,'select2-results')]/li");


	public CustomizePreference(RemoteWebDriver driver) throws IOException {
		super(driver);
		addYourDataButton();
//				validate select location box opened.
		 try{
	        	PerfectoUtils.fluentWait(closeButton, driver, 30);
	        	PerfectoUtils.getScreenShot(driver, "Customize preference");
	        }
	        catch(Exception e){
	        	PerfectoUtils.getScreenshotOnError(driver);
	        	throw new IllegalStateException();
	        }
	}
	
	public WebOptimizerBaseView closeButton() throws IOException{
		WebElement close = driver.findElement(closeButton);
		retryClick(close);
		return new WebOptimizerBaseView(driver);
	}
	
	public WebOptimizerBaseView addYourDataButton() throws IOException{
		WebElement addData = driver.findElement(addYourData);
		retryClick(addData);
		return new WebOptimizerBaseView(driver);
	}
	public WebOptimizerBaseView xButton() throws IOException{
		WebElement xbutton = driver.findElement(xButton);
		retryClick(xbutton);
		return new WebOptimizerBaseView(driver);
	}
//	
//	public WebOptimizerBaseView searchBox() throws IOException{
//		driver.findElement(searchBox).click();
//		return new WebOptimizerBaseView(driver);
//	}
	
	public List<WebElement> selectDevices(int row, String device){
		List<WebElement> selectDevices = driver.findElements(searchBox);
		selectDevices.size();
		System.out.println(selectDevices.size());
		Assert.assertEquals(selectDevices.size(), 5);
		PerfectoUtils.getScreenShot(driver, device);;
//		selectDevices.get(row).click();
		retryClick(selectDevices.get(row));
		String selected = inputBox(device);
		return selectDevices;
		
	}

	
	public String inputBox(String string){
		PerfectoUtils.fluentWait(inputBox, driver, 30);
		WebElement search = driver.findElement(inputBox);
		PerfectoUtils.sleep(100);
		String[] split = string.split("");
		for (String string2 : split) {
			search.sendKeys(string2);
		}
//		search.sendKeys(string);
		PerfectoUtils.fluentWait(dropDown, driver, 30);
		PerfectoUtils.sleep(500);
		List<WebElement> dropDownBox = driver.findElements(dropDown);

	try{
		if(dropDownBox.size()>2){
			System.out.println(dropDownBox.size());
		}
		System.out.println(string);
	}	 catch(Exception e){
		System.out.println("dropbox did not load");
		e.printStackTrace();
	}
			for (int i = 0; i < dropDownBox.size(); i++) {
				System.out.println(dropDownBox.get(i).getText());
				PerfectoUtils.sleep(100);
				String name = dropDownBox.get(i).getText();
				
				if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(name, string)) {
					System.out.print(name);
					PerfectoUtils.takeScreenshot(driver);
				} else {
					System.out.println("the "+ name +" is not compatible and does not contain the " + string);
					PerfectoUtils.getScreenshotOnError(driver);
				} }
				
		return string;
		
	}



	public String chooseDevice(String clickDevice, String expected) {
		WebElement chooseDevice = driver.findElement(By.xpath("//*[@class='select2-results']//*[contains(text(),'"+clickDevice +"')]"));
		retryClick(chooseDevice);
		WebElement chosenName = driver.findElement(By.xpath(".//*[@class='selection']//*[contains(@title,'"+clickDevice +"')]"));
		String actualName = chosenName.getText();
//		String newActualName = StringUtils.trimLeadingCharacter(actualName, "x");
		actualName = actualName.substring(1);
		System.out.println(actualName + expected);
		Assert.assertEquals(actualName.replaceAll("\\s+" , "") ,expected.replaceAll(" " , ""));
//		chooseDevice.click();
		return actualName;
	}
	
	public void dragDrop(int row, int row1){
		By dragRow = By.xpath("//pm-choice["+row+"]//img");
		By dropRow = By.xpath("//pm-choice["+row1+"]//img");
		WebElement element = driver.findElement(dragRow);
		WebElement target = driver.findElement(dropRow);
		(new Actions(driver)).dragAndDrop(element, target).perform();
		
	}
//	public String checkChosen(String expected) {
//		WebElement chosenName = driver.findElement(By.xpath(".//*[@class='selection']//*[contains(@title,'"+expected +"')]"));
//		String actualName = chosenName.getText();
//		Assert.assertEquals(actualName, expected);
////		chooseDevice.click();
//		return expected;
//	}
	


	private By ByXPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cleanInput(int row) {
		WebElement clean = driver.findElementByXPath(".//*[contains(@class,'selection__clear')]");
		clean.click();
		PerfectoUtils.sleep(1000);
		By arrow = By.xpath(".//*[contains(@class,'selection__arrow')]");
		List<WebElement> close = driver.findElements(arrow);
		System.out.println(close.size());
		close.get(row).click();
		
		
	}

}
