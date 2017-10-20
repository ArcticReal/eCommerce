package com.skytala.eCommerce.domain.product.relations.facility.mapper.locationGeoPoint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.model.locationGeoPoint.FacilityLocationGeoPoint;

public class FacilityLocationGeoPointMapper  {


	public static Map<String, Object> map(FacilityLocationGeoPoint facilitylocationgeopoint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitylocationgeopoint.getFacilityId() != null ){
			returnVal.put("facilityId",facilitylocationgeopoint.getFacilityId());
}

		if(facilitylocationgeopoint.getLocationSeqId() != null ){
			returnVal.put("locationSeqId",facilitylocationgeopoint.getLocationSeqId());
}

		if(facilitylocationgeopoint.getGeoPointId() != null ){
			returnVal.put("geoPointId",facilitylocationgeopoint.getGeoPointId());
}

		if(facilitylocationgeopoint.getFromDate() != null ){
			returnVal.put("fromDate",facilitylocationgeopoint.getFromDate());
}

		if(facilitylocationgeopoint.getThruDate() != null ){
			returnVal.put("thruDate",facilitylocationgeopoint.getThruDate());
}

		return returnVal;
}


	public static FacilityLocationGeoPoint map(Map<String, Object> fields) {

		FacilityLocationGeoPoint returnVal = new FacilityLocationGeoPoint();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
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
	public static FacilityLocationGeoPoint mapstrstr(Map<String, String> fields) throws Exception {

		FacilityLocationGeoPoint returnVal = new FacilityLocationGeoPoint();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
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
	public static FacilityLocationGeoPoint map(GenericValue val) {

FacilityLocationGeoPoint returnVal = new FacilityLocationGeoPoint();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setLocationSeqId(val.getString("locationSeqId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FacilityLocationGeoPoint map(HttpServletRequest request) throws Exception {

		FacilityLocationGeoPoint returnVal = new FacilityLocationGeoPoint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("locationSeqId"))  {
returnVal.setLocationSeqId(request.getParameter("locationSeqId"));
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
