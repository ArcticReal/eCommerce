package com.skytala.eCommerce.domain.content.relations.contentAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;

public class ContentAttributeMapper  {


	public static Map<String, Object> map(ContentAttribute contentattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentattribute.getContentId() != null ){
			returnVal.put("contentId",contentattribute.getContentId());
}

		if(contentattribute.getAttrName() != null ){
			returnVal.put("attrName",contentattribute.getAttrName());
}

		if(contentattribute.getAttrValue() != null ){
			returnVal.put("attrValue",contentattribute.getAttrValue());
}

		if(contentattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",contentattribute.getAttrDescription());
}

		return returnVal;
}


	public static ContentAttribute map(Map<String, Object> fields) {

		ContentAttribute returnVal = new ContentAttribute();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ContentAttribute mapstrstr(Map<String, String> fields) throws Exception {

		ContentAttribute returnVal = new ContentAttribute();

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static ContentAttribute map(GenericValue val) {

ContentAttribute returnVal = new ContentAttribute();
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static ContentAttribute map(HttpServletRequest request) throws Exception {

		ContentAttribute returnVal = new ContentAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentId")) {
returnVal.setContentId(request.getParameter("contentId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
