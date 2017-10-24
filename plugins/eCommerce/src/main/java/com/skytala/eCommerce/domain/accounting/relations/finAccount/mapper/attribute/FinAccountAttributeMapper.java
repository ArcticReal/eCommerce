package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.attribute.FinAccountAttribute;

public class FinAccountAttributeMapper  {


	public static Map<String, Object> map(FinAccountAttribute finaccountattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccountattribute.getFinAccountId() != null ){
			returnVal.put("finAccountId",finaccountattribute.getFinAccountId());
}

		if(finaccountattribute.getAttrName() != null ){
			returnVal.put("attrName",finaccountattribute.getAttrName());
}

		if(finaccountattribute.getAttrValue() != null ){
			returnVal.put("attrValue",finaccountattribute.getAttrValue());
}

		if(finaccountattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",finaccountattribute.getAttrDescription());
}

		return returnVal;
}


	public static FinAccountAttribute map(Map<String, Object> fields) {

		FinAccountAttribute returnVal = new FinAccountAttribute();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
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
	public static FinAccountAttribute mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountAttribute returnVal = new FinAccountAttribute();

		if(fields.get("finAccountId") != null) {
			returnVal.setFinAccountId((String) fields.get("finAccountId"));
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
	public static FinAccountAttribute map(GenericValue val) {

FinAccountAttribute returnVal = new FinAccountAttribute();
		returnVal.setFinAccountId(val.getString("finAccountId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static FinAccountAttribute map(HttpServletRequest request) throws Exception {

		FinAccountAttribute returnVal = new FinAccountAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountId")) {
returnVal.setFinAccountId(request.getParameter("finAccountId"));
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
