package gov.hiu.cybergis.commons.triggers;

import java.util.HashMap;

public class Triggers
{
	private HashMap<String,Trigger> triggers;
	public Triggers()
	{
		triggers = new HashMap<String,Trigger>();
	}
	public Trigger remove(String id)
	{
		return triggers.remove(id);
	}
	public void add(Trigger effect)
	{
		triggers.put(effect.getID(),effect);
	}
	public Trigger get(String id)
	{
		if(has(id))
			return triggers.get(id);
		else
			return null;
	}
	public boolean has(String id)
	{
		boolean valid = false;
		if(id!=null)
		{
			if(id.length()>0)
			{
				valid = triggers.containsKey(id);
			}
		}
		return valid;
	}	
}
