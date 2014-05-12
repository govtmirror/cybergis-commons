package gov.hiu.cybergis.commons.fonts;

import java.util.Locale;
import gov.hiu.cybergis.commons.registry.Registry2;

public class FontRegistry extends Registry2<String,FontFamily>
{
	public FontRegistry(String id)
	{
		super(id,"Fonts","Fonts");
	}
	public String getHTML(Locale locale)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"dialogs\">");
		for(FontFamily font: getList())
		{
			sb.append(font.getHTML());
		}
		sb.append("</div>");
		return sb.toString();
	}
	protected String tabs(int number)
	{
		String str = "";
		for(int i = 0; i < number; i++)
		{
			str += "\t";
		}
		return str;
	}
}
