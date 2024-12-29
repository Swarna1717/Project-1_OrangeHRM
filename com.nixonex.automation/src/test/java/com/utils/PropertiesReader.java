package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	Properties prop = new Properties();
	public PropertiesReader() {
	//File f1=new File(getClass().getClassLoader().getResource("//config\\config.properties").getFile());
		ClassLoader classLoader = getClass().getClassLoader();
		File f1 = new File(classLoader.getResource("config.properties").getFile());
	
	try {
		FileInputStream fs=new FileInputStream(f1);
		prop.load(fs);
		
	    }
	
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
   public String getData(String key)
   {
		return prop.getProperty(key);
   	
   }
}