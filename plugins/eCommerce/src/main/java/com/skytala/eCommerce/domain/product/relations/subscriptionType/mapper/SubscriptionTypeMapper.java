package com.skytala.eCommerce.domain.product.relations.subscriptionType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionType.model.SubscriptionType;

public class SubscriptionTypeMapper  {


	public static Map<String, Object> map(SubscriptionType subscriptiontype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(subscriptiontype.getSubscriptionTypeId() != null ){
			returnVal.put("subscriptionTypeId",subscriptiontype.getSubscriptionTypeId());
}

		if(subscriptiontype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",subscriptiontype.getParentTypeId());
}

		if(subscriptiontype.getHasTable() != null ){
			returnVal.put("hasTable",subscriptiontype.getHasTable());
}

		if(subscriptiontype.getDescription() != null ){
			returnVal.put("description",subscriptiontype.getDescription());
}

		return returnVal;
}


	public static SubscriptionType map(Map<String, Object> fields) {

		SubscriptionType returnVal = new SubscriptionType();

		if(fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SubscriptionType mapstrstr(Map<String, String> fields) throws Exception {

		SubscriptionType returnVal = new SubscriptionType();

		if(fields.get("subscriptionTypeId") != null) {
			returnVal.setSubscriptionTypeId((String) fields.get("subscriptionTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static SubscriptionType map(GenericValue val) {

SubscriptionType returnVal = new SubscriptionType();
		returnVal.setSubscriptionTypeId(val.getString("subscriptionTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static SubscriptionType map(HttpServletRequest request) throws Exception {

		SubscriptionType returnVal = new SubscriptionType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("subscriptionTypeId")) {
returnVal.setSubscriptionTypeId(request.getParameter("subscriptionTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
