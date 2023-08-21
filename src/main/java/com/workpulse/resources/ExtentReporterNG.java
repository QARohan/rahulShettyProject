package com.workpulse.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"\\Reports\\index.html"; 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Workpulse Automation Framework !");
		reporter.config().setDocumentTitle("Workpulse Test Result !");
		
		//Main extent report creation 
		ExtentReports extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Tester", "Rohan Gupta");
		 return extent;
	}

}
