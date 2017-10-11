package com.skytala.eCommerce.domain.order.relations.returnContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnContactMech.model.ReturnContactMech;

public class ReturnContactMechMapper  {


	public static Map<String, Object> map(ReturnContactMech returncontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returncontactmech.getReturnId() != null ){
			returnVal.put("returnId",returncontactmech.getReturnId());
}

		if(returncontactmech.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",returncontactmech.getContactMechPurposeTypeId());
}

		if(returncontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",returncontactmech.getContactMechId());
}

		return returnVal;
}


	public static ReturnContactMech map(Map<String, Object> fields) {

		ReturnContactMech returnVal = new ReturnContactMech();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static ReturnContactMech mapstrstr(Map<String, String> fields) throws Exception {

		ReturnContactMech returnVal = new ReturnContactMech();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static ReturnContactMech map(GenericValue val) {

ReturnContactMech returnVal = new ReturnContactMech();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static ReturnContactMech map(HttpServletRequest request) throws Exception {

		ReturnContactMech returnVal = new ReturnContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("contactMechPurposeTypeId"))  {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
}
		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
return returnVal;

}
}
