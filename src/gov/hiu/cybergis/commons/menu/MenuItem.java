package gov.hiu.cybergis.commons.menu;

import gov.hiu.cybergis.commons.registry.Item_Registry;
import gov.hiu.cybergis.commons.utility.Utility;

public class MenuItem
	implements Item_Registry<String>
{
	private String id;
	private String name;
	private String url;
	private boolean draggable;
	
	public MenuItem(String id, String name, String url, boolean draggable)
	{
		this.id = id;
		this.name = name;
		this.url = url;
		this.draggable = draggable;
	}
	public String getName()
	{
		return name;
	}
	public String getURL()
	{
		return url;
	}
	public boolean isDraggable()
	{
		return draggable;
	}
	@Override
	public boolean isID(String id)
	{
		return this.id.equalsIgnoreCase(id);
	}
	@Override
	public boolean isID(String[] ids)
	{
		return Utility.contains(ids,id,true);
	}
	@Override
	public String getID()
	{
		return id;
	}
}
