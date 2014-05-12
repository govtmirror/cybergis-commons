package gov.hiu.cybergis.commons.time;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

public class Time
{
	public static TimeZone getTimeZone(HttpServletRequest request)
	{
		TimeZone clientTimeZone = getCalendar(request).getTimeZone();
		return clientTimeZone;
	}
	public static Calendar getCalendar(HttpServletRequest request)
	{
		Locale clientLocale = request.getLocale();
		Calendar calendar = Calendar.getInstance(clientLocale);
		return calendar;
	}
	public static Locale getLocale(HttpServletRequest request)
	{
		Locale clientLocale = request.getLocale();
		return clientLocale;
	}
}
