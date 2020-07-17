package com.fedex.ZDServer.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.fedex.ZDServer.utils.ConfigFileReader;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
tags={"@ZDServerTests"},
format = {"pretty","html:target/site/cucumber-pretty",
"json:target/cucumber.json"},
monochrome=true
)
public class CucumberIT extends SpringBootBaseIT {
	
	static String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	static String reportpath = null;
	
	@BeforeClass
	public static void setUp() throws IOException {

		ConfigFileReader.loadProperties();
		ConfigFileReader.writeProperties();
		
	}

	@AfterClass
	public static void writeExtentReport() { 
		
	}
}