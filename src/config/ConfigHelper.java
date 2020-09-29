package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper 
{
	InputStream input;
	private String url;
	Properties prop;
	
	private ConfigHelper()
	{
		try {
			readProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static ConfigHelper helper = new ConfigHelper();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static ConfigHelper getHelper() {
		return helper;
	}

	public void readProperties() throws IOException
	{
		try
		{
			input = new FileInputStream("config.properties");
			prop = new Properties();
			prop.load(input);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("File not found");
		}
		
		url = prop.getProperty("URL");
	}
	
	
}
