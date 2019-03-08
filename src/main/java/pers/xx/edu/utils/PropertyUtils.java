package pers.xx.edu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertyUtils {
	public  Properties getProperties() throws IOException{
		Properties properties=new Properties();
		InputStream  is= this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
		properties.load(inputStreamReader);
		System.out.println(properties.getProperty("file.upload.path"));
		return properties;
	}
	public Properties testPro() throws IOException{
		Resource resource = new ClassPathResource("jdbc.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println(props.getProperty("file.upload.path"));
		return props;
	}
}
