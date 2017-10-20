package com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.mapper.week;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.techDataCalendar.model.week.TechDataCalendarWeek;

public class TechDataCalendarWeekMapper  {


	public static Map<String, Object> map(TechDataCalendarWeek techdatacalendarweek) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(techdatacalendarweek.getCalendarWeekId() != null ){
			returnVal.put("calendarWeekId",techdatacalendarweek.getCalendarWeekId());
}

		if(techdatacalendarweek.getDescription() != null ){
			returnVal.put("description",techdatacalendarweek.getDescription());
}

		if(techdatacalendarweek.getMondayStartTime() != null ){
			returnVal.put("mondayStartTime",techdatacalendarweek.getMondayStartTime());
}

		if(techdatacalendarweek.getMondayCapacity() != null ){
			returnVal.put("mondayCapacity",techdatacalendarweek.getMondayCapacity());
}

		if(techdatacalendarweek.getTuesdayStartTime() != null ){
			returnVal.put("tuesdayStartTime",techdatacalendarweek.getTuesdayStartTime());
}

		if(techdatacalendarweek.getTuesdayCapacity() != null ){
			returnVal.put("tuesdayCapacity",techdatacalendarweek.getTuesdayCapacity());
}

		if(techdatacalendarweek.getWednesdayStartTime() != null ){
			returnVal.put("wednesdayStartTime",techdatacalendarweek.getWednesdayStartTime());
}

		if(techdatacalendarweek.getWednesdayCapacity() != null ){
			returnVal.put("wednesdayCapacity",techdatacalendarweek.getWednesdayCapacity());
}

		if(techdatacalendarweek.getThursdayStartTime() != null ){
			returnVal.put("thursdayStartTime",techdatacalendarweek.getThursdayStartTime());
}

		if(techdatacalendarweek.getThursdayCapacity() != null ){
			returnVal.put("thursdayCapacity",techdatacalendarweek.getThursdayCapacity());
}

		if(techdatacalendarweek.getFridayStartTime() != null ){
			returnVal.put("fridayStartTime",techdatacalendarweek.getFridayStartTime());
}

		if(techdatacalendarweek.getFridayCapacity() != null ){
			returnVal.put("fridayCapacity",techdatacalendarweek.getFridayCapacity());
}

		if(techdatacalendarweek.getSaturdayStartTime() != null ){
			returnVal.put("saturdayStartTime",techdatacalendarweek.getSaturdayStartTime());
}

		if(techdatacalendarweek.getSaturdayCapacity() != null ){
			returnVal.put("saturdayCapacity",techdatacalendarweek.getSaturdayCapacity());
}

		if(techdatacalendarweek.getSundayStartTime() != null ){
			returnVal.put("sundayStartTime",techdatacalendarweek.getSundayStartTime());
}

		if(techdatacalendarweek.getSundayCapacity() != null ){
			returnVal.put("sundayCapacity",techdatacalendarweek.getSundayCapacity());
}

		return returnVal;
}


	public static TechDataCalendarWeek map(Map<String, Object> fields) {

		TechDataCalendarWeek returnVal = new TechDataCalendarWeek();

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("mondayStartTime") != null) {
			returnVal.setMondayStartTime((Timestamp) fields.get("mondayStartTime"));
}

		if(fields.get("mondayCapacity") != null) {
			returnVal.setMondayCapacity((BigDecimal) fields.get("mondayCapacity"));
}

		if(fields.get("tuesdayStartTime") != null) {
			returnVal.setTuesdayStartTime((Timestamp) fields.get("tuesdayStartTime"));
}

		if(fields.get("tuesdayCapacity") != null) {
			returnVal.setTuesdayCapacity((BigDecimal) fields.get("tuesdayCapacity"));
}

		if(fields.get("wednesdayStartTime") != null) {
			returnVal.setWednesdayStartTime((Timestamp) fields.get("wednesdayStartTime"));
}

		if(fields.get("wednesdayCapacity") != null) {
			returnVal.setWednesdayCapacity((BigDecimal) fields.get("wednesdayCapacity"));
}

		if(fields.get("thursdayStartTime") != null) {
			returnVal.setThursdayStartTime((Timestamp) fields.get("thursdayStartTime"));
}

		if(fields.get("thursdayCapacity") != null) {
			returnVal.setThursdayCapacity((BigDecimal) fields.get("thursdayCapacity"));
}

		if(fields.get("fridayStartTime") != null) {
			returnVal.setFridayStartTime((Timestamp) fields.get("fridayStartTime"));
}

		if(fields.get("fridayCapacity") != null) {
			returnVal.setFridayCapacity((BigDecimal) fields.get("fridayCapacity"));
}

		if(fields.get("saturdayStartTime") != null) {
			returnVal.setSaturdayStartTime((Timestamp) fields.get("saturdayStartTime"));
}

		if(fields.get("saturdayCapacity") != null) {
			returnVal.setSaturdayCapacity((BigDecimal) fields.get("saturdayCapacity"));
}

		if(fields.get("sundayStartTime") != null) {
			returnVal.setSundayStartTime((Timestamp) fields.get("sundayStartTime"));
}

		if(fields.get("sundayCapacity") != null) {
			returnVal.setSundayCapacity((BigDecimal) fields.get("sundayCapacity"));
}


		return returnVal;
 } 
	public static TechDataCalendarWeek mapstrstr(Map<String, String> fields) throws Exception {

		TechDataCalendarWeek returnVal = new TechDataCalendarWeek();

		if(fields.get("calendarWeekId") != null) {
			returnVal.setCalendarWeekId((String) fields.get("calendarWeekId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("mondayStartTime") != null) {
String buf = fields.get("mondayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setMondayStartTime(ibuf);
}

		if(fields.get("mondayCapacity") != null) {
String buf;
buf = fields.get("mondayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMondayCapacity(bd);
}

		if(fields.get("tuesdayStartTime") != null) {
String buf = fields.get("tuesdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setTuesdayStartTime(ibuf);
}

		if(fields.get("tuesdayCapacity") != null) {
String buf;
buf = fields.get("tuesdayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTuesdayCapacity(bd);
}

		if(fields.get("wednesdayStartTime") != null) {
String buf = fields.get("wednesdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setWednesdayStartTime(ibuf);
}

		if(fields.get("wednesdayCapacity") != null) {
String buf;
buf = fields.get("wednesdayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWednesdayCapacity(bd);
}

		if(fields.get("thursdayStartTime") != null) {
String buf = fields.get("thursdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThursdayStartTime(ibuf);
}

		if(fields.get("thursdayCapacity") != null) {
String buf;
buf = fields.get("thursdayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setThursdayCapacity(bd);
}

		if(fields.get("fridayStartTime") != null) {
String buf = fields.get("fridayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFridayStartTime(ibuf);
}

		if(fields.get("fridayCapacity") != null) {
String buf;
buf = fields.get("fridayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFridayCapacity(bd);
}

		if(fields.get("saturdayStartTime") != null) {
String buf = fields.get("saturdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSaturdayStartTime(ibuf);
}

		if(fields.get("saturdayCapacity") != null) {
String buf;
buf = fields.get("saturdayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSaturdayCapacity(bd);
}

		if(fields.get("sundayStartTime") != null) {
String buf = fields.get("sundayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setSundayStartTime(ibuf);
}

		if(fields.get("sundayCapacity") != null) {
String buf;
buf = fields.get("sundayCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSundayCapacity(bd);
}


		return returnVal;
 } 
	public static TechDataCalendarWeek map(GenericValue val) {

TechDataCalendarWeek returnVal = new TechDataCalendarWeek();
		returnVal.setCalendarWeekId(val.getString("calendarWeekId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setMondayStartTime(val.getTimestamp("mondayStartTime"));
		returnVal.setMondayCapacity(val.getBigDecimal("mondayCapacity"));
		returnVal.setTuesdayStartTime(val.getTimestamp("tuesdayStartTime"));
		returnVal.setTuesdayCapacity(val.getBigDecimal("tuesdayCapacity"));
		returnVal.setWednesdayStartTime(val.getTimestamp("wednesdayStartTime"));
		returnVal.setWednesdayCapacity(val.getBigDecimal("wednesdayCapacity"));
		returnVal.setThursdayStartTime(val.getTimestamp("thursdayStartTime"));
		returnVal.setThursdayCapacity(val.getBigDecimal("thursdayCapacity"));
		returnVal.setFridayStartTime(val.getTimestamp("fridayStartTime"));
		returnVal.setFridayCapacity(val.getBigDecimal("fridayCapacity"));
		returnVal.setSaturdayStartTime(val.getTimestamp("saturdayStartTime"));
		returnVal.setSaturdayCapacity(val.getBigDecimal("saturdayCapacity"));
		returnVal.setSundayStartTime(val.getTimestamp("sundayStartTime"));
		returnVal.setSundayCapacity(val.getBigDecimal("sundayCapacity"));


return returnVal;

}

public static TechDataCalendarWeek map(HttpServletRequest request) throws Exception {

		TechDataCalendarWeek returnVal = new TechDataCalendarWeek();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("calendarWeekId")) {
returnVal.setCalendarWeekId(request.getParameter("calendarWeekId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("mondayStartTime"))  {
String buf = request.getParameter("mondayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setMondayStartTime(ibuf);
}
		if(paramMap.containsKey("mondayCapacity"))  {
String buf = request.getParameter("mondayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMondayCapacity(bd);
}
		if(paramMap.containsKey("tuesdayStartTime"))  {
String buf = request.getParameter("tuesdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setTuesdayStartTime(ibuf);
}
		if(paramMap.containsKey("tuesdayCapacity"))  {
String buf = request.getParameter("tuesdayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTuesdayCapacity(bd);
}
		if(paramMap.containsKey("wednesdayStartTime"))  {
String buf = request.getParameter("wednesdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setWednesdayStartTime(ibuf);
}
		if(paramMap.containsKey("wednesdayCapacity"))  {
String buf = request.getParameter("wednesdayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWednesdayCapacity(bd);
}
		if(paramMap.containsKey("thursdayStartTime"))  {
String buf = request.getParameter("thursdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThursdayStartTime(ibuf);
}
		if(paramMap.containsKey("thursdayCapacity"))  {
String buf = request.getParameter("thursdayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setThursdayCapacity(bd);
}
		if(paramMap.containsKey("fridayStartTime"))  {
String buf = request.getParameter("fridayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFridayStartTime(ibuf);
}
		if(paramMap.containsKey("fridayCapacity"))  {
String buf = request.getParameter("fridayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFridayCapacity(bd);
}
		if(paramMap.containsKey("saturdayStartTime"))  {
String buf = request.getParameter("saturdayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setSaturdayStartTime(ibuf);
}
		if(paramMap.containsKey("saturdayCapacity"))  {
String buf = request.getParameter("saturdayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSaturdayCapacity(bd);
}
		if(paramMap.containsKey("sundayStartTime"))  {
String buf = request.getParameter("sundayStartTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setSundayStartTime(ibuf);
}
		if(paramMap.containsKey("sundayCapacity"))  {
String buf = request.getParameter("sundayCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSundayCapacity(bd);
}
return returnVal;

}
}
