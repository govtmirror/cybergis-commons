package gov.hiu.cybergis.commons.interfaces;

import java.util.Locale;

public interface PageInterface
{
	public String getPageName();
	public String getPageTitle();
	public String getMainID();
	public String getFontsAsHTML(Locale locale, int user);
}
