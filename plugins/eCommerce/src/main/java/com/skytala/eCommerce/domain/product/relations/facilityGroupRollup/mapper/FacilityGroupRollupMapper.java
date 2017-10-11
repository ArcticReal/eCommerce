package com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRollup.model.FacilityGroupRollup;

public class FacilityGroupRollupMapper  {


	public static Map<String, Object> map(FacilityGroupRollup facilitygrouprollup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitygrouprollup.getFacilityGroupId() != null ){
			returnVal.put("facilityGroupId",facilitygrouprollup.getFacilityGroupId());
}

		if(facilitygrouprollup.getParentFacilityGroupId() != null ){
			returnVal.put("parentFacilityGroupId",facilitygrouprollup.getParentFacilityGroupId());
}

		if(facilitygrouprollup.getFromDate() != null ){
			returnVal.put("fromDate",facilitygrouprollup.getFromDate());
}

		if(facilitygrouprollup.getThruDate() != null ){
			returnVal.put("thruDate",facilitygrouprollup.getThruDate());
}

		if(facilitygrouprollup.getSequenceNum() != null ){
			returnVal.put("sequenceNum",facilitygrouprollup.getSequenceNum());
}

		return returnVal;
}


	public static FacilityGroupRollup map(Map<String, Object> fields) {

		FacilityGroupRollup returnVal = new FacilityGroupRollup();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("parentFacilityGroupId") != null) {
			returnVal.setParentFacilityGroupId((String) fields.get("parentFacilityGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static FacilityGroupRollup mapstrstr(Map<String, String> fields) throws Exception {

		FacilityGroupRollup returnVal = new FacilityGroupRollup();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("parentFacilityGroupId") != null) {
			returnVal.setParentFacilityGroupId((String) fields.get("parentFacilityGroupId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static FacilityGroupRollup map(GenericValue val) {

FacilityGroupRollup returnVal = new FacilityGroupRollup();
		returnVal.setFacilityGroupId(val.getString("facilityGroupId"));
		returnVal.setParentFacilityGroupId(val.getString("parentFacilityGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static FacilityGroupRollup map(HttpServletRequest request) throws Exception {

		FacilityGroupRollup returnVal = new FacilityGroupRollup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityGroupId")) {
returnVal.setFacilityGroupId(request.getParameter("facilityGroupId"));
}

		if(paramMap.containsKey("parentFacilityGroupId"))  {
returnVal.setParentFacilityGroupId(request.getParameter("parentFacilityGroupId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
