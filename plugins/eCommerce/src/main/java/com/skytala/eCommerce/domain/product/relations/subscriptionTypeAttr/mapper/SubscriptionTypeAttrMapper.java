package com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.model.SubscriptionTypeAttr;

public class SubscriptionTypeAttrMapper  {


	public static Map<String, Object> map(SubscriptionTypeAttr subscriptiontypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptiontypeattr.getSubscriptionTypeId() != null ){
			returnVal.put("subscriptionTypeId",subscriptiontypeattr.getSubscriptionTypeId());
}

		if(subscriptiontypeattr.getAttrName() != null ){
			returnVal.put("attrName",subscriptiontypeattr.getAttrName());
}

		if(subscriptiontypeattr.getDescription() != null ){
			returnVal.put("description",subscriptiontypeattr.getDescription());
}

		return returnVal;
}


	public static SubscriptionTypeAttr map(Map<String, Object> fields) {

		SubscriptionTypeAttr returnVal = new SubscriptionTypeAttr();

		if(fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SubscriptionTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionTypeAttr returnVal = new SubscriptionTypeAttr();

		if(fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SubscriptionTypeAttr map(GenericValue val) {

SubscriptionTypeAttr returnVal = new SubscriptionTypeAttr();
		returnVal.setSubscriptionTypeId(val.getString("subscriptionTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SubscriptionTypeAttr map(HttpServletRequest request) throws Exception {

		SubscriptionTypeAttr returnVal = new SubscriptionTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionTypeId")) {
returnVal.setSubscriptionTypeId(request.getParameter("subscriptionTypeId"));
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
