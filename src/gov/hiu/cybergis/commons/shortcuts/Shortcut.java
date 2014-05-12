package gov.hiu.cybergis.commons.shortcuts;

import gov.hiu.cybergis.commons.registry.Item_Registry;
import gov.hiu.cybergis.commons.utility.Utility;

public class Shortcut
	implements Item_Registry<Integer>
{
	private Integer id;
	private String type;
	private String label;	
	private String url;
	
	public Shortcut(Integer id, String type, String label, String url)
	{
		this.id = id;
		this.type = type;
		this.label = label;
		this.url = url;
	}
	@Override
	public boolean isID(Integer id)
	{
		return this.id.intValue()==id.intValue();
	}
	@Override
	public boolean isID(Integer[] ids)
	{
		return Utility.contains(ids,id);
	}
	public Integer getID()
	{
		return id;
	}	
	public String getType()
	{
		return type;
	}
	public String getLabel()
	{
		return label;
	}
	public String getURL()
	{
		return url;
	}
}
