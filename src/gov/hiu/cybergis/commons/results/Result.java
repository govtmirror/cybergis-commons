package gov.hiu.cybergis.commons.results;

import java.util.Locale;

import gov.hiu.cybergis.commons.builders.JSONBuilder;
import gov.hiu.cybergis.commons.interfaces.JSONInterface;
import gov.hiu.cybergis.commons.interfaces.XMLInterface;
import gov.hiu.cybergis.commons.registry.Item_Registry;
import gov.hiu.cybergis.commons.utility.Utility;

public class Result
	implements Item_Registry<Long>, JSONInterface, XMLInterface
{
	protected static long ids = 0;
	
	private Long id;
	private boolean success;
	private int code;
	private String message;
	
	public Result(boolean success, int code, String message)
	{
		this.id = ids++;
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public Result(Long id, boolean success, int code, String message)
	{
		this.id = id;
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public boolean success()
	{
		return success;
	}
	public boolean failed()
	{
		return !success;
	}
	public int code()
	{
		return code;
	}
	
	public String asJSON(Locale locale)
	{
		String json[] = new String[4];
		
		json[0] = JSONBuilder.longField(locale,"id",id);
		json[1] = JSONBuilder.booleanField(locale,"success",success);
		json[2] = JSONBuilder.integerField(locale,"code",code);
		json[3] = JSONBuilder.stringField(locale,"message",message);
		
		return "{"+Utility.join(json,",")+"}";
	}
	public String asHTML(Locale locale)
	{
		String html = "";
		html += "<span style=\"color:"+(success?"green":"red")+";\">Result "+id.longValue()+":"+(success?"success":"failed")+" with code "+code+" ("+message+")</span>";		
		return html;
	}
	public String asXML(Locale locale)
	{
		String xml[] = new String[6];
		
		xml[0] = "<result>";
		xml[1] = "<id>"+id.longValue()+"</id>";
		xml[2] = "<success>"+(success?"true":"false")+"</success>";
		xml[3] = "<code>"+code+"</code>";
		xml[4] = "<message>"+message+"</message>";
		xml[5]= "</result>";
		
		return Utility.join(xml,"");
	}
	
	public boolean isID(Long id)
	{
		return this.id.longValue()==id;
	}	
	public boolean isID(Long[] ids)
	{
		return Utility.contains(ids,id);
	}	
	public Long getID()
	{
		return id;
	}
}
