package com.perfectomobile.optimizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.perfectomobile.utils.PerfectoUtils;

public class DeviceType extends WebOptimizerBaseView{

	public DeviceType(RemoteWebDriver driver) {
		super(driver);
	}
	public boolean SelectDeviceType(String dtype){
		By type = By.xpath("//device-selector[@dtype='" + dtype + "']//*[@ng-click='types.deviceClicked(dtype)']");
		System.out.println(type);
		WebElement selectType = driver.findElement(type);
		PerfectoUtils.fluentWait(type, driver, 30);
		PerfectoUtils.getScreenShot(driver, dtype);
		retryClick(selectType);
		return selectType.findElement(By.xpath("//device-selector[@dtype='"+dtype+"']")).isSelected();
		
	}
}
