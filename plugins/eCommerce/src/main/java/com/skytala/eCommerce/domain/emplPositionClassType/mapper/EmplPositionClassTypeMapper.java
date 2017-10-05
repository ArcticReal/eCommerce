package com.skytala.eCommerce.domain.emplPositionClassType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.emplPositionClassType.model.EmplPositionClassType;

public class EmplPositionClassTypeMapper  {


	public static Map<String, Object> map(EmplPositionClassType emplpositionclasstype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositionclasstype.getEmplPositionClassTypeId() != null ){
			returnVal.put("emplPositionClassTypeId",emplpositionclasstype.getEmplPositionClassTypeId());
}

		if(emplpositionclasstype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",emplpositionclasstype.getParentTypeId());
}

		if(emplpositionclasstype.getHasTable() != null ){
			returnVal.put("hasTable",emplpositionclasstype.getHasTable());
}

		if(emplpositionclasstype.getDescription() != null ){
			returnVal.put("description",emplpositionclasstype.getDescription());
}

		return returnVal;
}


	public static EmplPositionClassType map(Map<String, Object> fields) {

		EmplPositionClassType returnVal = new EmplPositionClassType();

		if(fields.get("emplPositionClassTypeId") != null) {
			returnVal.setEmplPositionClassTypeId((String) fields.get("emplPositionClassTypeId"));
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
	public static EmplPositionClassType mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionClassType returnVal = new EmplPositionClassType();

		if(fields.get("emplPositionClassTypeId") != null) {
			returnVal.setEmplPositionClassTypeId((String) fields.get("emplPositionClassTypeId"));
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
	public static EmplPositionClassType map(GenericValue val) {

EmplPositionClassType returnVal = new EmplPositionClassType();
		returnVal.setEmplPositionClassTypeId(val.getString("emplPositionClassTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static EmplPositionClassType map(HttpServletRequest request) throws Exception {

		EmplPositionClassType returnVal = new EmplPositionClassType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionClassTypeId")) {
returnVal.setEmplPositionClassTypeId(request.getParameter("emplPositionClassTypeId"));
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
