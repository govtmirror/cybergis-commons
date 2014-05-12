package gov.hiu.cybergis.commons.fonts;

import gov.hiu.cybergis.commons.registry.Item_Registry;
import gov.hiu.cybergis.commons.utility.Utility;

public class FontFamily
	implements Item_Registry<String>
{
	private String id;
	private int weights[];
	
	public FontFamily(String id, int weight)
	{
		this.id = id;
		this.weights = new int[]{weight};
	}
	public FontFamily(String id, int weights[])
	{
		this.id = id;
		this.weights = weights;
	}
	public int[] getWeights()
	{
		return weights;
	}
	public String getHTML()
	{
		return "<link href=\"http://fonts.googleapis.com/css?family="+getID().replace(" ","+")+":"+Utility.join(weights,",")+"\" rel=\"stylesheet\" type=\"text/css\">";
	}
	public boolean isID(String id)
	{
		return this.id.equalsIgnoreCase(id);
	}
	public boolean isID(String[] ids)
	{
		return Utility.contains(ids,id,true);
	}
	public String getID()
	{
		return id;
	}
}
