package com.skytala.eCommerce.domain.content.relations.contentTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.model.ContentTypeAttr;

public class ContentTypeAttrMapper  {


	public static Map<String, Object> map(ContentTypeAttr contenttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contenttypeattr.getContentTypeId() != null ){
			returnVal.put("contentTypeId",contenttypeattr.getContentTypeId());
}

		if(contenttypeattr.getAttrName() != null ){
			returnVal.put("attrName",contenttypeattr.getAttrName());
}

		if(contenttypeattr.getDescription() != null ){
			returnVal.put("description",contenttypeattr.getDescription());
}

		return returnVal;
}


	public static ContentTypeAttr map(Map<String, Object> fields) {

		ContentTypeAttr returnVal = new ContentTypeAttr();

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		ContentTypeAttr returnVal = new ContentTypeAttr();

		if(fields.get("contentTypeId") != null) {
			returnVal.setContentTypeId((String) fields.get("contentTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentTypeAttr map(GenericValue val) {

ContentTypeAttr returnVal = new ContentTypeAttr();
		returnVal.setContentTypeId(val.getString("contentTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentTypeAttr map(HttpServletRequest request) throws Exception {

		ContentTypeAttr returnVal = new ContentTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentTypeId")) {
returnVal.setContentTypeId(request.getParameter("contentTypeId"));
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
