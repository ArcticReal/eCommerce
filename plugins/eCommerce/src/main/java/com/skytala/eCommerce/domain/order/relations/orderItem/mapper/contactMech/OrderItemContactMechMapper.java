package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.contactMech;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;

public class OrderItemContactMechMapper  {


	public static Map<String, Object> map(OrderItemContactMech orderitemcontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemcontactmech.getOrderId() != null ){
			returnVal.put("orderId",orderitemcontactmech.getOrderId());
}

		if(orderitemcontactmech.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemcontactmech.getOrderItemSeqId());
}

		if(orderitemcontactmech.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",orderitemcontactmech.getContactMechPurposeTypeId());
}

		if(orderitemcontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",orderitemcontactmech.getContactMechId());
}

		return returnVal;
}


	public static OrderItemContactMech map(Map<String, Object> fields) {

		OrderItemContactMech returnVal = new OrderItemContactMech();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static OrderItemContactMech mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemContactMech returnVal = new OrderItemContactMech();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}


		return returnVal;
 } 
	public static OrderItemContactMech map(GenericValue val) {

OrderItemContactMech returnVal = new OrderItemContactMech();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setContactMechId(val.getString("contactMechId"));


return returnVal;

}

public static OrderItemContactMech map(HttpServletRequest request) throws Exception {

		OrderItemContactMech returnVal = new OrderItemContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
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
