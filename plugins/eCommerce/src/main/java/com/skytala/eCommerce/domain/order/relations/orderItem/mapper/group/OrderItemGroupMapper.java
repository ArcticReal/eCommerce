package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.group;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;

public class OrderItemGroupMapper  {


	public static Map<String, Object> map(OrderItemGroup orderitemgroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemgroup.getOrderId() != null ){
			returnVal.put("orderId",orderitemgroup.getOrderId());
}

		if(orderitemgroup.getOrderItemGroupSeqId() != null ){
			returnVal.put("orderItemGroupSeqId",orderitemgroup.getOrderItemGroupSeqId());
}

		if(orderitemgroup.getParentGroupSeqId() != null ){
			returnVal.put("parentGroupSeqId",orderitemgroup.getParentGroupSeqId());
}

		if(orderitemgroup.getGroupName() != null ){
			returnVal.put("groupName",orderitemgroup.getGroupName());
}

		return returnVal;
}


	public static OrderItemGroup map(Map<String, Object> fields) {

		OrderItemGroup returnVal = new OrderItemGroup();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemGroupSeqId") != null) {
			returnVal.setOrderItemGroupSeqId((String) fields.get("orderItemGroupSeqId"));
}

		if(fields.get("parentGroupSeqId") != null) {
			returnVal.setParentGroupSeqId((String) fields.get("parentGroupSeqId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}


		return returnVal;
 } 
	public static OrderItemGroup mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemGroup returnVal = new OrderItemGroup();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemGroupSeqId") != null) {
			returnVal.setOrderItemGroupSeqId((String) fields.get("orderItemGroupSeqId"));
}

		if(fields.get("parentGroupSeqId") != null) {
			returnVal.setParentGroupSeqId((String) fields.get("parentGroupSeqId"));
}

		if(fields.get("groupName") != null) {
			returnVal.setGroupName((String) fields.get("groupName"));
}


		return returnVal;
 } 
	public static OrderItemGroup map(GenericValue val) {

OrderItemGroup returnVal = new OrderItemGroup();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemGroupSeqId(val.getString("orderItemGroupSeqId"));
		returnVal.setParentGroupSeqId(val.getString("parentGroupSeqId"));
		returnVal.setGroupName(val.getString("groupName"));


return returnVal;

}

public static OrderItemGroup map(HttpServletRequest request) throws Exception {

		OrderItemGroup returnVal = new OrderItemGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemGroupSeqId"))  {
returnVal.setOrderItemGroupSeqId(request.getParameter("orderItemGroupSeqId"));
}
		if(paramMap.containsKey("parentGroupSeqId"))  {
returnVal.setParentGroupSeqId(request.getParameter("parentGroupSeqId"));
}
		if(paramMap.containsKey("groupName"))  {
returnVal.setGroupName(request.getParameter("groupName"));
}
return returnVal;

}
}
