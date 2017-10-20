package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.shipGroupAssoc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;

public class OrderItemShipGroupAssocMapper  {


	public static Map<String, Object> map(OrderItemShipGroupAssoc orderitemshipgroupassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemshipgroupassoc.getOrderId() != null ){
			returnVal.put("orderId",orderitemshipgroupassoc.getOrderId());
}

		if(orderitemshipgroupassoc.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemshipgroupassoc.getOrderItemSeqId());
}

		if(orderitemshipgroupassoc.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderitemshipgroupassoc.getShipGroupSeqId());
}

		if(orderitemshipgroupassoc.getQuantity() != null ){
			returnVal.put("quantity",orderitemshipgroupassoc.getQuantity());
}

		if(orderitemshipgroupassoc.getCancelQuantity() != null ){
			returnVal.put("cancelQuantity",orderitemshipgroupassoc.getCancelQuantity());
}

		return returnVal;
}


	public static OrderItemShipGroupAssoc map(Map<String, Object> fields) {

		OrderItemShipGroupAssoc returnVal = new OrderItemShipGroupAssoc();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("cancelQuantity") != null) {
			returnVal.setCancelQuantity((BigDecimal) fields.get("cancelQuantity"));
}


		return returnVal;
 } 
	public static OrderItemShipGroupAssoc mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemShipGroupAssoc returnVal = new OrderItemShipGroupAssoc();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("cancelQuantity") != null) {
String buf;
buf = fields.get("cancelQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
}


		return returnVal;
 } 
	public static OrderItemShipGroupAssoc map(GenericValue val) {

OrderItemShipGroupAssoc returnVal = new OrderItemShipGroupAssoc();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setCancelQuantity(val.getBigDecimal("cancelQuantity"));


return returnVal;

}

public static OrderItemShipGroupAssoc map(HttpServletRequest request) throws Exception {

		OrderItemShipGroupAssoc returnVal = new OrderItemShipGroupAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("cancelQuantity"))  {
String buf = request.getParameter("cancelQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setCancelQuantity(bd);
}
return returnVal;

}
}
