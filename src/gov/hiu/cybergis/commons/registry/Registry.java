package gov.hiu.cybergis.commons.registry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Registry<A,B,C extends Item_Registry<B>>
{
	protected A id;
	protected String label;
	protected String type;
	protected HashMap<B,C> map;
	protected LinkedList<C> list;
	
	public Registry(A id, String label, String type)
	{
		this.id = id;
		this.label = label;
		this.type = type;
		this.map = new HashMap<B,C>();
		this.list = new LinkedList<C>();
	}
	public A getID()
	{
		return id;
	}	
	public String getLabel()
	{
		return label;
	}
	public String getType()
	{
		return type;
	}
	public int getSize()
	{
		return list.size();
	}
	public boolean[] register(C items[], boolean breakOnError)
	{
		if(items==null)
		{
			return null;
		}
		else
		{
			boolean success[] = new boolean[items.length];
			for(int i = 0; i < items.length; i++)
			{
				success[i] = register(items[i]);
				if(breakOnError&&(!success[i]))
				{
					break;
				}
			}
			return success;
		}
	}
	public boolean[] register(List<C> items, boolean breakOnError)
	{
		boolean success[] = new boolean[items.size()];
		for(int i = 0; i < items.size(); i++)
		{
			success[i] = register(items.get(i));
			if(breakOnError&&(!success[i]))
			{
				break;
			}
		}
		return success;
	}
	public boolean register(C item)
	{
		return register(item, false, -1);
	}
	public boolean register(C item, int position)
	{
		return register(item, false, position);
	}
	public boolean register(C item, boolean override, int position)
	{
		boolean registered = false;
		if(override)
		{
			if(item!=null)
			{
				if(map!=null)
				{
					map.put(item.getID(), item);
					if(position==-1)
						list.add(item);
					else
						list.add(position, item);
					registered = true;	
				}
			}		
		}
		else
		{
			if(item!=null)
			{
				if(map!=null)
				{
					if(map.containsKey(item.getID()))
						registered = false;
					else
					{
						map.put(item.getID(), item);
						if(position==-1)
							list.add(item);
						else
							list.add(position, item);
						registered = true;
					}			
				}
			}
		}
		return registered;
	}
	
	public boolean unregister(B key)
	{
		boolean success = false;
		if(key!=null)
		{
			if(map!=null)
			{
				if(map.containsKey(key))
				{
					C obj = map.remove(key);
					if(obj!=null)
					{
						list.remove(obj);
					}
					success = true;
				}
			}
		}
		return success;
	}

	public boolean has(B key)
	{
		if(map!=null)
			return map.containsKey(key);
		else
			return false;
	}
	public C get(B key)
	{
		if(map!=null)
		{
			if(has(key))
				return map.get(key);
			else
				return null;
		}
		else
			return null;
	}
	public HashMap<B,C> getMap()
	{
		return map;
	}
	public LinkedList<C> getList()
	{
		return list;
	}

	public C getFirst()
	{
		return list.getFirst();
	}
	public C getLast()
	{
		return list.getLast();
	}
}
