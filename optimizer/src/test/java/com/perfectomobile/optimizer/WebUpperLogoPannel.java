package com.perfectomobile.optimizer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.PerfectoUtils;


public class WebUpperLogoPannel{


	private boolean pageloaded;	
	private	By PerfectoLogo	= By.xpath("//*[@class='pm-logo']/a");
	private String AppName = "//*[@class='pm-logo']/h2";
	private RemoteWebDriver driver;
	
	/**
	 * 		Constructor.
	 *
	 * @param driver the driver
	 * @throws IOException 
	 */
	public WebUpperLogoPannel(RemoteWebDriver driver) throws IOException{
		this.driver = driver;	  
        //validate page loaded successfully before proceeding
        try{
        	PerfectoUtils.fluentWait(PerfectoLogo, this.driver, 30);
              	try {
        		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        		this.driver.findElement(By.xpath(AppName));
        		this.pageloaded = true;
				
			} catch (Exception e) {
				
				this.pageloaded = false;
			}
        	
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        catch(Exception e){
        	
        	throw new IllegalStateException();
        }}
        
   //function that describes the click on the perfecto logo link to perfecto site
	
        public WebUpperLogoPannel openPerfectoSite() throws IOException{
        
    		driver.findElement(PerfectoLogo).click();
    		return new PerfectoSite(driver);
    		
        }
}
