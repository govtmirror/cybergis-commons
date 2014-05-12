package gov.hiu.cybergis.commons.registry;

public interface Item_Registry<A>
{
	public boolean isID(A id);
	public boolean isID(A ids[]);
	public A getID();
}
