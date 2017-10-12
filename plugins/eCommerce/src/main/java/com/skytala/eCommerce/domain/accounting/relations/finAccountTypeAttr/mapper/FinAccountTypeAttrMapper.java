package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeAttr.model.FinAccountTypeAttr;

public class FinAccountTypeAttrMapper  {


	public static Map<String, Object> map(FinAccountTypeAttr finaccounttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttypeattr.getFinAccountTypeId() != null ){
			returnVal.put("finAccountTypeId",finaccounttypeattr.getFinAccountTypeId());
}

		if(finaccounttypeattr.getAttrName() != null ){
			returnVal.put("attrName",finaccounttypeattr.getAttrName());
}

		if(finaccounttypeattr.getAttrValue() != null ){
			returnVal.put("attrValue",finaccounttypeattr.getAttrValue());
}

		if(finaccounttypeattr.getDescription() != null ){
			returnVal.put("description",finaccounttypeattr.getDescription());
}

		return returnVal;
}


	public static FinAccountTypeAttr map(Map<String, Object> fields) {

		FinAccountTypeAttr returnVal = new FinAccountTypeAttr();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FinAccountTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTypeAttr returnVal = new FinAccountTypeAttr();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((String) fields.get("attrValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FinAccountTypeAttr map(GenericValue val) {

FinAccountTypeAttr returnVal = new FinAccountTypeAttr();
		returnVal.setFinAccountTypeId(val.getString("finAccountTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getString("attrValue"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FinAccountTypeAttr map(HttpServletRequest request) throws Exception {

		FinAccountTypeAttr returnVal = new FinAccountTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTypeId")) {
returnVal.setFinAccountTypeId(request.getParameter("finAccountTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
returnVal.setAttrValue(request.getParameter("attrValue"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
