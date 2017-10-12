package com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.EmplLeave;

public class EmplLeaveMapper  {


	public static Map<String, Object> map(EmplLeave emplleave) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplleave.getPartyId() != null ){
			returnVal.put("partyId",emplleave.getPartyId());
}

		if(emplleave.getLeaveTypeId() != null ){
			returnVal.put("leaveTypeId",emplleave.getLeaveTypeId());
}

		if(emplleave.getEmplLeaveReasonTypeId() != null ){
			returnVal.put("emplLeaveReasonTypeId",emplleave.getEmplLeaveReasonTypeId());
}

		if(emplleave.getFromDate() != null ){
			returnVal.put("fromDate",emplleave.getFromDate());
}

		if(emplleave.getThruDate() != null ){
			returnVal.put("thruDate",emplleave.getThruDate());
}

		if(emplleave.getApproverPartyId() != null ){
			returnVal.put("approverPartyId",emplleave.getApproverPartyId());
}

		if(emplleave.getLeaveStatus() != null ){
			returnVal.put("leaveStatus",emplleave.getLeaveStatus());
}

		if(emplleave.getDescription() != null ){
			returnVal.put("description",emplleave.getDescription());
}

		return returnVal;
}


	public static EmplLeave map(Map<String, Object> fields) {

		EmplLeave returnVal = new EmplLeave();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("leaveTypeId") != null) {
			returnVal.setLeaveTypeId((String) fields.get("leaveTypeId"));
}

		if(fields.get("emplLeaveReasonTypeId") != null) {
			returnVal.setEmplLeaveReasonTypeId((String) fields.get("emplLeaveReasonTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("approverPartyId") != null) {
			returnVal.setApproverPartyId((String) fields.get("approverPartyId"));
}

		if(fields.get("leaveStatus") != null) {
			returnVal.setLeaveStatus((String) fields.get("leaveStatus"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static EmplLeave mapstrstr(Map<String, String> fields) throws Exception {

		EmplLeave returnVal = new EmplLeave();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("leaveTypeId") != null) {
			returnVal.setLeaveTypeId((String) fields.get("leaveTypeId"));
}

		if(fields.get("emplLeaveReasonTypeId") != null) {
			returnVal.setEmplLeaveReasonTypeId((String) fields.get("emplLeaveReasonTypeId"));
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

		if(fields.get("approverPartyId") != null) {
			returnVal.setApproverPartyId((String) fields.get("approverPartyId"));
}

		if(fields.get("leaveStatus") != null) {
			returnVal.setLeaveStatus((String) fields.get("leaveStatus"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static EmplLeave map(GenericValue val) {

EmplLeave returnVal = new EmplLeave();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setLeaveTypeId(val.getString("leaveTypeId"));
		returnVal.setEmplLeaveReasonTypeId(val.getString("emplLeaveReasonTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setApproverPartyId(val.getString("approverPartyId"));
		returnVal.setLeaveStatus(val.getString("leaveStatus"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmplLeave map(HttpServletRequest request) throws Exception {

		EmplLeave returnVal = new EmplLeave();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("leaveTypeId"))  {
returnVal.setLeaveTypeId(request.getParameter("leaveTypeId"));
}
		if(paramMap.containsKey("emplLeaveReasonTypeId"))  {
returnVal.setEmplLeaveReasonTypeId(request.getParameter("emplLeaveReasonTypeId"));
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
		if(paramMap.containsKey("approverPartyId"))  {
returnVal.setApproverPartyId(request.getParameter("approverPartyId"));
}
		if(paramMap.containsKey("leaveStatus"))  {
returnVal.setLeaveStatus(request.getParameter("leaveStatus"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
