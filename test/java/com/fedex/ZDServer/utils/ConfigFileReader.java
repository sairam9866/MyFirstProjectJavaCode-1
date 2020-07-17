package com.fedex.ZDServer.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFileReader {

	public String reportConfigPath = System.getProperty("user.dir")+ "/src/test/resources/configs/extent-config.xml";
	public static String envFilePath = System.getProperty("user.dir") + "/src/test/resources/configs/env.properties";
	public static String dataFilePath = System.getProperty("user.dir") +  "/src/test/resources/configs/data.properties";
	
	public static Properties ZipToDestAPIProp;
	public static Properties ZipToDestAPIPropWrite;
	
	//@SuppressWarnings("unused")
	public String getReportConfigPath() {

		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException("Report Config Path not specified in the Configuration :reportConfigPath");
	}

	public static void loadProperties() {

		ZipToDestAPIProp = new Properties();
		try {
			InputStream fis = new FileInputStream(envFilePath);
			ZipToDestAPIProp.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	
	public static void writeProperties() {
		
		
		try {
			OutputStream output = new FileOutputStream(dataFilePath);
			ZipToDestAPIPropWrite = new Properties();
			ZipToDestAPIPropWrite.store(output, null);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
