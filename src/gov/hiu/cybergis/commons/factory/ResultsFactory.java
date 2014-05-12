package gov.hiu.cybergis.commons.factory;

import java.util.HashMap;

public class ResultsFactory
{
	public static final int MESSAGE_FAILED_DATABASE_CONNECTION = 1;
	public static final int MESSAGE_FAILED_ID_MISSING = 2;
	public static final int MESSAGE_FAILED_ID_INVALID = 3;
	public static final int MESSAGE_FAILED_NAME_MISSING = 4;
	public static final int MESSAGE_FAILED_STATEMENT_CREATE = 5;
	public static final int MESSAGE_FAILED_STATEMENT_PREP = 6;
	public static final int MESSAGE_FAILED_SQL_EXEC_QUERY = 7;
	
	public static final int MESSAGE_SUCCESS_PARSE_BOOLEAN = 8;
	public static final int MESSAGE_SUCCESS_PARSE_INT = 9;
	public static final int MESSAGE_SUCCESS_PARSE_INT_ARRAY = 10;
	public static final int MESSAGE_SUCCESS_PARSE_DOUBLE = 11;
	public static final int MESSAGE_SUCCESS_PARSE_DOUBLE_ARRAY = 12;
	public static final int MESSAGE_SUCCESS_PARSE_STRING = 13;
	public static final int MESSAGE_SUCCESS_PARSE_STRING_ARRAY = 14;
	public static final int MESSAGE_SUCCESS_PARSE_DATE = 15;
	public static final int MESSAGE_SUCCESS_PARSE_TIMESTAMP = 16;
	public static final int MESSAGE_SUCCESS_PARSE_TIMESTAMPZ = 32;
	public static final int MESSAGE_SUCCESS_PARSE_WKT = 17;
	public static final int MESSAGE_SUCCESS_PARSE_ENTITY = 33;
	
	public static final int MESSAGE_SUCCESS_SQL_UPDATE = 18;
	public static final int MESSAGE_SUCCESS_SQL_QUERY = 19;
	
	public static final int MESSAGE_FAILED_ROOT_INVALID = 20;
	
	public static final int MESSAGE_FAILED_PARSE_BOOLEAN = 21;
	public static final int MESSAGE_FAILED_PARSE_INT = 22;
	public static final int MESSAGE_FAILED_PARSE_INT_ARRAY = 23;
	public static final int MESSAGE_FAILED_PARSE_DOUBLE = 24;
	public static final int MESSAGE_FAILED_PARSE_DOUBLE_ARRAY = 25;
	public static final int MESSAGE_FAILED_PARSE_STRING = 26;
	public static final int MESSAGE_FAILED_PARSE_STRING_ARRAY = 27;
	public static final int MESSAGE_FAILED_PARSE_DATE = 28;
	public static final int MESSAGE_FAILED_PARSE_TIMESTAMP = 29;
	public static final int MESSAGE_FAILED_PARSE_TIMESTAMPZ = 30;
	public static final int MESSAGE_FAILED_PARSE_WKT = 31;
	public static final int MESSAGE_FAILED_PARSE_ENTITY = 32;
	//////////////////////////////
	public static final int MESSAGE_FAILED_UNKNOWN = 33;
	public static final int MESSAGE_SUCCESS_STATEMENT_CREATE = 34;	
	/////////////////////////
	protected HashMap<Integer,String> messages;
	
	protected static ResultsFactory factory; 
	
	public ResultsFactory()
	{
		init_messages();
	}
	protected void init_messages()
	{
		messages = new HashMap<Integer,String>();
		messages.put(MESSAGE_FAILED_DATABASE_CONNECTION,"Failed.  Could not connect to database.");
		messages.put(MESSAGE_FAILED_STATEMENT_CREATE,"Failed.  Could not create statment.");
		messages.put(MESSAGE_FAILED_STATEMENT_PREP,"Failed.  Could not prepare statment.");
		messages.put(MESSAGE_FAILED_SQL_EXEC_QUERY,"Failed.  Could not execute query.");
		
		messages.put(MESSAGE_FAILED_ID_MISSING,"Failed.  No ID specified.");
		messages.put(MESSAGE_FAILED_ID_INVALID,"Failed.  ID must be greater than 0.");
		messages.put(MESSAGE_FAILED_NAME_MISSING,"Failed.  No name specified.");
		
		messages.put(MESSAGE_SUCCESS_PARSE_BOOLEAN,"Success.  Parsed boolean.");
		messages.put(MESSAGE_SUCCESS_PARSE_INT,"Success.  Parsed integer.");
		messages.put(MESSAGE_SUCCESS_PARSE_INT_ARRAY,"Success.  Parsed integer array.");
		messages.put(MESSAGE_SUCCESS_PARSE_DOUBLE,"Success.  Parsed double.");
		messages.put(MESSAGE_SUCCESS_PARSE_DOUBLE_ARRAY,"Success.  Parsed double array.");
		messages.put(MESSAGE_SUCCESS_PARSE_STRING,"Success.  Parsed string.");
		messages.put(MESSAGE_SUCCESS_PARSE_STRING_ARRAY,"Success.  Parsed string array.");
		messages.put(MESSAGE_SUCCESS_PARSE_DATE,"Success.  Parsed date.");
		messages.put(MESSAGE_SUCCESS_PARSE_TIMESTAMP,"Success.  Parsed timestamp.");
		messages.put(MESSAGE_SUCCESS_PARSE_TIMESTAMPZ,"Success.  Parsed timestampz.");
		messages.put(MESSAGE_SUCCESS_PARSE_WKT,"Success.  Parsed well-known text.");
		messages.put(MESSAGE_SUCCESS_PARSE_ENTITY,"Success.  Parsed entity.");		
		
		messages.put(MESSAGE_SUCCESS_SQL_UPDATE,"Success.  Executed SQL update statement.");
		messages.put(MESSAGE_SUCCESS_SQL_QUERY,"Success.  Executed SQL query statement.");
		
		messages.put(MESSAGE_FAILED_ROOT_INVALID,"Failed.  Root invalid.");
		
		messages.put(MESSAGE_FAILED_PARSE_BOOLEAN,"FAILED.  Parsed boolean.");
		messages.put(MESSAGE_FAILED_PARSE_INT,"FAILED.  Parsed integer.");
		messages.put(MESSAGE_FAILED_PARSE_INT_ARRAY,"FAILED.  Parsed integer array.");
		messages.put(MESSAGE_FAILED_PARSE_DOUBLE,"FAILED.  Parsed double.");
		messages.put(MESSAGE_FAILED_PARSE_DOUBLE_ARRAY,"FAILED.  Parsed double array.");
		messages.put(MESSAGE_FAILED_PARSE_STRING,"FAILED.  Parsed string.");
		messages.put(MESSAGE_FAILED_PARSE_STRING_ARRAY,"FAILED.  Parsed string array.");
		messages.put(MESSAGE_FAILED_PARSE_DATE,"FAILED.  Parsed date.");
		messages.put(MESSAGE_FAILED_PARSE_TIMESTAMP,"FAILED.  Parsed timestamp.");
		messages.put(MESSAGE_FAILED_PARSE_TIMESTAMPZ,"FAILED.  Parsed timestampz.");
		messages.put(MESSAGE_FAILED_PARSE_WKT,"FAILED.  Parsed well-known text.");
		messages.put(MESSAGE_FAILED_PARSE_ENTITY,"FAILED.  Could not parse entity.");
		
		messages.put(MESSAGE_FAILED_UNKNOWN,"Unknown error.  This should never happen.  Check the logs pronto.");
		messages.put(MESSAGE_SUCCESS_STATEMENT_CREATE,"Success.  Created statment.");
		
	}
	protected String getMessage(int code)
	{
		return messages.get(code);
	}	
	
	public static String message(int code)
	{
		if(factory==null)
		{
			factory = new ResultsFactory();
		}
		return factory.getMessage(code);
	}
}
