package gov.hiu.cybergis.commons.builders;

import gov.hiu.cybergis.commons.interfaces.JSONInterface;
import gov.hiu.cybergis.commons.utility.Utility;

import java.util.List;
import java.util.Locale;

public class JSONBuilder
{
	public static String jsonField(Locale locale, String field, JSONInterface a)
	{
		return "\""+field+"\":"+a.asJSON(locale)+"";	
	}
	public static String stringField(Locale locale, String field, String value)
	{
		return "\""+field+"\":\""+Utility.clean(value)+"\"";	
	}
	public static String booleanField(Locale locale, String field, boolean value)
	{
		return "\""+field+"\":"+(value?"true":"false")+"";	
	}
	public static String stringArrayField(Locale locale, String field, String values[])
	{
		return "\""+field+"\":"+(values==null?"null":"["+Utility.join(Utility.wrap(values,"\""),",")+"]")+"";				
	}
	public static String doubleField(Locale locale, String field, Double value)
	{
		return "\""+field+"\":"+value;
	}
	public static String integerField(Locale locale, String field, Integer value)
	{
		return "\""+field+"\":"+value.intValue();
	}
	public static String longField(Locale locale, String field, Long value)
	{
		return "\""+field+"\":"+value.longValue();
	}
	public static String arrayField(Locale locale, String field, String array)
	{
		return "\""+field+"\":"+array+"";
	}
	public static String arrayField(Locale locale, String field, List<JSONInterface> a)
	{
		return "\""+field+"\":"+getJSON(locale,a)+"";	
	}
	public static String getJSON(Locale locale, JSONInterface a[])
	{
		String json[] = new String[a.length];
		for(int i = 0; i < a.length; i++)
		{
			json[i] = a[i].asJSON(locale);
		}	
		return "["+Utility.join(json,",")+"]";
	}
	public static String getJSON(Locale locale, List<JSONInterface> a)
	{
		String json[] = new String[a.size()];
		for(int i = 0; i < a.size(); i++)
		{
			json[i] = a.get(i).asJSON(locale);
		}	
		return "["+Utility.join(json,",")+"]";
	}
}