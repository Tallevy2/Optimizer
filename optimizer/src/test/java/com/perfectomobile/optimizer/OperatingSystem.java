package com.perfectomobile.optimizer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.PerfectoUtils;

public class OperatingSystem extends WebOptimizerBaseView {

	public OperatingSystem(RemoteWebDriver driver) {
		super(driver);
	}
	public boolean SelectOS(String OS){
		By OPS = By.xpath("//os-selector[@os='" + OS + "']//*[@ng-click='os.osClicked(osName)']");
		System.out.println(OPS);
		WebElement selectOS = driver.findElement(OPS);
		PerfectoUtils.fluentWait(OPS, driver, 30);
		PerfectoUtils.sleep(5000);
		retryClick(selectOS);
//		selectOS.click();
		return selectOS.findElement(By.xpath("//os-selector[@os='"+OS+"']")).isSelected();
	}

}
