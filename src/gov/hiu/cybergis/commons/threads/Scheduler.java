package gov.hiu.cybergis.commons.threads;

import gov.hiu.cybergis.commons.registry.Registry2;

import java.util.Timer;

public abstract class Scheduler
{
	private String name;
	private Timer timer;
	private Registry2<Integer,AbstractThread> threads;
	
	public Scheduler(String name)
	{
		this.name = name;
		this.timer = new Timer(name, true);
	}
	public String getName()
	{
		return name;
	}
	public AbstractThread get(int id)
	{
		return threads.get(id);
	}
	public boolean exists(int id)
	{
		return threads.has(id);
	}
	
	public boolean schedule(AbstractThread thread, long delay, long period)
	{
		boolean success = threads.register(thread);
		if(success)
		{
			timer.schedule(thread, delay, period);
		}
		return success;
	}
	public abstract boolean unschedule(int id);
	public boolean remove(int id)
	{
		return threads.unregister(id);
	}
}
