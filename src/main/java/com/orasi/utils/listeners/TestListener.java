package com.orasi.utils.listeners;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.orasi.utils.Constants;
import com.orasi.utils.OrasiDriver;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;
import com.orasi.utils.mustard.Mustard;

import ru.yandex.qatools.allure.annotations.Attachment;

public class TestListener extends TestListenerAdapter implements IReporter{
	private OrasiDriver driver = null;
	private String runLocation = "";
	private boolean reportToMustard = true;
	private void init(ITestResult result){

	    Object currentClass = result.getInstance();
	    reportToMustard = ((TestEnvironment) currentClass).isReportingToMustard();
	    try{
		runLocation = ((TestEnvironment) currentClass).getRunLocation().toLowerCase();		
	    }catch (Exception e){}
	    
	    try{
		driver = ((TestEnvironment) currentClass).getDriver();
	    }catch (Exception e){}
	    
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		init(result);
		String slash = Constants.DIR_SEPARATOR;

		String destDir = Constants.SCREENSHOT_FOLDER + slash + result.getInstanceName().replace(".", slash);
		
		Reporter.setCurrentTestResult(result);
		
		WebDriver augmentDriver= driver.getWebDriver();
		if(!(augmentDriver instanceof HtmlUnitDriver)){
			if (runLocation == "remote" ){
				augmentDriver = new Augmenter().augment(driver.getWebDriver());
			}
		
			new File(destDir).mkdirs();
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
	
			String destFile = dateFormat.format(new Date()) + ".png";
	
			//Capture a screenshot for TestNG reporting
			TestReporter.logScreenshot(augmentDriver, destDir + slash + destFile, slash, runLocation);
			//Capture a screenshot for Allure reporting
			failedScreenshot(augmentDriver);
		}		
		
		if(reportToMustard) Mustard.postResultsToMustard(driver, result, runLocation );	 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// will be called after test will be skipped
		init(result);		
		if(reportToMustard) Mustard.postResultsToMustard(driver, result, runLocation );
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// will be called after test will pass
		init(result);
		if(reportToMustard) Mustard.postResultsToMustard(driver, result, runLocation );
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites,
		List<ISuite> suites, String outputDirectory) {
	    // TODO Auto-generated method stub
	    
	}

	@Attachment(type = "image/png")
	public static byte[] failedScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
