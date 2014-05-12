package gov.hiu.cybergis.commons.database;

import java.sql.Connection;

public abstract class DatabaseWrapper
{
	public DatabaseWrapper()
	{
		
	}
	protected abstract String encrypt(Object object, Connection db,String password, String salt);
}
