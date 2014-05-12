package gov.hiu.cybergis.commons.builders;

import gov.hiu.cybergis.commons.utility.Utility;

public class SQLBuilder
{
	public static String contains(String aliasA, String columnA, String columnsB[])
	{
		return " WHERE ARRAY["+aliasA+"."+columnA+"] <@ ARRAY["+Utility.join(Utility.wrap(columnsB,"'"),",")+"]";
	}
	public static String contains(String aliasA, String columnA, int columnsB[])
	{
		if(aliasA==null)
			return " WHERE ARRAY["+columnA+"] <@ ARRAY["+Utility.join(columnsB,",")+"]";
		else
			return " WHERE ARRAY["+aliasA+"."+columnA+"] <@ ARRAY["+Utility.join(columnsB,",")+"]";
	}
	public static String equals(String aliasA,String aliasB,String column)
	{
		return aliasA+"."+column+"="+aliasB+"."+column;
	}
	public static String equals(String aliasA,String columnA,String aliasB,String columnB)
	{
		return aliasA+"."+columnA+"="+aliasB+"."+columnB;
	}
	public static String intersects(String aliasA,String aliasB,String column)
	{
		return "st_intersects("+aliasA+"."+column+","+aliasB+"."+column+")";
	}
	public static String intersects(String aliasA,String columnA,String aliasB,String columnB)
	{
		return "st_intersects("+aliasA+"."+columnA+","+aliasB+"."+columnB+")";
	}
	public static String equals(String aliasA,String aliasB,String columns[])
	{
		String sSQL = "";
		if(columns.length > 0)
		{
			sSQL += equals(aliasA,aliasB,columns[0]);
			for(int i = 1; i < columns.length; i++)
			{
				sSQL += " and "+equals(aliasA,aliasB,columns[i]);
			}
		}
		else
		{
			sSQL = "true";
		}
		return sSQL;
	}
	public static String intersects(String aliasA,String aliasB,String columns[])
	{
		if(columns.length > 0)
		{
			String aSQL[] = new String[columns.length];
			for(int i = 0; i < columns.length; i++)
			{
				aSQL[i] = "st_intersects("+aliasA+"."+columns[i]+","+aliasB+"."+columns[i]+")";
			}
			return Utility.and(aSQL);
		}
		else
			return null;		
	}
	public static String intersects(String aliasA,String columnsA[],String aliasB,String columnsB[])
	{
		if(columnsA.length > 0 && columnsB.length > 0 && columnsA.length == columnsB.length)
		{
			String aSQL[] = new String[columnsA.length];
			for(int i = 0; i < columnsA.length; i++)
			{
				aSQL[i] = "st_intersects("+aliasA+"."+columnsA[i]+","+aliasB+"."+columnsB[i]+")";
			}
			return Utility.and(aSQL);
		}
		else
			return null;
	}
	public static String equals(String aliasA,String columnsA[],String aliasB,String columnsB[])
	{
		String sSQL = "";
		if(columnsA.length > 0 && columnsB.length > 0 && columnsA.length == columnsB.length)
		{
			sSQL += equals(aliasA,columnsA[0],aliasB,columnsB[0]);
			for(int i = 1; i < columnsA.length; i++)
			{
				sSQL += " and "+equals(aliasA,columnsA[i],aliasB,columnsB[i]);
			}
		}
		else
		{
			sSQL = "true";
		}
		return sSQL;
	}
	public static String clear(String schema, String table)
	{
		return "DELETE FROM "+schema+"."+table;
	}
	public static String[] clear(String schema, String tables[])
	{
		String aSQL[] = new String[tables.length];
		for(int i = 0; i < tables.length; i++)
			aSQL[i] = "DELETE FROM "+schema+"."+tables[i]+";";		
		return aSQL;
	}
	public static String updateCentroid(String schema_target, String table_target, String alias_target, String field_target_id, String field_target_geography, String schema_polygons, String table_polygons, String alias_polygons, String field_polygons_id, String field_polygons_geometry)
	{
		return "UPDATE "+schema_target+"."+table_target+" as "+alias_target+"3 SET "+field_target_geography+"="+alias_target+"2.centroid FROM( SELECT "+alias_target+".id as id, st_geogfromtext(st_astext(st_centroid(st_transform(st_envelope(st_collect("+alias_polygons+"."+field_polygons_geometry+")),4326)))) as centroid FROM "+schema_target+"."+table_target+" AS "+alias_target+" LEFT JOIN "+schema_polygons+"."+table_polygons+" as "+alias_polygons+" ON ARRAY["+alias_polygons+"."+field_polygons_id+"] <@ "+alias_target+"."+field_target_id+" GROUP BY "+alias_target+".id) as "+alias_target+"2 WHERE "+alias_target+"3.ID = "+alias_target+"2.ID";
	}
	public static String updateGeometryFromGeography(String schema, String table, String geog, String geom, int srid)
	{
		return "UPDATE "+schema+"."+table+" SET "+geom+"=st_transform(st_geomfromtext(st_astext("+geog+"),4326),"+srid+");";
	}
	public static String updateGeographyFromGeometry(String schema, String table, String geog, String geom)
	{
		return "UPDATE "+schema+"."+table+" SET "+geog+"=st_geogfromtext(st_astext(st_transform("+geog+",4326)));";
	}
	public static String merge(String source, String dest, String aColumn[], String aWhere[], String aGroup[], String aOrder[], boolean id)
	{
		return merge(source, dest, aColumn, aWhere, aGroup, aOrder, id, false, false, 0);
	}
	public static String merge(String source, String dest, String aColumn[], String sWhere, String aGroup[], String aOrder[], boolean id, boolean geom)
	{
		return merge(source, dest, aColumn, new String[]{sWhere}, aGroup, aOrder, id, geom, false, 0);
	}
	public static String merge(String source, String dest, String aColumn[], String sWhere, String aGroup[], String sOrder, boolean id, boolean geom)
	{
		return merge(source, dest, aColumn, new String[]{sWhere}, aGroup, new String[]{sOrder}, id, geom, false, 0);
	}
	public static String merge(String source, String dest, String aColumn[], String aWhere[], String aGroup[], String aOrder[], boolean id, boolean geom)
	{
		return merge(source, dest, aColumn, aWhere, aGroup, aOrder, id, geom, false, 0);
	}
	public static String merge(String source, String dest, String aColumn[], String sWhere, String aGroup[], String sOrder, boolean id, boolean geom, boolean simplify, double tolerance)
	{
		return merge(source, dest, aColumn, new String[]{sWhere}, aGroup, new String[]{sOrder}, id, geom, simplify, tolerance);
	}
	public static String merge(String source, String dest, String aColumn[], String aWhere[], String aGroup[], String aOrder[], boolean id, boolean geom, boolean simplify, double tolerance)
	{
		String sSQL = "INSERT INTO "+dest+"(";
		if(id) sSQL += "id, ";
		sSQL += Utility.join(aColumn,", ");
		if(geom) sSQL += ", geom";
		sSQL += ") SELECT ";
		if(id)
		{
			if(aGroup==null)
				sSQL += "id, ";
			else
				sSQL += "min(id) as id, ";
		}
		sSQL += Utility.join(aColumn,", ");
		if(geom)
		{
			if(aGroup!=null)
			{
				if(simplify)
				{
					sSQL += ", st_multi(st_simplify(st_union(geom), "+tolerance+"::double precision))";
				}
				else
					sSQL += ", st_multi(st_union(geom))";				
			}
			else
			{
				if(simplify)
					sSQL += ", st_simplify(geom, "+tolerance+"::double precision)";
				else
					sSQL += ", geom";
			}
		}
		sSQL += " FROM "+source;
		if(aWhere!=null) sSQL += " WHERE "+Utility.join(aWhere," and ");
		if(aGroup!=null) sSQL += " GROUP BY "+Utility.join(aGroup,", ");
		if(aOrder!=null) sSQL += " ORDER BY "+Utility.join(aOrder,", ");
		sSQL += ";";
		
		return sSQL;
	}
	public static String updateIndicatorValue(String mainSchema, String mainTable, String mainAlias, String mainField, String trackingSchema, String trackingTable, String trackingAlias, String linkField, String trackingField)
	{
		String sSQL = "UPDATE "+mainSchema+"."+mainTable+" AS "+mainAlias+"3 SET "+mainField+"="+mainAlias+"2."+mainField+"_new FROM (SELECT "+mainAlias+".id,";
		sSQL += " (SELECT "+trackingAlias+"."+trackingField+" FROM "+trackingSchema+"."+trackingTable+" "+trackingAlias;
		sSQL +=	" WHERE "+mainAlias+".id="+trackingAlias+"."+linkField+" and "+trackingAlias+"."+trackingField+" IS NOT NULL";
		sSQL +=	" ORDER BY "+trackingAlias+".enddate DESC, "+trackingAlias+".startdate DESC, "+trackingAlias+".published DESC, "+trackingAlias+".entered DESC";
		sSQL +=	" LIMIT 1 ) as "+mainField+"_new";
		sSQL +=	" FROM "+mainSchema+"."+mainTable+" AS "+mainAlias+" ORDER BY "+mainAlias+".id ASC ) AS "+mainAlias+"2";
		sSQL += " WHERE "+mainAlias+"3.id="+mainAlias+"2.id and "+mainAlias+"2."+mainField+"_new IS NOT NULL;";
		return sSQL;
	}
	public static String updateIndicatorHistory(String mainSchema, String mainTable, String mainAlias, String historyField, String trackingSchema, String trackingTable, String trackingAlias, String linkField, String trackingField)
	{
		String sSQL = "UPDATE "+mainSchema+"."+mainTable+" AS "+mainAlias+"3 SET "+historyField+"="+mainAlias+"2."+historyField+"_new FROM (SELECT "+mainAlias+".id, ARRAY(";
		sSQL += " SELECT "+trackingAlias+"3.val";
		sSQL += " FROM (SELECT "+trackingAlias+"2.val, row_number() OVER () as r";
		sSQL += " FROM (SELECT "+trackingAlias+"1."+trackingField+" as val";
		sSQL += " FROM "+trackingSchema+"."+trackingTable+" AS "+trackingAlias+"1 WHERE "+mainAlias+".id="+trackingAlias+"1."+linkField+" and "+trackingAlias+"1."+trackingField+" IS NOT NULL";
		sSQL += " ORDER BY "+trackingAlias+"1.enddate DESC, "+trackingAlias+"1.startdate DESC, "+trackingAlias+"1.published DESC, "+trackingAlias+"1.entered DESC) AS "+trackingAlias+"2) AS "+trackingAlias+"3";
		sSQL += " GROUP BY "+trackingAlias+"3.val ORDER BY min("+trackingAlias+"3.r) ASC)";
		sSQL += " AS "+historyField+"_new";
		sSQL +=	" FROM "+mainSchema+"."+mainTable+" AS "+mainAlias+" ORDER BY "+mainAlias+".id ASC) AS "+mainAlias+"2";
		sSQL += " WHERE "+mainAlias+"3.id="+mainAlias+"2.id and "+mainAlias+"2."+historyField+"_new IS NOT NULL;";
		return sSQL;
	}
}