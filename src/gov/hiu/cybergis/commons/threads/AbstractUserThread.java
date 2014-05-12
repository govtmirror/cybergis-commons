package gov.hiu.cybergis.commons.threads;

import java.util.Locale;

public abstract class AbstractUserThread extends AbstractThread
{
	private int user;
	private Locale locale;	
	
	public AbstractUserThread(Scheduler scheduler, int threadID, String serviceID, long delay, long period, int user, Locale locale)
	{
		super(scheduler,threadID,serviceID,delay,period);
		this.user = user;
		this.locale = locale;
	}
	public int getUser()
	{
		return user;
	}
	public Locale getLocale()
	{
		return locale;
	}
}
