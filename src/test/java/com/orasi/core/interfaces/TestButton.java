package com.orasi.core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orasi.core.interfaces.Button;
import com.orasi.core.interfaces.impl.internal.ElementFactory;
import com.orasi.utils.TestEnvironment;


public class TestButton extends TestEnvironment{
    @BeforeTest(groups ={"regression", "interfaces", "button", "dev"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion", "operatingSystem", "environment", "deviceName", "deviceOrientation" })
    public void setup(@Optional String runLocation, String browserUnderTest,
	    String browserVersion, String operatingSystem, String environment, @Optional String deviceName, @Optional String deviceOrientation) {
	setApplicationUnderTest("Test Site");
	setBrowserUnderTest(browserUnderTest);
	setBrowserVersion(browserVersion);
	setOperatingSystem(operatingSystem);
	setRunLocation(runLocation);
	setTestEnvironment(environment);
	setDeviceName(deviceName);
	setDeviceOrientation(deviceOrientation);
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/core/interfaces/button.html");	
	setTestEnvironment(environment);
	testStart("TestButton");
	}
    
    @AfterTest(groups ={"regression", "interfaces", "button", "dev"})
    public void close(ITestContext testResults){
	endTest("TestAlert", testResults);
    }

    @Test(groups ={"regression", "interfaces", "button"})
    public void click(){
   Button button= getDriver().findButton(By.id("click"));
	button.click();
	button.getWrappedDriver();
	Assert.assertTrue(getDriver().findElement(By.id("testClick")).getText().equals("Successful"));
    }
    
    @Test(groups ={"regression", "interfaces", "button"}, dependsOnMethods="click")
    public void jsClick(){
	Button button= getDriver().findButton(By.id("jsClick"));
	button.jsClick();
	Assert.assertTrue(getDriver().findButton(By.id("testJsClick")).getText().equals("Successful"));
    }
}
