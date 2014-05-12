package gov.hiu.cybergis.commons.effects;

import gov.hiu.cybergis.commons.store.Store;

public class Effects extends Store<String,Effect>
{
	public Effects(String id)
	{
		super(id,"Effects","effects");
	}
}
