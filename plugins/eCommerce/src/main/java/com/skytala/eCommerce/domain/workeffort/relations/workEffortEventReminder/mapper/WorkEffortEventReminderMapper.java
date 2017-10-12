package com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortEventReminder.model.WorkEffortEventReminder;

public class WorkEffortEventReminderMapper  {


	public static Map<String, Object> map(WorkEffortEventReminder workefforteventreminder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workefforteventreminder.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workefforteventreminder.getWorkEffortId());
}

		if(workefforteventreminder.getSequenceId() != null ){
			returnVal.put("sequenceId",workefforteventreminder.getSequenceId());
}

		if(workefforteventreminder.getContactMechId() != null ){
			returnVal.put("contactMechId",workefforteventreminder.getContactMechId());
}

		if(workefforteventreminder.getPartyId() != null ){
			returnVal.put("partyId",workefforteventreminder.getPartyId());
}

		if(workefforteventreminder.getReminderDateTime() != null ){
			returnVal.put("reminderDateTime",workefforteventreminder.getReminderDateTime());
}

		if(workefforteventreminder.getRepeatCount() != null ){
			returnVal.put("repeatCount",workefforteventreminder.getRepeatCount());
}

		if(workefforteventreminder.getRepeatInterval() != null ){
			returnVal.put("repeatInterval",workefforteventreminder.getRepeatInterval());
}

		if(workefforteventreminder.getCurrentCount() != null ){
			returnVal.put("currentCount",workefforteventreminder.getCurrentCount());
}

		if(workefforteventreminder.getReminderOffset() != null ){
			returnVal.put("reminderOffset",workefforteventreminder.getReminderOffset());
}

		if(workefforteventreminder.getLocaleId() != null ){
			returnVal.put("localeId",workefforteventreminder.getLocaleId());
}

		if(workefforteventreminder.getTimeZoneId() != null ){
			returnVal.put("timeZoneId",workefforteventreminder.getTimeZoneId());
}

		return returnVal;
}


	public static WorkEffortEventReminder map(Map<String, Object> fields) {

		WorkEffortEventReminder returnVal = new WorkEffortEventReminder();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("reminderDateTime") != null) {
			returnVal.setReminderDateTime((Timestamp) fields.get("reminderDateTime"));
}

		if(fields.get("repeatCount") != null) {
			returnVal.setRepeatCount((long) fields.get("repeatCount"));
}

		if(fields.get("repeatInterval") != null) {
			returnVal.setRepeatInterval((long) fields.get("repeatInterval"));
}

		if(fields.get("currentCount") != null) {
			returnVal.setCurrentCount((long) fields.get("currentCount"));
}

		if(fields.get("reminderOffset") != null) {
			returnVal.setReminderOffset((long) fields.get("reminderOffset"));
}

		if(fields.get("localeId") != null) {
			returnVal.setLocaleId((String) fields.get("localeId"));
}

		if(fields.get("timeZoneId") != null) {
			returnVal.setTimeZoneId((String) fields.get("timeZoneId"));
}


		return returnVal;
 } 
	public static WorkEffortEventReminder mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortEventReminder returnVal = new WorkEffortEventReminder();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("reminderDateTime") != null) {
String buf = fields.get("reminderDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReminderDateTime(ibuf);
}

		if(fields.get("repeatCount") != null) {
String buf;
buf = fields.get("repeatCount");
long ibuf = Long.parseLong(buf);
			returnVal.setRepeatCount(ibuf);
}

		if(fields.get("repeatInterval") != null) {
String buf;
buf = fields.get("repeatInterval");
long ibuf = Long.parseLong(buf);
			returnVal.setRepeatInterval(ibuf);
}

		if(fields.get("currentCount") != null) {
String buf;
buf = fields.get("currentCount");
long ibuf = Long.parseLong(buf);
			returnVal.setCurrentCount(ibuf);
}

		if(fields.get("reminderOffset") != null) {
String buf;
buf = fields.get("reminderOffset");
long ibuf = Long.parseLong(buf);
			returnVal.setReminderOffset(ibuf);
}

		if(fields.get("localeId") != null) {
			returnVal.setLocaleId((String) fields.get("localeId"));
}

		if(fields.get("timeZoneId") != null) {
			returnVal.setTimeZoneId((String) fields.get("timeZoneId"));
}


		return returnVal;
 } 
	public static WorkEffortEventReminder map(GenericValue val) {

WorkEffortEventReminder returnVal = new WorkEffortEventReminder();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setSequenceId(val.getString("sequenceId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setReminderDateTime(val.getTimestamp("reminderDateTime"));
		returnVal.setRepeatCount(val.getLong("repeatCount"));
		returnVal.setRepeatInterval(val.getLong("repeatInterval"));
		returnVal.setCurrentCount(val.getLong("currentCount"));
		returnVal.setReminderOffset(val.getLong("reminderOffset"));
		returnVal.setLocaleId(val.getString("localeId"));
		returnVal.setTimeZoneId(val.getString("timeZoneId"));


return returnVal;

}

public static WorkEffortEventReminder map(HttpServletRequest request) throws Exception {

		WorkEffortEventReminder returnVal = new WorkEffortEventReminder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("sequenceId"))  {
returnVal.setSequenceId(request.getParameter("sequenceId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("reminderDateTime"))  {
String buf = request.getParameter("reminderDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReminderDateTime(ibuf);
}
		if(paramMap.containsKey("repeatCount"))  {
String buf = request.getParameter("repeatCount");
Long ibuf = Long.parseLong(buf);
returnVal.setRepeatCount(ibuf);
}
		if(paramMap.containsKey("repeatInterval"))  {
String buf = request.getParameter("repeatInterval");
Long ibuf = Long.parseLong(buf);
returnVal.setRepeatInterval(ibuf);
}
		if(paramMap.containsKey("currentCount"))  {
String buf = request.getParameter("currentCount");
Long ibuf = Long.parseLong(buf);
returnVal.setCurrentCount(ibuf);
}
		if(paramMap.containsKey("reminderOffset"))  {
String buf = request.getParameter("reminderOffset");
Long ibuf = Long.parseLong(buf);
returnVal.setReminderOffset(ibuf);
}
		if(paramMap.containsKey("localeId"))  {
returnVal.setLocaleId(request.getParameter("localeId"));
}
		if(paramMap.containsKey("timeZoneId"))  {
returnVal.setTimeZoneId(request.getParameter("timeZoneId"));
}
return returnVal;

}
}
