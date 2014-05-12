package gov.hiu.cybergis.commons.menu;

import gov.hiu.cybergis.commons.registry.Registry2;

public class Menu
{
	protected Registry2<String,MenuGroup> menuGroups;
	
	public Menu()
	{
		menuGroups = new Registry2<String,MenuGroup>("menuGroups","Menu Groups","menuGroups");
	}
	public boolean addMenuGroup(MenuGroup menuGroup)
	{
		return menuGroups.register(menuGroup);
	}
	public boolean addMenuItem(MenuGroup menuGroup, MenuItem menuItem)
	{
		return menuGroup.addMenuItem(menuItem);
	}
	public boolean addMenuItem(String menuGroup, MenuItem menuItem)
	{
		boolean success = false;
		if(menuGroups.has(menuGroup))
		{
			success = menuGroups.get(menuGroup).addMenuItem(menuItem);
		}
		return success;
	}
}
