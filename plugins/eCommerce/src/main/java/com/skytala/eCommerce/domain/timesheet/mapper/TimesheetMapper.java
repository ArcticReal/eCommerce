package com.skytala.eCommerce.domain.timesheet.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.timesheet.model.Timesheet;

public class TimesheetMapper  {


	public static Map<String, Object> map(Timesheet timesheet) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(timesheet.getTimesheetId() != null ){
			returnVal.put("timesheetId",timesheet.getTimesheetId());
}

		if(timesheet.getPartyId() != null ){
			returnVal.put("partyId",timesheet.getPartyId());
}

		if(timesheet.getClientPartyId() != null ){
			returnVal.put("clientPartyId",timesheet.getClientPartyId());
}

		if(timesheet.getFromDate() != null ){
			returnVal.put("fromDate",timesheet.getFromDate());
}

		if(timesheet.getThruDate() != null ){
			returnVal.put("thruDate",timesheet.getThruDate());
}

		if(timesheet.getStatusId() != null ){
			returnVal.put("statusId",timesheet.getStatusId());
}

		if(timesheet.getApprovedByUserLoginId() != null ){
			returnVal.put("approvedByUserLoginId",timesheet.getApprovedByUserLoginId());
}

		if(timesheet.getComments() != null ){
			returnVal.put("comments",timesheet.getComments());
}

		return returnVal;
}


	public static Timesheet map(Map<String, Object> fields) {

		Timesheet returnVal = new Timesheet();

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("clientPartyId") != null) {
			returnVal.setClientPartyId((String) fields.get("clientPartyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("approvedByUserLoginId") != null) {
			returnVal.setApprovedByUserLoginId((String) fields.get("approvedByUserLoginId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static Timesheet mapstrstr(Map<String, String> fields) throws Exception {

		Timesheet returnVal = new Timesheet();

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("clientPartyId") != null) {
			returnVal.setClientPartyId((String) fields.get("clientPartyId"));
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

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("approvedByUserLoginId") != null) {
			returnVal.setApprovedByUserLoginId((String) fields.get("approvedByUserLoginId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static Timesheet map(GenericValue val) {

Timesheet returnVal = new Timesheet();
		returnVal.setTimesheetId(val.getString("timesheetId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setClientPartyId(val.getString("clientPartyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setApprovedByUserLoginId(val.getString("approvedByUserLoginId"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static Timesheet map(HttpServletRequest request) throws Exception {

		Timesheet returnVal = new Timesheet();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("timesheetId")) {
returnVal.setTimesheetId(request.getParameter("timesheetId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("clientPartyId"))  {
returnVal.setClientPartyId(request.getParameter("clientPartyId"));
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
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("approvedByUserLoginId"))  {
returnVal.setApprovedByUserLoginId(request.getParameter("approvedByUserLoginId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
