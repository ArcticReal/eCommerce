package com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;

public class EmplPositionTypeMapper  {


	public static Map<String, Object> map(EmplPositionType emplpositiontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositiontype.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",emplpositiontype.getEmplPositionTypeId());
}

		if(emplpositiontype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",emplpositiontype.getParentTypeId());
}

		if(emplpositiontype.getHasTable() != null ){
			returnVal.put("hasTable",emplpositiontype.getHasTable());
}

		if(emplpositiontype.getDescription() != null ){
			returnVal.put("description",emplpositiontype.getDescription());
}

		return returnVal;
}


	public static EmplPositionType map(Map<String, Object> fields) {

		EmplPositionType returnVal = new EmplPositionType();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
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
	public static EmplPositionType mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionType returnVal = new EmplPositionType();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
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
	public static EmplPositionType map(GenericValue val) {

EmplPositionType returnVal = new EmplPositionType();
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmplPositionType map(HttpServletRequest request) throws Exception {

		EmplPositionType returnVal = new EmplPositionType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionTypeId")) {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
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
