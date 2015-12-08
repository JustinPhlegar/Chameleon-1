package com.orasi.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestFrameHandler extends TestEnvironment{
    @BeforeTest(groups ={"regression", "utils", "dev", "framehandler"})
    @Parameters({ "runLocation", "browserUnderTest", "browserVersion",
	    "operatingSystem", "environment", "deviceName", "deviceOrientation" })
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
	setDefaultTestTimeout(3);
	setPageURL("http://orasi.github.io/Selenium-Java-Core/sites/unitTests/orasi/utils/frameHandler.html");
	testStart("TestFrame");
    }
    

    @AfterTest(groups ={"regression", "utils", "dev"})
    public void close(ITestContext testResults){
	endTest("TestFrame", testResults);
    }
    
    @Test(groups ={"regression", "utils", "dev", "framehandler"})
    public void findAndSwitchToFrameFromOutsideFrame(){
	String test = FrameHandler.getCurrentFrameName(getDriver());
	FrameHandler.findAndSwitchToFrame(getDriver(), "menu_page" );
	Assert.assertTrue("Link was not found in 'menu_page'", getDriver().findElement(By.id("googleLink")).isDisplayed());
    }

    @Test(groups ={"regression", "utils", "dev", "framehandler"}, dependsOnMethods="findAndSwitchToFrameFromOutsideFrame")
    public void testGetCurrentFrameName(){
	Assert.assertTrue("Frame name was not 'menu_page' ", FrameHandler.getCurrentFrameName(getDriver()).equals("menu_page"));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testGetCurrentFrameName")
    public void testGetDefaultContent(){
	FrameHandler.moveToDefaultContext(getDriver());
	Assert.assertNull("Failed to move to default Content",  FrameHandler.getCurrentFrameName(getDriver()));
    }

    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testGetDefaultContent")
    public void testMoveToChildFrameWithName(){
	FrameHandler.moveToChildFrame(getDriver(), "main_page" );
	Assert.assertTrue("Button was not found in frame 'main_page'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_page"));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToChildFrameWithName")
    public void testMoveToChildFrameWithLocator(){
	By locator = By.name("main_frame1");
	FrameHandler.moveToChildFrame(getDriver(), locator);
	Assert.assertTrue("Button was not found in frame 'main_frame1'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_frame1"));
    }
    

    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToChildFrameWithLocator")
    public void testMoveToParentFrame(){
	if(this.browserUnderTest.toLowerCase().contains("safari") || driver.toString().contains("safari") ) throw new SkipException("Test not valid for SafariDriver");
	FrameHandler.moveToParentFrame(getDriver());
	Assert.assertTrue("Button was not found in frame 'main_page'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_page"));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToParentFrame")
    public void testMoveToSiblingFrameWithName(){
	if(this.browserUnderTest.toLowerCase().contains("safari") || driver.toString().contains("safari") ) throw new SkipException("Test not valid for SafariDriver");
	FrameHandler.moveToChildFrame(getDriver(), "main_frame1" );
	FrameHandler.moveToSiblingFrame(getDriver(), "main_frame2" );
	Assert.assertTrue("Button was not found in frame 'main_page'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_frame2"));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToSiblingFrameWithName")
    public void testMoveToSiblingFrameWithLocator(){
	if(this.browserUnderTest.toLowerCase().contains("safari") || driver.toString().contains("safari") ) throw new SkipException("Test not valid for SafariDriver");
	By locator = By.name("main_frame1");
	FrameHandler.moveToSiblingFrame(getDriver(), locator);
	Assert.assertTrue("Button was not found in frame 'main_frame1'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_frame1"));
    }
    
    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToSiblingFrameWithLocator")
    public void testMoveToMultiChildFrameWithName(){
	FrameHandler.moveToDefaultContext(getDriver());
	FrameHandler.moveToChildFrame(getDriver(), new String[]{"main_page","main_frame1"});
	Assert.assertTrue("Button was not found in frame 'main_frame1'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_frame1"));
    }

    @Test(groups ={"regression", "utils", "dev"}, dependsOnMethods="testMoveToMultiChildFrameWithName")
    public void testMoveToMultiChildFrameWithLocator(){
	By locatorParentFrame = By.name("main_page");
	By locatorChildFrame = By.name("main_frame1");
	FrameHandler.moveToDefaultContext(getDriver());
	FrameHandler.moveToChildFrame(getDriver(), new By[]{locatorParentFrame, locatorChildFrame});
	Assert.assertTrue("Button was not found in frame 'main_frame1'", FrameHandler.getCurrentFrameName(getDriver()).equals("main_frame1"));
    }
   
}
