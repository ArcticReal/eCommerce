package com.skytala.eCommerce.domain.timeEntry.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.timeEntry.model.TimeEntry;

public class TimeEntryMapper  {


	public static Map<String, Object> map(TimeEntry timeentry) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(timeentry.getTimeEntryId() != null ){
			returnVal.put("timeEntryId",timeentry.getTimeEntryId());
}

		if(timeentry.getPartyId() != null ){
			returnVal.put("partyId",timeentry.getPartyId());
}

		if(timeentry.getFromDate() != null ){
			returnVal.put("fromDate",timeentry.getFromDate());
}

		if(timeentry.getThruDate() != null ){
			returnVal.put("thruDate",timeentry.getThruDate());
}

		if(timeentry.getRateTypeId() != null ){
			returnVal.put("rateTypeId",timeentry.getRateTypeId());
}

		if(timeentry.getWorkEffortId() != null ){
			returnVal.put("workEffortId",timeentry.getWorkEffortId());
}

		if(timeentry.getTimesheetId() != null ){
			returnVal.put("timesheetId",timeentry.getTimesheetId());
}

		if(timeentry.getInvoiceId() != null ){
			returnVal.put("invoiceId",timeentry.getInvoiceId());
}

		if(timeentry.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",timeentry.getInvoiceItemSeqId());
}

		if(timeentry.getHours() != null ){
			returnVal.put("hours",timeentry.getHours());
}

		if(timeentry.getComments() != null ){
			returnVal.put("comments",timeentry.getComments());
}

		return returnVal;
}


	public static TimeEntry map(Map<String, Object> fields) {

		TimeEntry returnVal = new TimeEntry();

		if(fields.get("timeEntryId") != null) {
			returnVal.setTimeEntryId((String) fields.get("timeEntryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("hours") != null) {
			returnVal.setHours((BigDecimal) fields.get("hours"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static TimeEntry mapstrstr(Map<String, String> fields) throws Exception {

		TimeEntry returnVal = new TimeEntry();

		if(fields.get("timeEntryId") != null) {
			returnVal.setTimeEntryId((String) fields.get("timeEntryId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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

		if(fields.get("rateTypeId") != null) {
			returnVal.setRateTypeId((String) fields.get("rateTypeId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("timesheetId") != null) {
			returnVal.setTimesheetId((String) fields.get("timesheetId"));
}

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("hours") != null) {
String buf;
buf = fields.get("hours");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setHours(bd);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static TimeEntry map(GenericValue val) {

TimeEntry returnVal = new TimeEntry();
		returnVal.setTimeEntryId(val.getString("timeEntryId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setRateTypeId(val.getString("rateTypeId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setTimesheetId(val.getString("timesheetId"));
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setHours(val.getBigDecimal("hours"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static TimeEntry map(HttpServletRequest request) throws Exception {

		TimeEntry returnVal = new TimeEntry();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("timeEntryId")) {
returnVal.setTimeEntryId(request.getParameter("timeEntryId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
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
		if(paramMap.containsKey("rateTypeId"))  {
returnVal.setRateTypeId(request.getParameter("rateTypeId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("timesheetId"))  {
returnVal.setTimesheetId(request.getParameter("timesheetId"));
}
		if(paramMap.containsKey("invoiceId"))  {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}
		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("hours"))  {
String buf = request.getParameter("hours");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setHours(bd);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
