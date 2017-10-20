package com.skytala.eCommerce.domain.party.relations.party.mapper.geoPoint;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;

public class PartyGeoPointMapper  {


	public static Map<String, Object> map(PartyGeoPoint partygeopoint) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partygeopoint.getPartyId() != null ){
			returnVal.put("partyId",partygeopoint.getPartyId());
}

		if(partygeopoint.getGeoPointId() != null ){
			returnVal.put("geoPointId",partygeopoint.getGeoPointId());
}

		if(partygeopoint.getFromDate() != null ){
			returnVal.put("fromDate",partygeopoint.getFromDate());
}

		if(partygeopoint.getThruDate() != null ){
			returnVal.put("thruDate",partygeopoint.getThruDate());
}

		return returnVal;
}


	public static PartyGeoPoint map(Map<String, Object> fields) {

		PartyGeoPoint returnVal = new PartyGeoPoint();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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
	public static PartyGeoPoint mapstrstr(Map<String, String> fields) throws Exception {

		PartyGeoPoint returnVal = new PartyGeoPoint();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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
	public static PartyGeoPoint map(GenericValue val) {

PartyGeoPoint returnVal = new PartyGeoPoint();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyGeoPoint map(HttpServletRequest request) throws Exception {

		PartyGeoPoint returnVal = new PartyGeoPoint();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
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
