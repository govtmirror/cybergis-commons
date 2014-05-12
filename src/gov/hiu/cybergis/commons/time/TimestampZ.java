package gov.hiu.cybergis.commons.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimestampZ
{
	private Timestamp timestamp;
	private Calendar calendar;
	
	private int offset_hours;
	private int offset_minutes;
	
	public static TimestampZ valueOf(String input)
	{
		String timestamp = input.replaceAll("^(\\d{2,4}[-]\\d{1,2}[-]\\d{1,2})(\\s)(\\d{2}:\\d{2}:\\d+[.]?\\d*)(([+-])?(\\d\\d)?[:]?(\\d\\d)?)$","$1$2$3");
		String sSign = input.replaceAll("^(\\d{2,4}[-]\\d{1,2}[-]\\d{1,2})(\\s)(\\d{2}:\\d{2}:\\d+[.]?\\d*)(([+-])?(\\d\\d)?[:]?(\\d\\d)?)$","$5");
		String sHours = input.replaceAll("^(\\d{2,4}[-]\\d{1,2}[-]\\d{1,2})(\\s)(\\d{2}:\\d{2}:\\d+[.]?\\d*)(([+-])?(\\d\\d)?[:]?(\\d\\d)?)$","$6");
		String sMinutes = input.replaceAll("^(\\d{2,4}[-]\\d{1,2}[-]\\d{1,2})(\\s)(\\d{2}:\\d{2}:\\d+[.]?\\d*)(([+-])?(\\d\\d)?[:]?(\\d\\d)?)$","$7");
		
		int iSign = 1; if(sSign!=null) iSign = sSign.equals("-")?-1:1;
		int iHours = 0; if(sHours!=null) iHours = sHours.length()==0?0:Integer.parseInt(sHours);
		int iMinutes = 0; if(sMinutes!=null) iMinutes = sMinutes.length()==0?0:Integer.parseInt(sMinutes);
		
		return new TimestampZ(Timestamp.valueOf(timestamp),iSign*iHours,iMinutes);
	}
	public static TimestampZ valueOf(Timestamp timestamp)
	{
		return new TimestampZ(timestamp);
	}
	public TimestampZ(Timestamp timestamp, int offset_hours)
	{
		this.timestamp = timestamp;
		this.offset_hours = offset_hours;
		this.offset_minutes = 0;
		this.calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		this.calendar.add(Calendar.HOUR,offset_hours);
	}
	public TimestampZ(Timestamp timestamp, int offset_hours, int offset_minutes)
	{
		this.timestamp = timestamp;
		this.offset_hours = offset_hours;
		this.offset_minutes = offset_minutes;
		
		this.calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.add(Calendar.HOUR,offset_hours);
		calendar.add(Calendar.MINUTE,offset_minutes);
	}
	public TimestampZ(Timestamp timestamp, Calendar calendar)
	{
		this.timestamp = timestamp;
		this.calendar = calendar;
	}
	
	public TimestampZ(Timestamp timestamp)
	{
		this.timestamp = timestamp;
		this.calendar = null;
		this.offset_hours = 0;
		this.offset_minutes = 0;
	}
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	public Calendar getCalendar()
	{
		return calendar;
	}
	public String toString()
	{
		String str = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSSZ");
		if(timestamp==null)
		{
			return "";
		}
		else
		{
			if(calendar==null)
			{
				str = format.format(timestamp);
			}
			else
			{
				if(offset_hours>=0)
					str = format.format(timestamp)+"+"+offset_hours+":"+offset_minutes;
				else if(offset_minutes>=0)
					str = format.format(timestamp)+"-"+offset_hours+":"+offset_minutes;
			}
		}
		return str;
	}
}
