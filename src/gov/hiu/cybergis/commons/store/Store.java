package gov.hiu.cybergis.commons.store;

import gov.hiu.cybergis.commons.registry.Registry2;
import gov.hiu.cybergis.commons.utility.Utility;

import java.util.LinkedList;
import java.util.Locale;

public abstract class Store<A,B extends Item_Store<A>> extends Registry2<A,B>
{
	public Store(String id, String label, String type)
	{
		super(id, label, type);
	}
	public String listItemsAsJSON(Locale locale)
	{
		LinkedList<String> json = new LinkedList<String>();
		for(Item_Store<A> item: list)
		{
			json.add(item.asJSON(locale));
		}
		return Utility.join(json.toArray(new String[]{}),",");
	}
	public String listItemsAsJSON(Locale locale, A ids[])
	{
		LinkedList<String> json = new LinkedList<String>();
		for(Item_Store<A> item: list)
		{
			if(item.isID(ids))
			{
				json.add(item.asJSON(locale));
			}
		}
		return Utility.join(json.toArray(new String[]{}),",");
	}
	public String getItemAsJSON(Locale locale, A id)
	{
		return getItemAsJSON(locale, id, null);
	}
	public String getItemAsJSON(Locale locale, A id, String fallback)
	{
		String json = fallback;
		for(Item_Store<A> item: list)
		{
			if(item.isID(id))
			{
				json = item.asJSON(locale);
				break;
			}
		}
		return json;
	}
	public String listItemsAsXML(Locale locale)
	{
		LinkedList<String> xml = new LinkedList<String>();
		for(Item_Store<A> item: list)
		{
			xml.add(item.asJSON(locale));
		}
		return Utility.join(xml.toArray(new String[]{}),",");
	}
	public String listItemsAsXML(Locale locale, A ids[])
	{
		LinkedList<String> xml = new LinkedList<String>();
		for(Item_Store<A> item: list)
		{
			if(item.isID(ids))
			{
				xml.add(item.asJSON(locale));
			}
		}
		return Utility.join(xml.toArray(new String[]{}),",");
	}
	public String getItemAsXML(Locale locale, A id)
	{
		return getItemAsXML(locale, id, null);
	}
	public String getItemAsXML(Locale locale, A id, String fallback)
	{
		String getItemAsXML = fallback;
		for(Item_Store<A> item: list)
		{
			if(item.isID(id))
			{
				getItemAsXML = item.asXML(locale);
				break;
			}
		}
		return getItemAsXML;
	}
}
