package gov.hiu.cybergis.commons.conditions;

import java.io.PrintWriter;

public abstract class Condition<S>
{
	private String id;
	public Condition(String id)
	{
		this.id = id;
	}
	public String getID()
	{
		return id;
	}
	public abstract boolean test(S state, boolean stderr,PrintWriter log,int user);
}
