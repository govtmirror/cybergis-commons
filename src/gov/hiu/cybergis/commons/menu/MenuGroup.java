package gov.hiu.cybergis.commons.menu;

import gov.hiu.cybergis.commons.registry.Item_Registry;
import gov.hiu.cybergis.commons.registry.Registry2;
import gov.hiu.cybergis.commons.utility.Utility;

import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

public class MenuGroup
	implements Item_Registry<String>
{
	private String id;
	private String name;
	private String url;
	private boolean allowAllUsers = false;
	private LinkedList<String> roles;	
	private Registry2<String,MenuItem> menuItems;
	
	public MenuGroup(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.allowAllUsers = false;
		this.url = null;
	}
	public MenuGroup(String id, String name,boolean allowAllUsers)
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.allowAllUsers = allowAllUsers;
		this.url = null;
	}
	public MenuGroup(String id, String name,String role)
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.roles.add(role);
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.allowAllUsers = false;
		this.url = null;
	}
	public MenuGroup(String id, String name, String role, String url)
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.roles.add(role);
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.allowAllUsers = false;
		this.url = url;
	}
	public MenuGroup(String id, String name, boolean allowAllUsers, String url)
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.allowAllUsers = allowAllUsers;
		this.url = url;
	}
	public MenuGroup(String id, String name,String roles[],MenuItem menuItems[])
	{
		this.id = id;
		this.name = name;
		this.roles = new LinkedList<String>();
		this.roles.addAll(Arrays.asList(roles));
		this.menuItems = new Registry2<String,MenuItem>("menuItems","Menu Items","menuItems");
		this.menuItems.register(menuItems,false);
		this.allowAllUsers = false;
		this.url = null;
	}
	public void addRole(String role)
	{
		roles.add(role);
	}

	public boolean addMenuItem(MenuItem menuItem)
	{
		return this.menuItems.register(menuItem);
	}	
	public String getName()
	{
		return name;
	}
	public boolean hasURL()
	{
		return url!=null;
	}
	public String getURL()
	{
		return url;
	}
	public boolean hasRole(HttpServletRequest request)
	{
		boolean hasRole = false;
		if(allowAllUsers)
		{
			hasRole = true;
		}
		else
		{
			for(int i = 0; i < roles.size(); i++)
			{
				String role = roles.get(i);
				if(request.isUserInRole(role))
				{
					hasRole = true;
					break;
				}
			}
		}		
		return hasRole;
	}
	public LinkedList<MenuItem> getItems()
	{
		return this.menuItems.getList();
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
