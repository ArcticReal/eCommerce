package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.TechDataCalendar;

public class TechDataCalendarMapper  {


	public static Map<String, Object> map(TechDataCalendar techdatacalendar) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(techdatacalendar.getCalendarId() != null ){
			returnVal.put("calendarId",techdatacalendar.getCalendarId());
}

		if(techdatacalendar.getDescription() != null ){
			returnVal.put("description",techdatacalendar.getDescription());
}

		if(techdatacalendar.getCalendarWeekId() != null ){
			returnVal.put("calendarWeekId",techdatacalendar.getCalendarWeekId());
}

		return returnVal;
}


	public static TechDataCalendar map(Map<String, Object> fields) {

		TechDataCalendar returnVal = new TechDataCalendar();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}


		return returnVal;
 } 
	public static TechDataCalendar mapstrstr(Map<String, String> fields) throws Exception {

		TechDataCalendar returnVal = new TechDataCalendar();

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}


		return returnVal;
 } 
	public static TechDataCalendar map(GenericValue val) {

TechDataCalendar returnVal = new TechDataCalendar();
		returnVal.setCalendarId(val.getString("calendarId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setCalendarWeekId(val.getString("calendarWeekId"));


return returnVal;

}

public static TechDataCalendar map(HttpServletRequest request) throws Exception {

		TechDataCalendar returnVal = new TechDataCalendar();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("calendarId")) {
returnVal.setCalendarId(request.getParameter("calendarId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("calendarWeekId"))  {
returnVal.setCalendarWeekId(request.getParameter("calendarWeekId"));
}
return returnVal;

}
}
