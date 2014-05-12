package gov.hiu.cybergis.commons.utility;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.geotools.data.DataSourceException;

import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Utility
{
	public static double[] getExtentAsWebMercator_Array(String filename) throws NoSuchAuthorityCodeException, DataSourceException, TransformException, FactoryException
	{
		return Utility.getExtent_Array(filename,"EPSG:3857");
	}
	public static String getExtentAsWebMercator_String(String filename) throws NoSuchAuthorityCodeException, DataSourceException, TransformException, FactoryException
	{
		return Utility.getExtent_String(filename,"EPSG:3857");
	}
	public static String getExtentAsWGS84_String(String filename) throws NoSuchAuthorityCodeException, DataSourceException, TransformException, FactoryException
	{
		return Utility.getExtent_String(filename,"EPSG:4326");
	}
	public static double[] getExtent_Array(String filename,String srs) throws NoSuchAuthorityCodeException, TransformException, FactoryException, DataSourceException
	{
		File file = new File(filename);
		GeoTiffReader reader = new GeoTiffReader(file, new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE));
		ReferencedEnvelope envelope = new ReferencedEnvelope(reader.getOriginalEnvelope());
		CoordinateReferenceSystem targetCRS = CRS.decode(srs);
		if(!envelope.getCoordinateReferenceSystem().getName().equals(targetCRS.getName()))
			envelope = envelope.transform(targetCRS,true);
		return new double[]{envelope.getMinX(),envelope.getMinY(),envelope.getMaxX(),envelope.getMaxY()};
	}
	public static String getExtent_String(String filename,String srs) throws NoSuchAuthorityCodeException, TransformException, FactoryException, DataSourceException
	{
		File file = new File(filename);
		GeoTiffReader reader = new GeoTiffReader(file, new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE));
		ReferencedEnvelope envelope = new ReferencedEnvelope(reader.getOriginalEnvelope());
		CoordinateReferenceSystem targetCRS = CRS.decode(srs);
		if(!envelope.getCoordinateReferenceSystem().getName().equals(targetCRS.getName()))
			envelope = envelope.transform(targetCRS,true);
		return envelope.getMinX()+","+envelope.getMinY()+","+envelope.getMaxX()+","+envelope.getMaxY();
	}
	public static boolean contains(String sArray[], String value)
	{
		return contains(sArray, value, true);
	}
	public static boolean contains(String sArray[], String value,boolean ignoreCase)
	{
		boolean contains = false;
		if(ignoreCase)
		{
			for(String str: sArray)
			{
				if(value.equalsIgnoreCase(str))
				{
					contains = true;
					break;
				}
			}	
		}
		else
		{
			for(String str: sArray)
			{
				if(value.equals(str))
				{
					contains = true;
					break;
				}
			}	
		}
		return contains;
	}
	public static boolean contains(String a[],String b[])
	{
		boolean contains = false;
		if(a==null||b==null)
			contains = false;
		else
		{
			for(int i = 0; i < a.length; i++)
			{
				String d = a[i];
				if(contains(b,d))
				{
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	public static boolean endsWith(String sArray[], String value)
	{
		return endsWith(sArray, value, true);
	}
	public static boolean endsWith(String sArray[], String value,boolean ignoreCase)
	{
		boolean contains = false;
		if(ignoreCase)
		{
			for(String str: sArray)
			{
				if(value.toLowerCase().endsWith(str.toLowerCase()))
				{
					contains = true;
					break;
				}
			}	
		}
		else
		{
			for(String str: sArray)
			{
				if(value.endsWith(str))
				{
					contains = true;
					break;
				}
			}	
		}
		return contains;
	}
	public static boolean contains(int iArray[], int value)
	{
		boolean contains = false;
		for(int i: iArray)
		{
			if(value==i)
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	public static boolean contains(Integer iArray[], Integer value)
	{
		boolean contains = false;
		for(Integer i: iArray)
		{
			if(i.intValue()==value.intValue())
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	public static boolean contains(long iArray[], long value)
	{
		boolean contains = false;
		for(long n: iArray)
		{
			if(value==n)
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	public static boolean contains(Long iArray[], Long value)
	{
		boolean contains = false;
		for(Long n: iArray)
		{
			if(n.longValue()==value.longValue())
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	public static boolean contains(PreparedStatement st[], PreparedStatement value)
	{
		boolean contains = false;
		for(PreparedStatement i: st)
		{
			if(value==i)
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	public static boolean containsNull(PreparedStatement st[])
	{
		boolean contains = false;
		for(PreparedStatement i: st)
		{
			if(i==null)
			{
				contains = true;
				break;
			}
		}		
		return contains;
	}
	
	public static void append(StringBuilder sb, LinkedList<String> lines, String delimiter, boolean prefix, boolean suffix)
	{
		if(lines!=null)
		{
			if(lines.size()>0)
			{
				if(prefix)
					sb.append(delimiter);
				
				ListIterator<String> iter = lines.listIterator();
				if(iter.hasNext())
				{
					sb.append(iter.next());
					while(iter.hasNext())
					{
						sb.append(delimiter);
						sb.append(iter.next());
					}
				}
				
				if(suffix)
					sb.append(delimiter);
			}
		}
	}

	public static String join(String a[],String delimiter)
	{
		return join(a,delimiter,false,false);
	}
	public static String join(String a[],String delimiter,boolean prefix,boolean suffix)
	{
		String str = "";
		if(a!=null)
		{
			if(a.length>0)
			{
				if(prefix)
					str += delimiter;
				str += a[0];
				for(int i = 1; i < a.length; i++)
					str += delimiter+a[i];
				if(suffix)
					str += delimiter;
			}
		}
		return str;
	}
	public static String join(Integer a[],String delimiter)
	{
		return join(a,delimiter,false,false);
	}
	public static String join(Integer a[],String delimiter,boolean prefix,boolean suffix)
	{
		String str = "";
		if(a.length>0)
		{
			if(prefix)
				str += delimiter;
			str += a[0].intValue();
			for(int i = 1; i < a.length; i++)
			{
				str += delimiter+a[i].intValue();
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	public static String join(Boolean a[], String delimiter)
	{
		return join(a,delimiter,false,false);
	}
	public static String join(Boolean a[], String delimiter,boolean prefix,boolean suffix)
	{
		String str = "";
		if(a.length>0)
		{
			if(prefix)
				str += delimiter;
			str += a[0].booleanValue();
			for(int i = 1; i < a.length; i++)
			{
				str += delimiter+a[i].booleanValue();
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	public static String join(int a[],String delimiter)
	{
		return join(a,delimiter,false);
	}
	public static String join(int a[],String delimiter,boolean suffix)
	{
		String str = "";
		if(a.length>0)
		{
			str += a[0];
			for(int i = 1; i < a.length; i++)
			{
				str += delimiter+a[i];
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	public static String join(Double a[],String delimiter)
	{
		return join(a,delimiter,false);
	}
	public static String join(Double a[],String delimiter,boolean suffix)
	{
		String str = "";
		if(a.length>0)
		{
			str += a[0].intValue();
			for(int i = 1; i < a.length; i++)
			{
				str += delimiter+a[i].doubleValue();
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	public static String join_set_string(Set<String> a, String delimiter)
	{
		return join_set_string(a,delimiter,false);
	}
	public static String join_set_string(Set<String> a, String delimiter, boolean suffix)
	{
		String str = "";
		if(a.size()>0)
		{
			Iterator<String> i = a.iterator();
			str += i.next();
			while(i.hasNext())
			{
				str += delimiter+i.next();
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	public static String join_set_integer(Set<Integer> a, String delimiter)
	{
		return join_set_integer(a,delimiter,false);
	}
	public static String join_set_integer(Set<Integer> a, String delimiter, boolean suffix)
	{
		String str = "";
		if(a.size()>0)
		{
			Iterator<Integer> i = a.iterator();
			str += ""+i.next().intValue();
			while(i.hasNext())
			{
				str += delimiter+i.next().intValue();
			}
			if(suffix)
				str += delimiter;
		}
		return str;
	}
	
	public static String[] wrap(String sArray[],String w)
	{
		return wrap(sArray, w, true);
	}
	public static String[] wrap(String sArray[],String w, boolean force)
	{
		String newArray[] = new String[sArray.length];
		for(int i = 0; i < sArray.length; i++)
		{
			newArray[i] = wrap(sArray[i],w,force);
		}
		return newArray;
	}

	public static String wrap(String str, String w)
	{
		return wrap(str,w,true);
	}
	public static String wrap(String str, String w, boolean force)
	{
		if(str==null)
		{
			if(force)
				return w+w;
			else
				return "";
		}
		else
		{
			if(str.startsWith(w)&&str.endsWith(w))
				return str;
			else
				return w+str+w;
		}
	}
	
	public static boolean and(boolean a[])
	{
		boolean c = true;
		for(boolean b: a)
		{
			c = c && b;
		}
		return c;
	}
	public static String and(String a[])
	{
		return Utility.join(a," and ");
	}
	/**
	 * Only return values from a that are in b;
	 * @param a - original array
	 * @param b - test array
	 * @return
	 */
	public static String[] grep(String a[],String b[])
	{
		if(a==null||b==null)
			return null;
		else
		{
			LinkedList<String> c = new LinkedList<String>();
			for(int i = 0; i < a.length; i++)
			{
				String d = a[i];
				if(contains(b,d))
					c.add(d);
			}		
			return c.toArray(new String[]{});
		}
	}
	public static String[] grep(String a[], boolean b[])
	{
		if(a==null||b==null)
			return null;
		else
		{
			LinkedList<String> c = new LinkedList<String>();
			for(int i = 0; i < a.length; i++)
			{
				if(b[i])
					c.add(a[i]);
			}		
			return c.toArray(new String[]{});
		}
	}
	public static int[] grep(int a[],int b[])
	{
		if(a==null||b==null)
			return null;
		else
		{
			LinkedList<Integer> c = new LinkedList<Integer>();
			for(int i = 0; i < a.length; i++)
			{
				int d = a[i];
				if(contains(b,d))
					c.add(new Integer(d));
			}		
			return unbox(c.toArray(new Integer[]{}));
		}
	}

	public static boolean[] test(String a[], String b[])
	{
		if(a==null||b==null)
			return null;
		else
		{
			boolean c[] = new boolean[a.length];
			for(int i = 0; i < a.length; i++)
			{
				c[i] = contains(b,a[i]);
			}		
			return c;
		}
	}
	
	
	public static int[] build_intarray(Array a) throws SQLException
	{
		if(a==null)
			return null;
		else
			return unbox((Integer[])a.getArray());
	}
	public static String[] build_stringarray(Array a) throws SQLException
	{
		if(a==null)
			return null;
		else
			return (String[])a.getArray();
	}
	public static int[] unbox(Integer a[])
	{
		if(a==null)
			return null;
		else
		{
			int b[] = new int[a.length];
			for(int i = 0; i < a.length; i++)
			{
				b[i] = a[i].intValue();
			}
			return b;
		}
	}
	public static Integer[] box(int a[])
	{
		if(a==null)
			return null;
		else
		{
			Integer b[] = new Integer[a.length];
			for(int i = 0; i < a.length; i++)
			{
				b[i] = new Integer(a[i]);
			}
			return b;
		}
	}
	public static boolean[] unbox(Boolean a[])
	{
		if(a==null)
			return null;
		else
		{
			boolean b[] = new boolean[a.length];
			for(int i = 0; i < a.length; i++)
			{
				b[i] = a[i].booleanValue();
			}
			return b;
		}
	}
	public static Boolean[] box(boolean a[])
	{
		if(a==null)
			return null;
		else
		{
			Boolean b[] = new Boolean[a.length];
			for(int i = 0; i < a.length; i++)
			{
				b[i] = new Boolean(a[i]);
			}
			return b;
		}
	}
	
	public static String[] join(String a,String b[])
	{
		String c[] = new String[1+b.length];
		c[0] = a;
		for(int i = 0; i < b.length; i++){c[1+i] = b[i];}
		return c;
	}
	public static String[] join(String a[],String b[])
	{
		String c[] = new String[a.length+b.length];
		for(int i = 0; i < a.length; i++){c[i] = a[i];}
		for(int i = 0; i < b.length; i++){c[i+a.length] = b[i];}
		return c;
	}
	public static String[] join(String a[],String b[],String c[])
	{
		String d[] = new String[a.length+b.length+c.length];
		for(int i = 0; i < a.length; i++){d[i] = a[i];}
		for(int i = 0; i < b.length; i++){d[i+a.length] = b[i];}
		for(int i = 0; i < c.length; i++){d[i+a.length+b.length] = c[i];}
		return d;
	}
	public static String[] join(String a[],String b[],String c[],String d[])
	{
		String e[] = new String[a.length+b.length+c.length+d.length];
		for(int i = 0; i < a.length; i++){e[i] = a[i];}
		for(int i = 0; i < b.length; i++){e[i+a.length] = b[i];}
		for(int i = 0; i < c.length; i++){e[i+a.length+b.length] = c[i];}
		for(int i = 0; i < d.length; i++){e[i+a.length+b.length+c.length] = d[i];}
		return e;
	}
	public static String[] generate_array_string(String str,int count)
	{
		String array[] = new String[count];
		for(int i = 0; i < count; i++)
		{
			array[i] = str;
		}
		return array;
	}
	public static Integer[] generate_array_integer(Integer integer,int count)
	{
		Integer array[] = new Integer[count];
		for(int i = 0; i < count; i++)
		{
			array[i] = integer;
		}
		return array;
	}
	public static boolean[] generate_array_boolean(boolean value,int count)
	{
		boolean array[] = new boolean[count];
		for(int i = 0; i < count; i++)
		{
			array[i] = value;
		}
		return array;
	}
	/**
	 * 
	 * @param a - first array
	 * @param b - second array
	 * @param order - does order matter
	 * @return
	 */
	public static boolean equals(int a[], int b[], boolean order)
	{
		boolean equals = false;
		if(!order)
		{
			Arrays.sort(a);
			Arrays.sort(b);
		}
		
		if(a.length==b.length)
		{
			equals = true;
			for(int i = 0; i < a.length; i++)
			{
				if(a[i]!=b[i])
				{
					equals = false;
					break;
				}
			}
		}
		else
			equals = false;
		
		return equals;
	}
	public static int[] join(int a[],int b[])
	{
		int c[] = new int[a.length+b.length];
		for(int i = 0; i < a.length; i++){c[i] = a[i];}
		for(int i = 0; i < b.length; i++){c[i+a.length] = b[i];}
		return c;
	}

	public static String[] concat(String a[], String b)
	{
		String c[] = new String[a.length];
		for(int i = 0; i < a.length; i++)
			c[i] = a[i]+b;
		return c;
	}
	public static String[] concat(String a[], String b[])
	{
		String c[] = new String[a.length];
		for(int i = 0; i < a.length; i++)
			c[i] = a[i]+b[i];
		return c;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param map
	 * @return
	 */
	public static LinkedHashMap<String,String> appendTo(String key, String value, LinkedHashMap<String,String> map)
	{
		if(map!=null)
		{
			if(map.containsKey(key))
				map.put(key,map.get(key)+value);
			else
				map.put(key,value);
		}
		return map;
	}
	public static String[] trim(String a[])
	{
		if(a!=null)
		{
			String b[] = new String[a.length];
			for(int i = 0; i < b.length; i++)
			{
				b[i] = Utility.trim(a[i]);
			}
			return b;
		}
		else
			return null;
	}
	/**
	 * Trims whitespace and controls characters from the start and end of the string
	 * @param a - the ${String} to trim
	 * @return trimmed ${String}
	 */
	public static String trim(String a)
	{
		if(a!=null)
		{
			if(a.length()>0)
			{
				a = StringUtils.trim(a);
				a = StringUtils.strip(a);
				a = StringUtils.strip(a,""+('\u00A0'));
			}
		}
		return a;
	}
	public static int getParameterAsInteger(HttpServletRequest request, String name)
	{
		return getParameterAsInteger(request, new String[]{name});
	}
	public static int getParameterAsInteger(HttpServletRequest request, String names[])
	{
		int iValue = 0;
		String sValue = getParameterAsString(request,names);
		if(sValue!=null)
		{
			if(sValue.length()>0)
			{
				try{iValue = Integer.parseInt(sValue);}catch(Exception e){iValue = 0;};
			}
		}
		return iValue;
	}
	public static String getParameterAsString(HttpServletRequest request, String name)
	{
		return getParameterAsString(request, new String[]{name});
	}
	public static String getParameterAsString(HttpServletRequest request, String names[])
	{
		String value = null;
		for(int i = 0; i < names.length; i++)
		{
			String str = request.getParameter(names[i]);
			if(str!=null)
			{
				value = str;
				break;
			}
		}
		return value;
	}
	public static String getParameterAsURL(HttpServletRequest request, String name)
	{
		return getParameterAsURL(request, new String[]{name});
	}
	public static String getParameterAsURL(HttpServletRequest request, String names[])
	{
		String value = null;
		for(int i = 0; i < names.length; i++)
		{
			String str = request.getParameter(names[i]);
			if(str!=null)
			{
				try{value = URLDecoder.decode(str, "UTF-8");}
				catch(Exception e){value = null;};
				break;
			}
		}
		return value;
	}
	
	public static String[] getParameterAsStringArray(HttpServletRequest request, String name)
	{
		return Utility.split(getParameterAsString(request,name));
	}
	public static String[] getParameterAsStringArray(HttpServletRequest request, String names[])
	{
		return Utility.split(getParameterAsString(request,names));
	}
	public static Integer[] getParameterAsIntegerArray(HttpServletRequest request, String name)
	{
		String sArray[] = Utility.split(getParameterAsString(request,name));
		if(sArray!=null)
		{
			Integer iArray[] = new Integer[sArray.length];
			for(int i = 0; i < iArray.length; i++)
			{
				String sID = sArray[i];
				int id = 0;
				try{id = Integer.parseInt(sID);}catch(NumberFormatException e){id = 0;e.printStackTrace();};
				if(id!=0)
				{
					iArray[i] = Integer.valueOf(id);
				}
			}
			return iArray;
		}
		else
			return null;
		
	}
	public static Integer[] getParameterAsIntegerArray(HttpServletRequest request, String names[])
	{
		String sArray[] = Utility.split(getParameterAsString(request,names));
		if(sArray!=null)
		{
			Integer iArray[] = new Integer[sArray.length];
			for(int i = 0; i < iArray.length; i++)
			{
				String sID = sArray[i];
				int id = 0;
				try{id = Integer.parseInt(sID);}catch(NumberFormatException e){id = 0;e.printStackTrace();};
				if(id!=0)
				{
					iArray[i] = Integer.valueOf(id);
				}
			}
			return iArray;
		}
		else
			return null;
		
	}
	


	public static String[] split(String sValue)
	{
		String aValue[] = null;
		if(sValue!=null)
		{
			if(sValue.length()>0)
			{
				aValue = sValue.split(",");
			}
		}
		return aValue;
	}
	
	public static File buildDirectory(File candidate)
	{
		if(candidate.exists())
    	{
    		if(candidate.isFile())
    		{
    			return null;
    		}
    	}
    	else
    	{
    		candidate.mkdir();
    	}
		return candidate;
	}
	public static String coalesce(String a, String b)
	{
		if(a==null)
			return b;
		else
			return a;
	}
	public static String coalesce(String a, String b, String c)
	{
		if(a!=null)
			return a;
		else if(b!=null)
			return b;
		else
			return c;
	}
	public static String coalesce(String a[])
	{
		String str = null;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i]!=null)
			{
				str = a[i];
				break;
			}
		}
		return str;
	}
	public static String clean(String str)
	{
		if(str==null)
			return "";
		else
			return str.replace("\\","\\\\").replace("\"","\\\"").replace("\n"," ");//Order matters here.  You don't won't to replace backslahes once you arelady put htem in for quotes.
	}
	public static String convertFileToString(String filename)
	{
		return convertFileToString(new File(filename));
	}
	public static String convertFileToString(File f)
	{
		String str = null;
		if(f.exists())
		{
			try{str = FileUtils.readFileToString(f,"UTF-8");}catch(Exception e){e.printStackTrace();str=null;}			
		}
		return str;
	}
	
 	public static HashMap<String,String> convertJSONToHashMap(String json)
	{
		HashMap<String,String> map = null;
		if(json!=null)
		{
			if(json.length()>0)
			{
				Type type = new TypeToken<Map<String,String>>(){}.getType();
				Gson gson = new Gson();
				map = gson.fromJson(json,type);
			}
		}
		return map;
	}
	public static String convertHashMapToJSON(HashMap<String,String> map)
	{
		String json = "";
		if(map!=null)
		{
			if(map.size()>0)
			{
				Gson gson = new Gson();
				return gson.toJson(map);
			}
		}
		return json;
	}

	public static String[] buildLine(Row row, boolean trim)
	{
		int length = row.getLastCellNum();
		String line[] = new String[length];
		for(int i = 0; i < length; i++)
		{
			Cell cell = row.getCell(i,Row.CREATE_NULL_AS_BLANK);
			int type = cell.getCellType();
			if(type==Cell.CELL_TYPE_NUMERIC)
				line[i] = ""+row.getCell(i,Row.CREATE_NULL_AS_BLANK).getNumericCellValue();
			else if(type==Cell.CELL_TYPE_BOOLEAN)
				line[i] = row.getCell(i,Row.CREATE_NULL_AS_BLANK).getBooleanCellValue()?"true":"false";
			else if(type==Cell.CELL_TYPE_STRING)
			{
				if(trim)
				{
					line[i] = Utility.trim(row.getCell(i,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				}
				else
					line[i] = row.getCell(i,Row.CREATE_NULL_AS_BLANK).getStringCellValue();
			}
			else if(type==Cell.CELL_TYPE_BLANK||type==Cell.CELL_TYPE_ERROR||type==Cell.CELL_TYPE_FORMULA)
				line[i] = "";
		}
		return line;
	}

	public static String convertToSortValue(String sValue, Locale locale)
	{
		if(sValue!=null)
		{
			if(sValue.length()>0)
			{
				sValue = StringUtils.stripAccents(sValue);
				sValue = sValue.replaceAll("[\u0049\u0069\u0130\u0131]","I");
				sValue = sValue.toLowerCase(locale);
			}
		}
				
		return sValue;
	}

	public static File[] listFiles(String directory, String extensions[])
	{
		File d = new File(directory);
		Collection<File> files = FileUtils.listFiles(d, extensions, true);
		return files.toArray(new File[]{});
	}
}
