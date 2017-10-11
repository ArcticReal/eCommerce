package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;

public class SubscriptionAttributeMapper  {


	public static Map<String, Object> map(SubscriptionAttribute subscriptionattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptionattribute.getSubscriptionId() != null ){
			returnVal.put("subscriptionId",subscriptionattribute.getSubscriptionId());
}

		if(subscriptionattribute.getAttrName() != null ){
			returnVal.put("attrName",subscriptionattribute.getAttrName());
}

		if(subscriptionattribute.getAttrValue() != null ){
			returnVal.put("attrValue",subscriptionattribute.getAttrValue());
}

		if(subscriptionattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",subscriptionattribute.getAttrDescription());
}

		return returnVal;
}


	public static SubscriptionAttribute map(Map<String, Object> fields) {

		SubscriptionAttribute returnVal = new SubscriptionAttribute();

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
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
	public static SubscriptionAttribute mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionAttribute returnVal = new SubscriptionAttribute();

		if(fields.get("subscriptionId") != null) {
			returnVal.setSubscriptionId((String) fields.get("subscriptionId"));
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
	public static SubscriptionAttribute map(GenericValue val) {

SubscriptionAttribute returnVal = new SubscriptionAttribute();
		returnVal.setSubscriptionId(val.getString("subscriptionId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static SubscriptionAttribute map(HttpServletRequest request) throws Exception {

		SubscriptionAttribute returnVal = new SubscriptionAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionId")) {
returnVal.setSubscriptionId(request.getParameter("subscriptionId"));
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
