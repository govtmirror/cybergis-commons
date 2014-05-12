package gov.hiu.cybergis.commons.registry;

public class Registry2<A,B extends Item_Registry<A>> extends Registry<String,A,B>
{
	public Registry2(String id, String label, String type)
	{
		super(id,label,type);
	}
}
