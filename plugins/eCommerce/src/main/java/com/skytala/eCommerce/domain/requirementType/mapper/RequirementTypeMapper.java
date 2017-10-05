package com.skytala.eCommerce.domain.requirementType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.requirementType.model.RequirementType;

public class RequirementTypeMapper  {


	public static Map<String, Object> map(RequirementType requirementtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementtype.getRequirementTypeId() != null ){
			returnVal.put("requirementTypeId",requirementtype.getRequirementTypeId());
}

		if(requirementtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",requirementtype.getParentTypeId());
}

		if(requirementtype.getHasTable() != null ){
			returnVal.put("hasTable",requirementtype.getHasTable());
}

		if(requirementtype.getDescription() != null ){
			returnVal.put("description",requirementtype.getDescription());
}

		return returnVal;
}


	public static RequirementType map(Map<String, Object> fields) {

		RequirementType returnVal = new RequirementType();

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
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
	public static RequirementType mapstrstr(Map<String, String> fields) throws Exception {

		RequirementType returnVal = new RequirementType();

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
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
	public static RequirementType map(GenericValue val) {

RequirementType returnVal = new RequirementType();
		returnVal.setRequirementTypeId(val.getString("requirementTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RequirementType map(HttpServletRequest request) throws Exception {

		RequirementType returnVal = new RequirementType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementTypeId")) {
returnVal.setRequirementTypeId(request.getParameter("requirementTypeId"));
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
