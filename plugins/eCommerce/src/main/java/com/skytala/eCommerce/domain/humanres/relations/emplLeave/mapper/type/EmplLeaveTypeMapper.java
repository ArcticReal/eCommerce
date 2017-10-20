package com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.type.EmplLeaveType;

public class EmplLeaveTypeMapper  {


	public static Map<String, Object> map(EmplLeaveType emplleavetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplleavetype.getLeaveTypeId() != null ){
			returnVal.put("leaveTypeId",emplleavetype.getLeaveTypeId());
}

		if(emplleavetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",emplleavetype.getParentTypeId());
}

		if(emplleavetype.getHasTable() != null ){
			returnVal.put("hasTable",emplleavetype.getHasTable());
}

		if(emplleavetype.getDescription() != null ){
			returnVal.put("description",emplleavetype.getDescription());
}

		return returnVal;
}


	public static EmplLeaveType map(Map<String, Object> fields) {

		EmplLeaveType returnVal = new EmplLeaveType();

		if(fields.get("leaveTypeId") != null) {
			returnVal.setLeaveTypeId((String) fields.get("leaveTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static EmplLeaveType mapstrstr(Map<String, String> fields) throws Exception {

		EmplLeaveType returnVal = new EmplLeaveType();

		if(fields.get("leaveTypeId") != null) {
			returnVal.setLeaveTypeId((String) fields.get("leaveTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static EmplLeaveType map(GenericValue val) {

EmplLeaveType returnVal = new EmplLeaveType();
		returnVal.setLeaveTypeId(val.getString("leaveTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmplLeaveType map(HttpServletRequest request) throws Exception {

		EmplLeaveType returnVal = new EmplLeaveType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("leaveTypeId")) {
returnVal.setLeaveTypeId(request.getParameter("leaveTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
