package gov.hiu.cybergis.commons.triggers;

import gov.hiu.cybergis.commons.conditions.Condition;
import gov.hiu.cybergis.commons.effects.Effect;
import gov.hiu.cybergis.commons.utility.Utility;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;

public class Trigger<S>
{
	private String id;
	private LinkedList<Condition<S>> conditions;
	private HashMap<String,Condition<S>> map_conditions;
	private Effect effects[];
	private HashMap<String,Effect> map_effects;
	public Trigger(String id)
	{
		this.id = id;
		
		this.conditions = new LinkedList<Condition<S>>();
		map_conditions = new HashMap<String,Condition<S>>();
		
		this.effects = new Effect[0];
		map_effects = new HashMap<String,Effect>();
	}
	public Trigger(String id,Effect effect)
	{
		this.id = id;
		
		this.conditions = new LinkedList<Condition<S>>();
		map_conditions = new HashMap<String,Condition<S>>();
		
		this.effects = new Effect[]{effect};
		map_effects = new HashMap<String,Effect>();
		map_effects.put(effect.getID(),effect);
	}
	public Trigger(String id,Effect effects[])
	{
		this.id = id;
		
		this.conditions = new LinkedList<Condition<S>>();
		map_conditions = new HashMap<String,Condition<S>>();
		
		this.effects = effects;
		map_effects = new HashMap<String,Effect>();
		for(Effect effect: effects)
			map_effects.put(effect.getID(),effect);
	}
	public Trigger(String id,Condition<S> conditions[],Effect effects[])
	{
		this.id = id;
		
		this.conditions = new LinkedList<Condition<S>>();
		map_conditions = new HashMap<String,Condition<S>>();
		for(Condition<S> condition: conditions)
			map_conditions.put(condition.getID(),condition);
		
		this.effects = effects;
		map_effects = new HashMap<String,Effect>();
		for(Effect effect: effects)
			map_effects.put(effect.getID(),effect);
	}
	public String getID()
	{
		return id;
	}
	public boolean execute(Connection db, S state,boolean stderr,PrintWriter log,int user)
	{
		if(test(state, stderr, log, user))
		{
			boolean success[] = new boolean[effects.length];
			for(int i = 0; i < effects.length; i++)
			{
				Effect effect = effects[i];
				success[i] = effect.execute(db, stderr, log, user);
			}
			return Utility.and(success);
		}
		else
			return true;		
	}
	private boolean test(S state,boolean stderr,PrintWriter log,int user)
	{
		boolean valid[] = new boolean[conditions.size()];
		for(int i = 0; i < conditions.size(); i++)
		{
			valid[i] = conditions.get(i).test(state,stderr, log, user);
		}
		return Utility.and(valid);
	}
	/**
	 * 
	 * @param state
	 * @param name
	 * @param force - bypass conditions
	 * @param stderr
	 * @param log
	 * @param user
	 * @return
	 */
	public boolean execute(Connection db, S state,String name,boolean force,boolean stderr,PrintWriter log,int user)
	{
		if(force)
		{
			Effect effect = map_effects.get(name);
			if(effect!=null)
				return effect.execute(db, stderr, log, user);
			else
				return false;
		}
		else
		{
			if(test(state, stderr, log, user))
			{
				Effect effect = map_effects.get(name);
				if(effect!=null)
					return effect.execute(db, stderr, log, user);
				else
					return false;
			}
			else
				return true;
		}
		
	}
}
