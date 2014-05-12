package gov.hiu.cybergis.commons.threads;

import gov.hiu.cybergis.commons.registry.Item_Registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.TimerTask;

public abstract class AbstractThread extends TimerTask
	implements Item_Registry<Integer>
{
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_PAUSED = 2;
	public static final int STATUS_STOPPED = 3;
	public static final int STATUS_DONE = 4;
	public static final boolean debug = false;
	
	private Scheduler scheduler;
	protected int threadID;
	protected String serviceID;
	private long delay;
	private long period;
	protected boolean stderr = false;
	protected PrintWriter log;
	
	protected boolean initialized;
	private boolean valid;
	private int status;

	public AbstractThread(Scheduler scheduler, int threadID, String serviceID, long delay, long period)
	{
		this.scheduler = scheduler;
		this.threadID = threadID;
		this.serviceID = serviceID;
		this.delay = delay;
		this.period = period;
		this.stderr = false;
		this.log = null;
		this.initialized = false;
		this.valid = false;
		this.status = STATUS_STOPPED;
	}
	
	public abstract boolean initialize(boolean start);
	public abstract void finish(boolean success);
	
	public void setInitialized(boolean initialized)
	{
		this.initialized = initialized;
	}
	protected boolean isInitialized()
	{
		return initialized;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public boolean isValid()
	{
		return valid;
	}
	protected int getThreadID()
	{
		return threadID;
	}
	protected String getServiceID()
	{
		return serviceID;
	}
	protected void turnOnStandardError()
	{
		stderr = true;
	}
	protected void turnOffStandardError()
	{
		stderr = false;
	}
	protected boolean getStandardError()
	{
		return stderr;
	}
	protected abstract String buildLogPath();
	protected PrintWriter openLog()
	{
		if(log==null)
		{
			FileOutputStream os = null;
			String path = buildLogPath();
			if(path!=null)
			{
				try
				{
					os = new FileOutputStream(new File(path));
				}
				catch(FileNotFoundException e)
				{
					System.err.println("Could not open up log at \""+path+"\".");
					e.printStackTrace();
				}
			}
			if(os!=null)
			{
				log = new PrintWriter(os);
			}
		}
		return log;
	}
	protected PrintWriter getLog()
	{
		return log;
	}
	protected void closeLog()
	{
		if(log!=null)
		{
			log.close();
			log = null;
		}
	}
	
	public void start()
	{
		if(isStopped())
			setStatus(STATUS_RUNNING);
	}
	public void resume()
	{
		if(isPaused())
			setStatus(STATUS_RUNNING);
	}
	public void stop()
	{
		if(isRunning()||isPaused())
			setStatus(STATUS_STOPPED);
	}
	public void pause()
	{
		if(isRunning())
			setStatus(STATUS_PAUSED);
	}
	public void done()
	{
		if(isRunning())
			setStatus(STATUS_DONE);
	}
	
	public boolean isDone()
	{
		return status==STATUS_DONE;
	}
	public boolean isPaused()
	{
		return status==STATUS_PAUSED;
	}
	public boolean isStopped()
	{
		return status==STATUS_STOPPED;
	}
	public boolean isRunning()
	{
		return status==STATUS_RUNNING;
	}
	
	public long getDelay()
	{
		return delay;
	}
	public long getPeriod()
	{
		return period;
	}

	public boolean unschedule()
	{
		if(scheduler!=null)
			return scheduler.unschedule(this.getThreadID());
		else
			return false;
	}
}
