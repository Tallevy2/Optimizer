package testng;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.perfectomobile.optimizer.DeviceType;
import com.perfectomobile.optimizer.FullReport;
import com.perfectomobile.optimizer.OperatingSystem;
import com.perfectomobile.optimizer.ShowFullResult;
import com.perfectomobile.optimizer.WebOptimizerBaseView;
import com.perfectomobile.test.BasicTest;
import com.perfectomobile.utils.PerfectoUtils;

public class OptimizerMiniTest extends BasicTest{
	@Factory(dataProvider="factoryData")
	public OptimizerMiniTest(DesiredCapabilities caps) {
		super(caps);
	}


		 @Test
		 public void optimizerMiniTest() throws IOException{ 
			 if(this.driver == null){
					Assert.fail("Resource not avalable: " + this.deviceDesc);
				}
				WebOptimizerBaseView mobileView = new WebOptimizerBaseView(driver);
			
			try {
				try{
				mobileView.init();
				mobileView.isElementVisible("//*[text()='Digital coverage index']");
				Reporter.log("Optimizer is launched");
				PerfectoUtils.getScreenShot(driver, "Optimizer is launched");
				}catch (Exception e) {
					String  siteName = driver.findElementByXPath("//*[text()='Digital coverage index']").getText();
					reportFail("Digital coverage index", siteName, "");
					e.printStackTrace();
				}
					
				
//	 			primary operating system
					OperatingSystem operatingSystem = new OperatingSystem(driver);
					operatingSystem.SelectOS("iOS");
					PerfectoUtils.sleep(500);
					
//				primary device type
					DeviceType deviceType = new DeviceType(driver);
					deviceType.SelectDeviceType("Smartphone");
					
					PerfectoUtils.sleep(500);
//					ShowFullResult
					ShowFullResult showFullResult = new ShowFullResult(driver);
					List<String> heroesList = showFullResult.HeroesList();
					showFullResult.toFullReport();
//				FullReport
					
					FullReport fullReport = new FullReport(driver);
					List<String> fullReportlist = FullReport.FullReportlist();
					System.out.println(heroesList.size());
					String screenshot = PerfectoUtils.takeScreenshot(driver);
					Reporter.log("<br> <img src=" + screenshot + " style=\"max-width:50%;max-height:50%\" /> <br>");
				for (int i = 0; i < heroesList.size(); i++) {
					try{
					Assert.assertEquals(fullReportlist.get(i), heroesList.get(i), "actual device name in row "+i+"not the same");
					System.out.println(fullReportlist.get(i)+" "+heroesList.get(i));
//					
					}
					catch (Exception e) {
						reportFail(fullReportlist.get(i), heroesList.get(i), "actual device name in row "+i+"not the same");
						e.printStackTrace();
					}
				}
				
				
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				reportFailWithMessage(e.getMessage());
			
			}
		 }

}
