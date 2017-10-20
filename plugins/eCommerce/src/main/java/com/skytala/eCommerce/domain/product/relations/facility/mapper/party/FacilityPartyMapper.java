package com.skytala.eCommerce.domain.product.relations.facility.mapper.party;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;

public class FacilityPartyMapper  {


	public static Map<String, Object> map(FacilityParty facilityparty) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilityparty.getFacilityId() != null ){
			returnVal.put("facilityId",facilityparty.getFacilityId());
}

		if(facilityparty.getPartyId() != null ){
			returnVal.put("partyId",facilityparty.getPartyId());
}

		if(facilityparty.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",facilityparty.getRoleTypeId());
}

		if(facilityparty.getFromDate() != null ){
			returnVal.put("fromDate",facilityparty.getFromDate());
}

		if(facilityparty.getThruDate() != null ){
			returnVal.put("thruDate",facilityparty.getThruDate());
}

		return returnVal;
}


	public static FacilityParty map(Map<String, Object> fields) {

		FacilityParty returnVal = new FacilityParty();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FacilityParty mapstrstr(Map<String, String> fields) throws Exception {

		FacilityParty returnVal = new FacilityParty();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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
	public static FacilityParty map(GenericValue val) {

FacilityParty returnVal = new FacilityParty();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FacilityParty map(HttpServletRequest request) throws Exception {

		FacilityParty returnVal = new FacilityParty();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
