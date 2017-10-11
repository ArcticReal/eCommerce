package com.skytala.eCommerce.domain.product.relations.facilityGroupMember.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroupMember.model.FacilityGroupMember;

public class FacilityGroupMemberMapper  {


	public static Map<String, Object> map(FacilityGroupMember facilitygroupmember) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitygroupmember.getFacilityId() != null ){
			returnVal.put("facilityId",facilitygroupmember.getFacilityId());
}

		if(facilitygroupmember.getFacilityGroupId() != null ){
			returnVal.put("facilityGroupId",facilitygroupmember.getFacilityGroupId());
}

		if(facilitygroupmember.getFromDate() != null ){
			returnVal.put("fromDate",facilitygroupmember.getFromDate());
}

		if(facilitygroupmember.getThruDate() != null ){
			returnVal.put("thruDate",facilitygroupmember.getThruDate());
}

		if(facilitygroupmember.getSequenceNum() != null ){
			returnVal.put("sequenceNum",facilitygroupmember.getSequenceNum());
}

		return returnVal;
}


	public static FacilityGroupMember map(Map<String, Object> fields) {

		FacilityGroupMember returnVal = new FacilityGroupMember();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
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
	public static FacilityGroupMember mapstrstr(Map<String, String> fields) throws Exception {

		FacilityGroupMember returnVal = new FacilityGroupMember();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
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
	public static FacilityGroupMember map(GenericValue val) {

FacilityGroupMember returnVal = new FacilityGroupMember();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setFacilityGroupId(val.getString("facilityGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static FacilityGroupMember map(HttpServletRequest request) throws Exception {

		FacilityGroupMember returnVal = new FacilityGroupMember();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("facilityGroupId"))  {
returnVal.setFacilityGroupId(request.getParameter("facilityGroupId"));
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
