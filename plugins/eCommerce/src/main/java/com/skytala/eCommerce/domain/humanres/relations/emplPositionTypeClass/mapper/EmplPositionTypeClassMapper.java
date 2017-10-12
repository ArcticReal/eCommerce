package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;

public class EmplPositionTypeClassMapper  {


	public static Map<String, Object> map(EmplPositionTypeClass emplpositiontypeclass) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositiontypeclass.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",emplpositiontypeclass.getEmplPositionTypeId());
}

		if(emplpositiontypeclass.getEmplPositionClassTypeId() != null ){
			returnVal.put("emplPositionClassTypeId",emplpositiontypeclass.getEmplPositionClassTypeId());
}

		if(emplpositiontypeclass.getFromDate() != null ){
			returnVal.put("fromDate",emplpositiontypeclass.getFromDate());
}

		if(emplpositiontypeclass.getThruDate() != null ){
			returnVal.put("thruDate",emplpositiontypeclass.getThruDate());
}

		if(emplpositiontypeclass.getStandardHoursPerWeek() != null ){
			returnVal.put("standardHoursPerWeek",emplpositiontypeclass.getStandardHoursPerWeek());
}

		return returnVal;
}


	public static EmplPositionTypeClass map(Map<String, Object> fields) {

		EmplPositionTypeClass returnVal = new EmplPositionTypeClass();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("emplPositionClassTypeId") != null) {
			returnVal.setEmplPositionClassTypeId((String) fields.get("emplPositionClassTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("standardHoursPerWeek") != null) {
			returnVal.setStandardHoursPerWeek((BigDecimal) fields.get("standardHoursPerWeek"));
}


		return returnVal;
 } 
	public static EmplPositionTypeClass mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionTypeClass returnVal = new EmplPositionTypeClass();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
}

		if(fields.get("emplPositionClassTypeId") != null) {
			returnVal.setEmplPositionClassTypeId((String) fields.get("emplPositionClassTypeId"));
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

		if(fields.get("standardHoursPerWeek") != null) {
String buf;
buf = fields.get("standardHoursPerWeek");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStandardHoursPerWeek(bd);
}


		return returnVal;
 } 
	public static EmplPositionTypeClass map(GenericValue val) {

EmplPositionTypeClass returnVal = new EmplPositionTypeClass();
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setEmplPositionClassTypeId(val.getString("emplPositionClassTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStandardHoursPerWeek(val.getBigDecimal("standardHoursPerWeek"));


return returnVal;

}

public static EmplPositionTypeClass map(HttpServletRequest request) throws Exception {

		EmplPositionTypeClass returnVal = new EmplPositionTypeClass();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionTypeId")) {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
}

		if(paramMap.containsKey("emplPositionClassTypeId"))  {
returnVal.setEmplPositionClassTypeId(request.getParameter("emplPositionClassTypeId"));
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
		if(paramMap.containsKey("standardHoursPerWeek"))  {
String buf = request.getParameter("standardHoursPerWeek");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStandardHoursPerWeek(bd);
}
return returnVal;

}
}
