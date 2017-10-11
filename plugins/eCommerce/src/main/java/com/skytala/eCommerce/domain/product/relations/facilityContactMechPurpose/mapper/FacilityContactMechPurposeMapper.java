package com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityContactMechPurpose.model.FacilityContactMechPurpose;

public class FacilityContactMechPurposeMapper  {


	public static Map<String, Object> map(FacilityContactMechPurpose facilitycontactmechpurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitycontactmechpurpose.getFacilityId() != null ){
			returnVal.put("facilityId",facilitycontactmechpurpose.getFacilityId());
}

		if(facilitycontactmechpurpose.getContactMechId() != null ){
			returnVal.put("contactMechId",facilitycontactmechpurpose.getContactMechId());
}

		if(facilitycontactmechpurpose.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",facilitycontactmechpurpose.getContactMechPurposeTypeId());
}

		if(facilitycontactmechpurpose.getFromDate() != null ){
			returnVal.put("fromDate",facilitycontactmechpurpose.getFromDate());
}

		if(facilitycontactmechpurpose.getThruDate() != null ){
			returnVal.put("thruDate",facilitycontactmechpurpose.getThruDate());
}

		return returnVal;
}


	public static FacilityContactMechPurpose map(Map<String, Object> fields) {

		FacilityContactMechPurpose returnVal = new FacilityContactMechPurpose();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static FacilityContactMechPurpose mapstrstr(Map<String, String> fields) throws Exception {

		FacilityContactMechPurpose returnVal = new FacilityContactMechPurpose();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
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
	public static FacilityContactMechPurpose map(GenericValue val) {

FacilityContactMechPurpose returnVal = new FacilityContactMechPurpose();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static FacilityContactMechPurpose map(HttpServletRequest request) throws Exception {

		FacilityContactMechPurpose returnVal = new FacilityContactMechPurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("contactMechPurposeTypeId"))  {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
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
