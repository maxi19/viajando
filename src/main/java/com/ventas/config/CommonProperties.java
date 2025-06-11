package com.ventas.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class CommonProperties {

	
	public static Properties getProperties() throws IOException {
		
	    InputStream inputStream;
	    
		 Properties prop = new Properties();
         String propFileName = "environment.properties";
         inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
         if (inputStream != null) {
             prop.load(inputStream);
         } else {
             throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
         }
		
         inputStream.close();
         
         return prop;
	}
	
	
}
