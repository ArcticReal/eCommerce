package com.skytala.eCommerce.domain.order.relations.requirement.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;

public class RequirementAttributeMapper  {


	public static Map<String, Object> map(RequirementAttribute requirementattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementattribute.getRequirementId() != null ){
			returnVal.put("requirementId",requirementattribute.getRequirementId());
}

		if(requirementattribute.getAttrName() != null ){
			returnVal.put("attrName",requirementattribute.getAttrName());
}

		if(requirementattribute.getAttrValue() != null ){
			returnVal.put("attrValue",requirementattribute.getAttrValue());
}

		if(requirementattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",requirementattribute.getAttrDescription());
}

		return returnVal;
}


	public static RequirementAttribute map(Map<String, Object> fields) {

		RequirementAttribute returnVal = new RequirementAttribute();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static RequirementAttribute mapstrstr(Map<String, String> fields) throws Exception {

		RequirementAttribute returnVal = new RequirementAttribute();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static RequirementAttribute map(GenericValue val) {

RequirementAttribute returnVal = new RequirementAttribute();
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static RequirementAttribute map(HttpServletRequest request) throws Exception {

		RequirementAttribute returnVal = new RequirementAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementId")) {
returnVal.setRequirementId(request.getParameter("requirementId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
returnVal.setAttrValue(request.getParameter("attrValue"));
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
