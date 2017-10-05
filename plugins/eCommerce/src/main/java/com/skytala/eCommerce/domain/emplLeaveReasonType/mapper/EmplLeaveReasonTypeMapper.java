package com.skytala.eCommerce.domain.emplLeaveReasonType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.emplLeaveReasonType.model.EmplLeaveReasonType;

public class EmplLeaveReasonTypeMapper  {


	public static Map<String, Object> map(EmplLeaveReasonType emplleavereasontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplleavereasontype.getEmplLeaveReasonTypeId() != null ){
			returnVal.put("emplLeaveReasonTypeId",emplleavereasontype.getEmplLeaveReasonTypeId());
}

		if(emplleavereasontype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",emplleavereasontype.getParentTypeId());
}

		if(emplleavereasontype.getHasTable() != null ){
			returnVal.put("hasTable",emplleavereasontype.getHasTable());
}

		if(emplleavereasontype.getDescription() != null ){
			returnVal.put("description",emplleavereasontype.getDescription());
}

		return returnVal;
}


	public static EmplLeaveReasonType map(Map<String, Object> fields) {

		EmplLeaveReasonType returnVal = new EmplLeaveReasonType();

		if(fields.get("emplLeaveReasonTypeId") != null) {
			returnVal.setEmplLeaveReasonTypeId((String) fields.get("emplLeaveReasonTypeId"));
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
	public static EmplLeaveReasonType mapstrstr(Map<String, String> fields) throws Exception {

		EmplLeaveReasonType returnVal = new EmplLeaveReasonType();

		if(fields.get("emplLeaveReasonTypeId") != null) {
			returnVal.setEmplLeaveReasonTypeId((String) fields.get("emplLeaveReasonTypeId"));
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
	public static EmplLeaveReasonType map(GenericValue val) {

EmplLeaveReasonType returnVal = new EmplLeaveReasonType();
		returnVal.setEmplLeaveReasonTypeId(val.getString("emplLeaveReasonTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmplLeaveReasonType map(HttpServletRequest request) throws Exception {

		EmplLeaveReasonType returnVal = new EmplLeaveReasonType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplLeaveReasonTypeId")) {
returnVal.setEmplLeaveReasonTypeId(request.getParameter("emplLeaveReasonTypeId"));
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
