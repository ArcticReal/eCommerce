package com.skytala.eCommerce.domain.content.relations.dataResource.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.typeAttr.DataResourceTypeAttr;

public class DataResourceTypeAttrMapper  {


	public static Map<String, Object> map(DataResourceTypeAttr dataresourcetypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourcetypeattr.getDataResourceTypeId() != null ){
			returnVal.put("dataResourceTypeId",dataresourcetypeattr.getDataResourceTypeId());
}

		if(dataresourcetypeattr.getAttrName() != null ){
			returnVal.put("attrName",dataresourcetypeattr.getAttrName());
}

		if(dataresourcetypeattr.getDescription() != null ){
			returnVal.put("description",dataresourcetypeattr.getDescription());
}

		return returnVal;
}


	public static DataResourceTypeAttr map(Map<String, Object> fields) {

		DataResourceTypeAttr returnVal = new DataResourceTypeAttr();

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DataResourceTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		DataResourceTypeAttr returnVal = new DataResourceTypeAttr();

		if(fields.get("dataResourceTypeId") != null) {
			returnVal.setDataResourceTypeId((String) fields.get("dataResourceTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DataResourceTypeAttr map(GenericValue val) {

DataResourceTypeAttr returnVal = new DataResourceTypeAttr();
		returnVal.setDataResourceTypeId(val.getString("dataResourceTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DataResourceTypeAttr map(HttpServletRequest request) throws Exception {

		DataResourceTypeAttr returnVal = new DataResourceTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceTypeId")) {
returnVal.setDataResourceTypeId(request.getParameter("dataResourceTypeId"));
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
