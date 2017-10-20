package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.geoPoint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.geoPoint.FixedAssetGeoPoint;

public class FixedAssetGeoPointMapper  {


	public static Map<String, Object> map(FixedAssetGeoPoint fixedassetgeopoint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetgeopoint.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetgeopoint.getFixedAssetId());
}

		if(fixedassetgeopoint.getGeoPointId() != null ){
			returnVal.put("geoPointId",fixedassetgeopoint.getGeoPointId());
}

		if(fixedassetgeopoint.getFromDate() != null ){
			returnVal.put("fromDate",fixedassetgeopoint.getFromDate());
}

		if(fixedassetgeopoint.getThruDate() != null ){
			returnVal.put("thruDate",fixedassetgeopoint.getThruDate());
}

		return returnVal;
}


	public static FixedAssetGeoPoint map(Map<String, Object> fields) {

		FixedAssetGeoPoint returnVal = new FixedAssetGeoPoint();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FixedAssetGeoPoint mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetGeoPoint returnVal = new FixedAssetGeoPoint();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static FixedAssetGeoPoint map(GenericValue val) {

FixedAssetGeoPoint returnVal = new FixedAssetGeoPoint();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FixedAssetGeoPoint map(HttpServletRequest request) throws Exception {

		FixedAssetGeoPoint returnVal = new FixedAssetGeoPoint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("geoPointId"))  {
returnVal.setGeoPointId(request.getParameter("geoPointId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
