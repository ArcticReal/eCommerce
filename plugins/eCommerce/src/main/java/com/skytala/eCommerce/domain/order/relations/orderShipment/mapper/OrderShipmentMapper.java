package com.skytala.eCommerce.domain.order.relations.orderShipment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;

public class OrderShipmentMapper  {


	public static Map<String, Object> map(OrderShipment ordershipment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordershipment.getOrderId() != null ){
			returnVal.put("orderId",ordershipment.getOrderId());
}

		if(ordershipment.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",ordershipment.getOrderItemSeqId());
}

		if(ordershipment.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",ordershipment.getShipGroupSeqId());
}

		if(ordershipment.getShipmentId() != null ){
			returnVal.put("shipmentId",ordershipment.getShipmentId());
}

		if(ordershipment.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",ordershipment.getShipmentItemSeqId());
}

		if(ordershipment.getQuantity() != null ){
			returnVal.put("quantity",ordershipment.getQuantity());
}

		return returnVal;
}


	public static OrderShipment map(Map<String, Object> fields) {

		OrderShipment returnVal = new OrderShipment();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static OrderShipment mapstrstr(Map<String, String> fields) throws Exception {

		OrderShipment returnVal = new OrderShipment();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
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
	public static OrderShipment map(GenericValue val) {

OrderShipment returnVal = new OrderShipment();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static OrderShipment map(HttpServletRequest request) throws Exception {

		OrderShipment returnVal = new OrderShipment();

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
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
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
