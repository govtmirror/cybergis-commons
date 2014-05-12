package gov.hiu.cybergis.commons.effects;

import gov.hiu.cybergis.commons.store.Item_Store;
import gov.hiu.cybergis.commons.utility.Utility;

import java.io.PrintWriter;
import java.sql.Connection;

public abstract class Effect
	implements Item_Store<String>
{
	private String id;
	public Effect(String id)
	{
		this.id = id;
	}
	public String getID()
	{
		return id;
	}
	@Override
	public boolean isID(String id)
	{
		return this.id.equalsIgnoreCase(id);
	}
	@Override
	public boolean isID(String[] ids)
	{
		return Utility.contains(ids,this.id,true);
	}
	public abstract boolean execute(Connection db, boolean stderr,PrintWriter log,int user);
}
