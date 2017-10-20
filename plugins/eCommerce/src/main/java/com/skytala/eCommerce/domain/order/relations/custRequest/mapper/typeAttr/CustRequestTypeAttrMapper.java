package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.typeAttr;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;

public class CustRequestTypeAttrMapper  {


	public static Map<String, Object> map(CustRequestTypeAttr custrequesttypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequesttypeattr.getCustRequestTypeId() != null ){
			returnVal.put("custRequestTypeId",custrequesttypeattr.getCustRequestTypeId());
}

		if(custrequesttypeattr.getAttrName() != null ){
			returnVal.put("attrName",custrequesttypeattr.getAttrName());
}

		if(custrequesttypeattr.getDescription() != null ){
			returnVal.put("description",custrequesttypeattr.getDescription());
}

		return returnVal;
}


	public static CustRequestTypeAttr map(Map<String, Object> fields) {

		CustRequestTypeAttr returnVal = new CustRequestTypeAttr();

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestTypeAttr returnVal = new CustRequestTypeAttr();

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestTypeAttr map(GenericValue val) {

CustRequestTypeAttr returnVal = new CustRequestTypeAttr();
		returnVal.setCustRequestTypeId(val.getString("custRequestTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CustRequestTypeAttr map(HttpServletRequest request) throws Exception {

		CustRequestTypeAttr returnVal = new CustRequestTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestTypeId")) {
returnVal.setCustRequestTypeId(request.getParameter("custRequestTypeId"));
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
