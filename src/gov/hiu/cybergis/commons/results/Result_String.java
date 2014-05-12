package gov.hiu.cybergis.commons.results;

import java.util.Locale;

import gov.hiu.cybergis.commons.builders.JSONBuilder;
import gov.hiu.cybergis.commons.utility.Utility;

public class Result_String extends Result
{
	private Integer id;
	private boolean success;
	private int code;
	private String message;
	private String value;
	
	public Result_String(boolean success, int code, String message, String value)
	{
		super(success, code, message);
		this.value = value;
	}
	public String value()
	{
		return value;
	}
	
	public String asJSON(Locale locale)
	{
		String json[] = new String[5];
		
		json[0] = JSONBuilder.longField(locale,"id",id.longValue());
		json[1] = JSONBuilder.booleanField(locale,"success",success);
		json[2] = JSONBuilder.integerField(locale,"code",code);
		json[3] = JSONBuilder.stringField(locale,"message",message);
		json[4] = JSONBuilder.stringField(locale,"value",value);
		
		return "{"+Utility.join(json,",")+"}";
	}
	public String asHTML(Locale locale)
	{
		String html = "";
		html += "<span style=\"color:"+(success?"green":"red")+";\">Result "+id.longValue()+":"+(success?"success":"failed")+" with code "+code+" ("+message+").  Returned "+value+" value.</span>";		
		return html;
	}
	public String asXML(Locale locale)
	{
		String xml[] = new String[7];
		
		xml[0] = "<result>";
		xml[1] = "<id>"+id.longValue()+"</id>";
		xml[2] = "<success>"+(success?"true":"false")+"</success>";
		xml[3] = "<code>"+code+"</code>";
		xml[4] = "<message>"+message+"</message>";
		xml[5] = "<value>"+value+"</value>";
		xml[6]= "</result>";
		
		return Utility.join(xml,"");
	}
}
