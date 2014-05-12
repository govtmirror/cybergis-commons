package gov.hiu.cybergis.commons.servlets;

import gov.hiu.cybergis.commons.cors.CORS;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Class_Servlet_List extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final boolean DEBUG = true;
	
	private static String CONTENT_TYPE = "application/json";
	private static String CHARACTER_ENCODING = "UTF-8";
	private static String ACCESS_CONTROL_ALLOW_ORIGIN = "http://example.com";	
      
    public Class_Servlet_List()
    {
	   super();
    }
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(DEBUG) System.err.println(this.getClass().getName()+".doOptions()");
		CORS.preflight(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(DEBUG) System.err.println(this.getClass().getName()+".doGet()");
		response.addHeader("Access-Control-Allow-Origin",ACCESS_CONTROL_ALLOW_ORIGIN);
		response.setCharacterEncoding(CHARACTER_ENCODING);
		response.setContentType(CONTENT_TYPE);
		
		PrintWriter out = response.getWriter();
		String sResponse = buildResponse(request);
		out.println(sResponse);
		out.close();
	}
	protected abstract String buildResponse(HttpServletRequest request);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(DEBUG) System.err.println(this.getClass().getName()+".doPost()");
	}
}
