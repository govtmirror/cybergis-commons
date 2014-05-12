package gov.hiu.cybergis.commons.results;

import java.util.LinkedList;
import java.util.Locale;

import gov.hiu.cybergis.commons.registry.Registry2;
import gov.hiu.cybergis.commons.utility.Utility;

public class Results extends Registry2<Long,Result> 
{
	public Results(String id, String label)
	{
		super(id,label,"results");
	}
	public boolean addResult(Result result)
	{
		return register(result);
	}
	public boolean success()
	{
		return getLast().success();
	}
	public boolean failed()
	{
		return !getLast().success();
	}	
	public String asJSON(Locale locale)
	{
		LinkedList<String> json = new LinkedList<String>();
		for(Result result: this.getList())
		{
			json.add(result.asJSON(locale));
		}
		return Utility.join(json.toArray(new String[]{}),",");
	}
	public String asXML(Locale locale)
	{
		LinkedList<String> xml = new LinkedList<String>();
		for(Result result: this.getList())
		{
			xml.add(result.asXML(locale));
		}
		return Utility.join(xml.toArray(new String[]{}),"");
	}
	public String asHTML(Locale locale)
	{
		LinkedList<String> html = new LinkedList<String>();
		for(Result result: this.getList())
		{
			html.add(result.asHTML(locale));
		}
		return Utility.join(html.toArray(new String[]{}),"<br>",false,true);
	}
}
