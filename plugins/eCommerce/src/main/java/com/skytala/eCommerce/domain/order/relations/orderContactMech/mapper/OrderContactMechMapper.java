package com.skytala.eCommerce.domain.order.relations.orderContactMech.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;

public class OrderContactMechMapper  {


	public static Map<String, Object> map(OrderContactMech ordercontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordercontactmech.getOrderId() != null ){
			returnVal.put("orderId",ordercontactmech.getOrderId());
}

		if(ordercontactmech.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",ordercontactmech.getContactMechPurposeTypeId());
}

		if(ordercontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",ordercontactmech.getContactMechId());
}

		return returnVal;
}


	public static OrderContactMech map(Map<String, Object> fields) {

		OrderContactMech returnVal = new OrderContactMech();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static OrderContactMech mapstrstr(Map<String, String> fields) throws Exception {

		OrderContactMech returnVal = new OrderContactMech();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static OrderContactMech map(GenericValue val) {

OrderContactMech returnVal = new OrderContactMech();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static OrderContactMech map(HttpServletRequest request) throws Exception {

		OrderContactMech returnVal = new OrderContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
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
