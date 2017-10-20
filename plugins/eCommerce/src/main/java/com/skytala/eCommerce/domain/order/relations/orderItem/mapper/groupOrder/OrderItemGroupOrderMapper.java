package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.groupOrder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.groupOrder.OrderItemGroupOrder;

public class OrderItemGroupOrderMapper  {


	public static Map<String, Object> map(OrderItemGroupOrder orderitemgrouporder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemgrouporder.getOrderId() != null ){
			returnVal.put("orderId",orderitemgrouporder.getOrderId());
}

		if(orderitemgrouporder.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemgrouporder.getOrderItemSeqId());
}

		if(orderitemgrouporder.getGroupOrderId() != null ){
			returnVal.put("groupOrderId",orderitemgrouporder.getGroupOrderId());
}

		return returnVal;
}


	public static OrderItemGroupOrder map(Map<String, Object> fields) {

		OrderItemGroupOrder returnVal = new OrderItemGroupOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("groupOrderId") != null) {
			returnVal.setGroupOrderId((String) fields.get("groupOrderId"));
}


		return returnVal;
 } 
	public static OrderItemGroupOrder mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemGroupOrder returnVal = new OrderItemGroupOrder();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("groupOrderId") != null) {
			returnVal.setGroupOrderId((String) fields.get("groupOrderId"));
}


		return returnVal;
 } 
	public static OrderItemGroupOrder map(GenericValue val) {

OrderItemGroupOrder returnVal = new OrderItemGroupOrder();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setGroupOrderId(val.getString("groupOrderId"));


return returnVal;

}

public static OrderItemGroupOrder map(HttpServletRequest request) throws Exception {

		OrderItemGroupOrder returnVal = new OrderItemGroupOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("groupOrderId"))  {
returnVal.setGroupOrderId(request.getParameter("groupOrderId"));
}
return returnVal;

}
}
