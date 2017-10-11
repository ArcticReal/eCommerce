package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;

public class OrderItemAssocMapper  {


	public static Map<String, Object> map(OrderItemAssoc orderitemassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemassoc.getOrderId() != null ){
			returnVal.put("orderId",orderitemassoc.getOrderId());
}

		if(orderitemassoc.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemassoc.getOrderItemSeqId());
}

		if(orderitemassoc.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderitemassoc.getShipGroupSeqId());
}

		if(orderitemassoc.getToOrderId() != null ){
			returnVal.put("toOrderId",orderitemassoc.getToOrderId());
}

		if(orderitemassoc.getToOrderItemSeqId() != null ){
			returnVal.put("toOrderItemSeqId",orderitemassoc.getToOrderItemSeqId());
}

		if(orderitemassoc.getToShipGroupSeqId() != null ){
			returnVal.put("toShipGroupSeqId",orderitemassoc.getToShipGroupSeqId());
}

		if(orderitemassoc.getOrderItemAssocTypeId() != null ){
			returnVal.put("orderItemAssocTypeId",orderitemassoc.getOrderItemAssocTypeId());
}

		if(orderitemassoc.getQuantity() != null ){
			returnVal.put("quantity",orderitemassoc.getQuantity());
}

		return returnVal;
}


	public static OrderItemAssoc map(Map<String, Object> fields) {

		OrderItemAssoc returnVal = new OrderItemAssoc();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("toOrderId") != null) {
			returnVal.setToOrderId((String) fields.get("toOrderId"));
}

		if(fields.get("toOrderItemSeqId") != null) {
			returnVal.setToOrderItemSeqId((String) fields.get("toOrderItemSeqId"));
}

		if(fields.get("toShipGroupSeqId") != null) {
			returnVal.setToShipGroupSeqId((String) fields.get("toShipGroupSeqId"));
}

		if(fields.get("orderItemAssocTypeId") != null) {
			returnVal.setOrderItemAssocTypeId((String) fields.get("orderItemAssocTypeId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static OrderItemAssoc mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemAssoc returnVal = new OrderItemAssoc();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("toOrderId") != null) {
			returnVal.setToOrderId((String) fields.get("toOrderId"));
}

		if(fields.get("toOrderItemSeqId") != null) {
			returnVal.setToOrderItemSeqId((String) fields.get("toOrderItemSeqId"));
}

		if(fields.get("toShipGroupSeqId") != null) {
			returnVal.setToShipGroupSeqId((String) fields.get("toShipGroupSeqId"));
}

		if(fields.get("orderItemAssocTypeId") != null) {
			returnVal.setOrderItemAssocTypeId((String) fields.get("orderItemAssocTypeId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}


		return returnVal;
 } 
	public static OrderItemAssoc map(GenericValue val) {

OrderItemAssoc returnVal = new OrderItemAssoc();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setToOrderId(val.getString("toOrderId"));
		returnVal.setToOrderItemSeqId(val.getString("toOrderItemSeqId"));
		returnVal.setToShipGroupSeqId(val.getString("toShipGroupSeqId"));
		returnVal.setOrderItemAssocTypeId(val.getString("orderItemAssocTypeId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static OrderItemAssoc map(HttpServletRequest request) throws Exception {

		OrderItemAssoc returnVal = new OrderItemAssoc();

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
		if(paramMap.containsKey("toOrderId"))  {
returnVal.setToOrderId(request.getParameter("toOrderId"));
}
		if(paramMap.containsKey("toOrderItemSeqId"))  {
returnVal.setToOrderItemSeqId(request.getParameter("toOrderItemSeqId"));
}
		if(paramMap.containsKey("toShipGroupSeqId"))  {
returnVal.setToShipGroupSeqId(request.getParameter("toShipGroupSeqId"));
}
		if(paramMap.containsKey("orderItemAssocTypeId"))  {
returnVal.setOrderItemAssocTypeId(request.getParameter("orderItemAssocTypeId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
return returnVal;

}
}
