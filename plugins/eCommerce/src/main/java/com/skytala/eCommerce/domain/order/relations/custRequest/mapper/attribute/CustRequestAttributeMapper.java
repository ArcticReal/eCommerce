package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;

public class CustRequestAttributeMapper  {


	public static Map<String, Object> map(CustRequestAttribute custrequestattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestattribute.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestattribute.getCustRequestId());
}

		if(custrequestattribute.getAttrName() != null ){
			returnVal.put("attrName",custrequestattribute.getAttrName());
}

		if(custrequestattribute.getAttrValue() != null ){
			returnVal.put("attrValue",custrequestattribute.getAttrValue());
}

		if(custrequestattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",custrequestattribute.getAttrDescription());
}

		return returnVal;
}


	public static CustRequestAttribute map(Map<String, Object> fields) {

		CustRequestAttribute returnVal = new CustRequestAttribute();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
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
	public static CustRequestAttribute mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestAttribute returnVal = new CustRequestAttribute();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
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
	public static CustRequestAttribute map(GenericValue val) {

CustRequestAttribute returnVal = new CustRequestAttribute();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static CustRequestAttribute map(HttpServletRequest request) throws Exception {

		CustRequestAttribute returnVal = new CustRequestAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
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
