package com.perfectomobile.optimizer;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class OptimizerUtils {
	
	public long measuredLaunchTime = 0;
	public long measuredNavTime = 0;
	public String appName = "Optimizer";
	public boolean testFail = false;
	public String testMsg = "";
	public int testFailnum = 0;
	
	
	public void validateAndMessage(String str, String msg, boolean throwException, RemoteWebDriver driver){
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
	public static Boolean elementExists(RemoteWebDriver driver,By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
	

}
