package com.skytala.eCommerce.domain.order.relations.orderRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;

public class OrderRoleMapper  {


	public static Map<String, Object> map(OrderRole orderrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderrole.getOrderId() != null ){
			returnVal.put("orderId",orderrole.getOrderId());
}

		if(orderrole.getPartyId() != null ){
			returnVal.put("partyId",orderrole.getPartyId());
}

		if(orderrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",orderrole.getRoleTypeId());
}

		return returnVal;
}


	public static OrderRole map(Map<String, Object> fields) {

		OrderRole returnVal = new OrderRole();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static OrderRole mapstrstr(Map<String, String> fields) throws Exception {

		OrderRole returnVal = new OrderRole();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static OrderRole map(GenericValue val) {

OrderRole returnVal = new OrderRole();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static OrderRole map(HttpServletRequest request) throws Exception {

		OrderRole returnVal = new OrderRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
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
