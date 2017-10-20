package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.excWeek;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.excWeek.TechDataCalendarExcWeek;

public class TechDataCalendarExcWeekMapper  {


	public static Map<String, Object> map(TechDataCalendarExcWeek techdatacalendarexcweek) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(techdatacalendarexcweek.getCalendarId() != null ){
			returnVal.put("calendarId",techdatacalendarexcweek.getCalendarId());
}

		if(techdatacalendarexcweek.getExceptionDateStart() != null ){
			returnVal.put("exceptionDateStart",techdatacalendarexcweek.getExceptionDateStart());
}

		if(techdatacalendarexcweek.getCalendarWeekId() != null ){
			returnVal.put("calendarWeekId",techdatacalendarexcweek.getCalendarWeekId());
}

		if(techdatacalendarexcweek.getDescription() != null ){
			returnVal.put("description",techdatacalendarexcweek.getDescription());
}

		return returnVal;
}


	public static TechDataCalendarExcWeek map(Map<String, Object> fields) {

		TechDataCalendarExcWeek returnVal = new TechDataCalendarExcWeek();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("exceptionDateStart") != null) {
			returnVal.setExceptionDateStart((Timestamp) fields.get("exceptionDateStart"));
}

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TechDataCalendarExcWeek mapstrstr(Map<String, String> fields) throws Exception {

		TechDataCalendarExcWeek returnVal = new TechDataCalendarExcWeek();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("exceptionDateStart") != null) {
String buf = fields.get("exceptionDateStart");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExceptionDateStart(ibuf);
}

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TechDataCalendarExcWeek map(GenericValue val) {

TechDataCalendarExcWeek returnVal = new TechDataCalendarExcWeek();
		returnVal.setCalendarId(val.getString("calendarId"));
		returnVal.setExceptionDateStart(val.getTimestamp("exceptionDateStart"));
		returnVal.setCalendarWeekId(val.getString("calendarWeekId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TechDataCalendarExcWeek map(HttpServletRequest request) throws Exception {

		TechDataCalendarExcWeek returnVal = new TechDataCalendarExcWeek();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("calendarId")) {
returnVal.setCalendarId(request.getParameter("calendarId"));
}

		if(paramMap.containsKey("exceptionDateStart"))  {
String buf = request.getParameter("exceptionDateStart");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExceptionDateStart(ibuf);
}
		if(paramMap.containsKey("calendarWeekId"))  {
returnVal.setCalendarWeekId(request.getParameter("calendarWeekId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
