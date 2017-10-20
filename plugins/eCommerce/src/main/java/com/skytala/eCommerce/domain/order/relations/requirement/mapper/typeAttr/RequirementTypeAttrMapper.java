package com.skytala.eCommerce.domain.order.relations.requirement.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;

public class RequirementTypeAttrMapper  {


	public static Map<String, Object> map(RequirementTypeAttr requirementtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementtypeattr.getRequirementTypeId() != null ){
			returnVal.put("requirementTypeId",requirementtypeattr.getRequirementTypeId());
}

		if(requirementtypeattr.getAttrName() != null ){
			returnVal.put("attrName",requirementtypeattr.getAttrName());
}

		if(requirementtypeattr.getDescription() != null ){
			returnVal.put("description",requirementtypeattr.getDescription());
}

		return returnVal;
}


	public static RequirementTypeAttr map(Map<String, Object> fields) {

		RequirementTypeAttr returnVal = new RequirementTypeAttr();

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RequirementTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		RequirementTypeAttr returnVal = new RequirementTypeAttr();

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RequirementTypeAttr map(GenericValue val) {

RequirementTypeAttr returnVal = new RequirementTypeAttr();
		returnVal.setRequirementTypeId(val.getString("requirementTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RequirementTypeAttr map(HttpServletRequest request) throws Exception {

		RequirementTypeAttr returnVal = new RequirementTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementTypeId")) {
returnVal.setRequirementTypeId(request.getParameter("requirementTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
