package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.partyAssignment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.partyAssignment.WorkEffortPartyAssignment;

public class WorkEffortPartyAssignmentMapper  {


	public static Map<String, Object> map(WorkEffortPartyAssignment workeffortpartyassignment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortpartyassignment.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortpartyassignment.getWorkEffortId());
}

		if(workeffortpartyassignment.getPartyId() != null ){
			returnVal.put("partyId",workeffortpartyassignment.getPartyId());
}

		if(workeffortpartyassignment.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",workeffortpartyassignment.getRoleTypeId());
}

		if(workeffortpartyassignment.getFromDate() != null ){
			returnVal.put("fromDate",workeffortpartyassignment.getFromDate());
}

		if(workeffortpartyassignment.getThruDate() != null ){
			returnVal.put("thruDate",workeffortpartyassignment.getThruDate());
}

		if(workeffortpartyassignment.getAssignedByUserLoginId() != null ){
			returnVal.put("assignedByUserLoginId",workeffortpartyassignment.getAssignedByUserLoginId());
}

		if(workeffortpartyassignment.getStatusId() != null ){
			returnVal.put("statusId",workeffortpartyassignment.getStatusId());
}

		if(workeffortpartyassignment.getStatusDateTime() != null ){
			returnVal.put("statusDateTime",workeffortpartyassignment.getStatusDateTime());
}

		if(workeffortpartyassignment.getExpectationEnumId() != null ){
			returnVal.put("expectationEnumId",workeffortpartyassignment.getExpectationEnumId());
}

		if(workeffortpartyassignment.getDelegateReasonEnumId() != null ){
			returnVal.put("delegateReasonEnumId",workeffortpartyassignment.getDelegateReasonEnumId());
}

		if(workeffortpartyassignment.getFacilityId() != null ){
			returnVal.put("facilityId",workeffortpartyassignment.getFacilityId());
}

		if(workeffortpartyassignment.getComments() != null ){
			returnVal.put("comments",workeffortpartyassignment.getComments());
}

		if(workeffortpartyassignment.getMustRsvp() != null ){
			returnVal.put("mustRsvp",workeffortpartyassignment.getMustRsvp());
}

		if(workeffortpartyassignment.getAvailabilityStatusId() != null ){
			returnVal.put("availabilityStatusId",workeffortpartyassignment.getAvailabilityStatusId());
}

		return returnVal;
}


	public static WorkEffortPartyAssignment map(Map<String, Object> fields) {

		WorkEffortPartyAssignment returnVal = new WorkEffortPartyAssignment();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
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

		if(fields.get("assignedByUserLoginId") != null) {
			returnVal.setAssignedByUserLoginId((String) fields.get("assignedByUserLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDateTime") != null) {
			returnVal.setStatusDateTime((Timestamp) fields.get("statusDateTime"));
}

		if(fields.get("expectationEnumId") != null) {
			returnVal.setExpectationEnumId((String) fields.get("expectationEnumId"));
}

		if(fields.get("delegateReasonEnumId") != null) {
			returnVal.setDelegateReasonEnumId((String) fields.get("delegateReasonEnumId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("mustRsvp") != null) {
			returnVal.setMustRsvp((boolean) fields.get("mustRsvp"));
}

		if(fields.get("availabilityStatusId") != null) {
			returnVal.setAvailabilityStatusId((String) fields.get("availabilityStatusId"));
}


		return returnVal;
 } 
	public static WorkEffortPartyAssignment mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortPartyAssignment returnVal = new WorkEffortPartyAssignment();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
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

		if(fields.get("assignedByUserLoginId") != null) {
			returnVal.setAssignedByUserLoginId((String) fields.get("assignedByUserLoginId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDateTime") != null) {
String buf = fields.get("statusDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDateTime(ibuf);
}

		if(fields.get("expectationEnumId") != null) {
			returnVal.setExpectationEnumId((String) fields.get("expectationEnumId"));
}

		if(fields.get("delegateReasonEnumId") != null) {
			returnVal.setDelegateReasonEnumId((String) fields.get("delegateReasonEnumId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("mustRsvp") != null) {
String buf;
buf = fields.get("mustRsvp");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setMustRsvp(ibuf);
}

		if(fields.get("availabilityStatusId") != null) {
			returnVal.setAvailabilityStatusId((String) fields.get("availabilityStatusId"));
}


		return returnVal;
 } 
	public static WorkEffortPartyAssignment map(GenericValue val) {

WorkEffortPartyAssignment returnVal = new WorkEffortPartyAssignment();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAssignedByUserLoginId(val.getString("assignedByUserLoginId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDateTime(val.getTimestamp("statusDateTime"));
		returnVal.setExpectationEnumId(val.getString("expectationEnumId"));
		returnVal.setDelegateReasonEnumId(val.getString("delegateReasonEnumId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setMustRsvp(val.getBoolean("mustRsvp"));
		returnVal.setAvailabilityStatusId(val.getString("availabilityStatusId"));


return returnVal;

}

public static WorkEffortPartyAssignment map(HttpServletRequest request) throws Exception {

		WorkEffortPartyAssignment returnVal = new WorkEffortPartyAssignment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
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
		if(paramMap.containsKey("assignedByUserLoginId"))  {
returnVal.setAssignedByUserLoginId(request.getParameter("assignedByUserLoginId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDateTime"))  {
String buf = request.getParameter("statusDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDateTime(ibuf);
}
		if(paramMap.containsKey("expectationEnumId"))  {
returnVal.setExpectationEnumId(request.getParameter("expectationEnumId"));
}
		if(paramMap.containsKey("delegateReasonEnumId"))  {
returnVal.setDelegateReasonEnumId(request.getParameter("delegateReasonEnumId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("mustRsvp"))  {
String buf = request.getParameter("mustRsvp");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setMustRsvp(ibuf);
}
		if(paramMap.containsKey("availabilityStatusId"))  {
returnVal.setAvailabilityStatusId(request.getParameter("availabilityStatusId"));
}
return returnVal;

}
}
