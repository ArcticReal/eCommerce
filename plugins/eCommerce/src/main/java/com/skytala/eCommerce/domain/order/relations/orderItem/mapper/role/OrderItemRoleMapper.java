package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.role.OrderItemRole;

public class OrderItemRoleMapper  {


	public static Map<String, Object> map(OrderItemRole orderitemrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemrole.getOrderId() != null ){
			returnVal.put("orderId",orderitemrole.getOrderId());
}

		if(orderitemrole.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemrole.getOrderItemSeqId());
}

		if(orderitemrole.getPartyId() != null ){
			returnVal.put("partyId",orderitemrole.getPartyId());
}

		if(orderitemrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",orderitemrole.getRoleTypeId());
}

		return returnVal;
}


	public static OrderItemRole map(Map<String, Object> fields) {

		OrderItemRole returnVal = new OrderItemRole();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static OrderItemRole mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemRole returnVal = new OrderItemRole();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static OrderItemRole map(GenericValue val) {

OrderItemRole returnVal = new OrderItemRole();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static OrderItemRole map(HttpServletRequest request) throws Exception {

		OrderItemRole returnVal = new OrderItemRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
