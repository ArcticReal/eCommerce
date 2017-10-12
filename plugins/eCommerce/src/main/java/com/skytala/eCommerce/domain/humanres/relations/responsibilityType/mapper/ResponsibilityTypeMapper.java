package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;

public class ResponsibilityTypeMapper  {


	public static Map<String, Object> map(ResponsibilityType responsibilitytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(responsibilitytype.getResponsibilityTypeId() != null ){
			returnVal.put("responsibilityTypeId",responsibilitytype.getResponsibilityTypeId());
}

		if(responsibilitytype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",responsibilitytype.getParentTypeId());
}

		if(responsibilitytype.getHasTable() != null ){
			returnVal.put("hasTable",responsibilitytype.getHasTable());
}

		if(responsibilitytype.getDescription() != null ){
			returnVal.put("description",responsibilitytype.getDescription());
}

		return returnVal;
}


	public static ResponsibilityType map(Map<String, Object> fields) {

		ResponsibilityType returnVal = new ResponsibilityType();

		if(fields.get("responsibilityTypeId") != null) {
			returnVal.setResponsibilityTypeId((String) fields.get("responsibilityTypeId"));
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
	public static ResponsibilityType mapstrstr(Map<String, String> fields) throws Exception {

		ResponsibilityType returnVal = new ResponsibilityType();

		if(fields.get("responsibilityTypeId") != null) {
			returnVal.setResponsibilityTypeId((String) fields.get("responsibilityTypeId"));
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
	public static ResponsibilityType map(GenericValue val) {

ResponsibilityType returnVal = new ResponsibilityType();
		returnVal.setResponsibilityTypeId(val.getString("responsibilityTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ResponsibilityType map(HttpServletRequest request) throws Exception {

		ResponsibilityType returnVal = new ResponsibilityType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("responsibilityTypeId")) {
returnVal.setResponsibilityTypeId(request.getParameter("responsibilityTypeId"));
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
