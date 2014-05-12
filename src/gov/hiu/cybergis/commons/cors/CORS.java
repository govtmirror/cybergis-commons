package gov.hiu.cybergis.commons.cors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORS
{
	public static final String WHITELIST = "http://example.com";
	public static final String METHODS = "POST, GET, OPTIONS";
	public static final String MAX_AGE = "60";
	public static final String CONTENT_TYPE = "application/json";
	public static void preflight(HttpServletRequest request, HttpServletResponse response)
	{
		preflight(request,response,false);
	}
	/**
	 * Used in protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 * @param request
	 * @param response
	 * @param withCredentials
	 */
	public static void preflight(HttpServletRequest request, HttpServletResponse response, boolean withCredentials)
	{
		response.addHeader("Access-Control-Allow-Origin",WHITELIST);
		response.addHeader("Access-Control-Allow-Methods",METHODS);
		response.addHeader("Access-Control-Max-Age",MAX_AGE);
		response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept");
		if(withCredentials)
		{
			response.addHeader("Access-Control-Allow-Credentials","true");
		}
		response.setContentType(CONTENT_TYPE);
	}
	public static void get(HttpServletRequest request, HttpServletResponse response, String contentType, boolean withCredentials)
	{
		response.addHeader("cybergis","cybergis");
		if(withCredentials)
		{
			response.addHeader("Access-Control-Allow-Origin",WHITELIST);
			response.addHeader("Access-Control-Allow-Methods",METHODS);
			response.addHeader("Access-Control-Max-Age",MAX_AGE);
			response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept");
			response.addHeader("Access-Control-Allow-Credentials","true");
		}
		else
		{
			//Preflighted
			response.addHeader("Access-Control-Allow-Origin",WHITELIST);
		}
		response.setContentType(contentType);
	}
}
