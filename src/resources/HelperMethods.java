package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class HelperMethods 
{
	public static XmlPath rawToXML(Response r)
	{
		String res = r.asString();
		XmlPath x = new XmlPath(res);
		return x;
	}

	public static JsonPath rawToJson(Response r)
	{
		String res = r.asString();
		JsonPath x = new JsonPath(res);
		return x;
	}
}
