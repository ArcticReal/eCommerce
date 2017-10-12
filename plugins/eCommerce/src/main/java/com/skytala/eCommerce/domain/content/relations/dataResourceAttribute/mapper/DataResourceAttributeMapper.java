package com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.model.DataResourceAttribute;

public class DataResourceAttributeMapper  {


	public static Map<String, Object> map(DataResourceAttribute dataresourceattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(dataresourceattribute.getDataResourceId() != null ){
			returnVal.put("dataResourceId",dataresourceattribute.getDataResourceId());
}

		if(dataresourceattribute.getAttrName() != null ){
			returnVal.put("attrName",dataresourceattribute.getAttrName());
}

		if(dataresourceattribute.getAttrValue() != null ){
			returnVal.put("attrValue",dataresourceattribute.getAttrValue());
}

		if(dataresourceattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",dataresourceattribute.getAttrDescription());
}

		return returnVal;
}


	public static DataResourceAttribute map(Map<String, Object> fields) {

		DataResourceAttribute returnVal = new DataResourceAttribute();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
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
	public static DataResourceAttribute mapstrstr(Map<String, String> fields) throws Exception {

		DataResourceAttribute returnVal = new DataResourceAttribute();

		if(fields.get("dataResourceId") != null) {
			returnVal.setDataResourceId((String) fields.get("dataResourceId"));
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
	public static DataResourceAttribute map(GenericValue val) {

DataResourceAttribute returnVal = new DataResourceAttribute();
		returnVal.setDataResourceId(val.getString("dataResourceId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static DataResourceAttribute map(HttpServletRequest request) throws Exception {

		DataResourceAttribute returnVal = new DataResourceAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("dataResourceId")) {
returnVal.setDataResourceId(request.getParameter("dataResourceId"));
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
