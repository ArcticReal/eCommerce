package com.skytala.eCommerce.domain.product.relations.facilityContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityContactMech.model.FacilityContactMech;

public class FacilityContactMechMapper  {


	public static Map<String, Object> map(FacilityContactMech facilitycontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitycontactmech.getFacilityId() != null ){
			returnVal.put("facilityId",facilitycontactmech.getFacilityId());
}

		if(facilitycontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",facilitycontactmech.getContactMechId());
}

		if(facilitycontactmech.getFromDate() != null ){
			returnVal.put("fromDate",facilitycontactmech.getFromDate());
}

		if(facilitycontactmech.getThruDate() != null ){
			returnVal.put("thruDate",facilitycontactmech.getThruDate());
}

		if(facilitycontactmech.getExtension() != null ){
			returnVal.put("extension",facilitycontactmech.getExtension());
}

		if(facilitycontactmech.getComments() != null ){
			returnVal.put("comments",facilitycontactmech.getComments());
}

		return returnVal;
}


	public static FacilityContactMech map(Map<String, Object> fields) {

		FacilityContactMech returnVal = new FacilityContactMech();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static FacilityContactMech mapstrstr(Map<String, String> fields) throws Exception {

		FacilityContactMech returnVal = new FacilityContactMech();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
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

		if(fields.get("extension") != null) {
			returnVal.setExtension((String) fields.get("extension"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static FacilityContactMech map(GenericValue val) {

FacilityContactMech returnVal = new FacilityContactMech();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setExtension(val.getString("extension"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static FacilityContactMech map(HttpServletRequest request) throws Exception {

		FacilityContactMech returnVal = new FacilityContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
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
		if(paramMap.containsKey("extension"))  {
returnVal.setExtension(request.getParameter("extension"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
