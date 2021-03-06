package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transAttribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transAttribute.FinAccountTransAttribute;

public class FinAccountTransAttributeMapper  {


	public static Map<String, Object> map(FinAccountTransAttribute finaccounttransattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttransattribute.getFinAccountTransId() != null ){
			returnVal.put("finAccountTransId",finaccounttransattribute.getFinAccountTransId());
}

		if(finaccounttransattribute.getAttrName() != null ){
			returnVal.put("attrName",finaccounttransattribute.getAttrName());
}

		if(finaccounttransattribute.getAttrValue() != null ){
			returnVal.put("attrValue",finaccounttransattribute.getAttrValue());
}

		if(finaccounttransattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",finaccounttransattribute.getAttrDescription());
}

		return returnVal;
}


	public static FinAccountTransAttribute map(Map<String, Object> fields) {

		FinAccountTransAttribute returnVal = new FinAccountTransAttribute();

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
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
	public static FinAccountTransAttribute mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTransAttribute returnVal = new FinAccountTransAttribute();

		if(fields.get("finAccountTransId") != null) {
			returnVal.setFinAccountTransId((String) fields.get("finAccountTransId"));
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
	public static FinAccountTransAttribute map(GenericValue val) {

FinAccountTransAttribute returnVal = new FinAccountTransAttribute();
		returnVal.setFinAccountTransId(val.getString("finAccountTransId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static FinAccountTransAttribute map(HttpServletRequest request) throws Exception {

		FinAccountTransAttribute returnVal = new FinAccountTransAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTransId")) {
returnVal.setFinAccountTransId(request.getParameter("finAccountTransId"));
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
