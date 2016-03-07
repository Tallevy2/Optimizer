package testng;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.perfectomobile.dataDrivers.excelDriver.*;
import com.perfectomobile.optimizer.CustomizePreference;
import com.perfectomobile.optimizer.DeviceType;
import com.perfectomobile.optimizer.FullReport;
import com.perfectomobile.optimizer.OperatingSystem;
import com.perfectomobile.optimizer.OptimizerUtils;
import com.perfectomobile.optimizer.SelectLocation;
import com.perfectomobile.optimizer.ShowFullResult;
import com.perfectomobile.optimizer.WebOptimizerBaseView;
import com.perfectomobile.test.*;
import com.perfectomobile.utils.PerfectoUtils;
import com.perfectomobile.dataDrivers.*;




public class Optimizer extends BasicTest {

	public long measuredLaunchTime = 0;
	public long measuredNavTime = 0;
	public String appName = "Optimizer";
	public boolean testFail = false;
	public String testMsg = "";
	public int testFailnum = 0;
	
	
	@Factory(dataProvider="factoryData")
	public Optimizer(DesiredCapabilities caps) {
		super(caps);
		

		
	}
	

	 @Test
//	(dataProvider = "logInData") 
	 public void optimizer() throws IOException{ 
			if(this.driver == null){
			validateAndMessage("false", "Device not available: " + caps, true);
//				Assert.fail("Resource not avalable: " + this.deviceDesc);
			}
			WebOptimizerBaseView mobileView = new WebOptimizerBaseView(driver);
		try{
			try {
				String browserName = driver.getCapabilities().getBrowserName();
				System.out.println(browserName);
				Reporter.log(browserName);
				mobileView.init();
				mobileView.isElementVisible("//*[text()='Digital coverage index']");
				Reporter.log("Optimizer is launched");
				PerfectoUtils.getScreenShot(driver, "Optimizer is launched");
			} catch (Exception e) {
				String  siteName = driver.findElementByXPath("//*[text()='Digital coverage index']").getText();
				reportFail("Digital coverage index", siteName, "");
//				PerfectoUtils.getScreenshotOnError(driver);
				e.printStackTrace();
			}
//			target audience		
				
					SelectLocation sl = new SelectLocation(driver);
					String country = sl.selectCountry("Australia");
					mobileView.isCountryDisplayed("Australia");
					PerfectoUtils.getScreenShot(driver, "Australia is chosen");
					String country1 = sl.selectCountry("EU5");
					mobileView.isCountryDisplayed("EU5");
					PerfectoUtils.getScreenShot(driver, "EU5 is chosen");
					String country2 = sl.selectCountry("Germany");
					mobileView.isCountryDisplayed("Germany");
					PerfectoUtils.getScreenShot(driver, "Germany is chosen");
					sl.CloseButton();
					By showFullReportLink = By.xpath(".//*[@id='showFullResults']");
					PerfectoUtils.fluentWait(showFullReportLink, driver, 30);
					PerfectoUtils.getScreenShot(driver, "full report link is available");
					mobileView.isElementVisible(".//*[@id='showFullResults']");
					PerfectoUtils.sleep(500);
					mobileView.deselectLocation("Spain");
					mobileView.SelectedAudiance();
					try{
					int listAudiance = 	mobileView.SelectedAudiance().size();
					String list = mobileView.SelectedAudiance().toString();
					if (listAudiance>0)
						reportPass("audiance was selected", "");
					
				} catch (Exception e) {
					
					reportFailWithMessage("audiance was not defined", "");
//					PerfectoUtils.getScreenshotOnError(driver);
					e.printStackTrace();
				}
			
// 			primary operating system
			
				OperatingSystem operatingSystem = new OperatingSystem(driver);
				operatingSystem.SelectOS("iOS");
				String selectedOS =("//os-selector/*[contains(@class,'selectionBox')]");
			try{
						mobileView.isElementSelected(selectedOS);
						reportPass("selected platform ", "");
					} catch (Exception e) {
						reportFailWithMessage("no selected platform", "");
		//			PerfectoUtils.getScreenshotOnError(driver);
					e.printStackTrace();
				}
				mobileView.isElementVisible(".//*[@id='showFullResults']");
				PerfectoUtils.sleep(500);
			
			
				
				
//			primary device type
				DeviceType deviceType = new DeviceType(driver);
				deviceType.SelectDeviceType("Smartphone");
				String selectedDtype =("//device-selector/*[contains(@class,'selectionBox')]");
				try{
					mobileView.isElementSelected(selectedDtype);
					reportPass("selected dtype","" );
				}
				catch (Exception e) {
					reportFailWithMessage("no selected dtype", "");
	//			PerfectoUtils.getScreenshotOnError(driver);
				e.printStackTrace();
			}

				PerfectoUtils.sleep(500);
				
//			Customize Preference
				CustomizePreference customizePreference = new  CustomizePreference(driver);
//				customizePreference.searchBox();
				customizePreference.selectDevices(0, "Ap");
				customizePreference.chooseDevice("6S","Apple iPhone 6S");
				customizePreference.cleanInput(0);
				customizePreference.selectDevices(0, "s5");
				PerfectoUtils.sleep(500);
				customizePreference.chooseDevice("neo","Samsung Galaxy S5 neo");
				PerfectoUtils.sleep(500);
				customizePreference.selectDevices(1, "B");
				customizePreference.chooseDevice("Q10","BlackBerry Q10");
				PerfectoUtils.sleep(500);
				customizePreference.dragDrop(1,2);
				
				customizePreference.closeButton();
				mobileView.isElementVisible(".//*[@id='showFullResults']");
				
//			ShowFullResult
				ShowFullResult showFullResult = new ShowFullResult(driver);
				List<String> heroesList = showFullResult.HeroesList();
				showFullResult.toFullReport();
//			FullReport
				FullReport fullReport = new FullReport(driver);
				List<String> fullReportlist = FullReport.FullReportlist();
				System.out.println(heroesList.size());
			for (int i = 0; i < heroesList.size(); i++) {
				Assert.assertEquals(fullReportlist.get(i), heroesList.get(i), "actual device name in row "+i+" is the same");
				System.out.println(fullReportlist.get(i)+""+heroesList.get(i));
				
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				reportFailWithMessage(e.getMessage());
			
			}
			
			
	 } 
		private void validateAndMessage(String str, String msg, boolean throwException){
			if (str.equals("false")){
				++this.testFailnum;
	 			this.testFail = true;
	 			this.testMsg += "Fail#"+this.testFailnum+":"+msg+"*** ";
				String errorFile = PerfectoUtils.takeScreenshot(driver);
				Reporter.log("Error screenshot saved in file: " + errorFile);
				if (throwException)
					throw new IllegalStateException();
	 		}
		}

	
	

	 } 




