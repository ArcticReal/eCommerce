package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excDay;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excDay.TechDataCalendarExcDay;

public class TechDataCalendarExcDayMapper  {


	public static Map<String, Object> map(TechDataCalendarExcDay techdatacalendarexcday) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(techdatacalendarexcday.getCalendarId() != null ){
			returnVal.put("calendarId",techdatacalendarexcday.getCalendarId());
}

		if(techdatacalendarexcday.getExceptionDateStartTime() != null ){
			returnVal.put("exceptionDateStartTime",techdatacalendarexcday.getExceptionDateStartTime());
}

		if(techdatacalendarexcday.getExceptionCapacity() != null ){
			returnVal.put("exceptionCapacity",techdatacalendarexcday.getExceptionCapacity());
}

		if(techdatacalendarexcday.getUsedCapacity() != null ){
			returnVal.put("usedCapacity",techdatacalendarexcday.getUsedCapacity());
}

		if(techdatacalendarexcday.getDescription() != null ){
			returnVal.put("description",techdatacalendarexcday.getDescription());
}

		return returnVal;
}


	public static TechDataCalendarExcDay map(Map<String, Object> fields) {

		TechDataCalendarExcDay returnVal = new TechDataCalendarExcDay();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("exceptionDateStartTime") != null) {
			returnVal.setExceptionDateStartTime((Timestamp) fields.get("exceptionDateStartTime"));
}

		if(fields.get("exceptionCapacity") != null) {
			returnVal.setExceptionCapacity((BigDecimal) fields.get("exceptionCapacity"));
}

		if(fields.get("usedCapacity") != null) {
			returnVal.setUsedCapacity((BigDecimal) fields.get("usedCapacity"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TechDataCalendarExcDay mapstrstr(Map<String, String> fields) throws Exception {

		TechDataCalendarExcDay returnVal = new TechDataCalendarExcDay();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("exceptionDateStartTime") != null) {
String buf = fields.get("exceptionDateStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExceptionDateStartTime(ibuf);
}

		if(fields.get("exceptionCapacity") != null) {
String buf;
buf = fields.get("exceptionCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExceptionCapacity(bd);
}

		if(fields.get("usedCapacity") != null) {
String buf;
buf = fields.get("usedCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUsedCapacity(bd);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TechDataCalendarExcDay map(GenericValue val) {

TechDataCalendarExcDay returnVal = new TechDataCalendarExcDay();
		returnVal.setCalendarId(val.getString("calendarId"));
		returnVal.setExceptionDateStartTime(val.getTimestamp("exceptionDateStartTime"));
		returnVal.setExceptionCapacity(val.getBigDecimal("exceptionCapacity"));
		returnVal.setUsedCapacity(val.getBigDecimal("usedCapacity"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TechDataCalendarExcDay map(HttpServletRequest request) throws Exception {

		TechDataCalendarExcDay returnVal = new TechDataCalendarExcDay();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("calendarId")) {
returnVal.setCalendarId(request.getParameter("calendarId"));
}

		if(paramMap.containsKey("exceptionDateStartTime"))  {
String buf = request.getParameter("exceptionDateStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExceptionDateStartTime(ibuf);
}
		if(paramMap.containsKey("exceptionCapacity"))  {
String buf = request.getParameter("exceptionCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setExceptionCapacity(bd);
}
		if(paramMap.containsKey("usedCapacity"))  {
String buf = request.getParameter("usedCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUsedCapacity(bd);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
