package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model.OrderItemShipGrpInvRes;

public class OrderItemShipGrpInvResMapper  {


	public static Map<String, Object> map(OrderItemShipGrpInvRes orderitemshipgrpinvres) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitemshipgrpinvres.getOrderId() != null ){
			returnVal.put("orderId",orderitemshipgrpinvres.getOrderId());
}

		if(orderitemshipgrpinvres.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",orderitemshipgrpinvres.getShipGroupSeqId());
}

		if(orderitemshipgrpinvres.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitemshipgrpinvres.getOrderItemSeqId());
}

		if(orderitemshipgrpinvres.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",orderitemshipgrpinvres.getInventoryItemId());
}

		if(orderitemshipgrpinvres.getReserveOrderEnumId() != null ){
			returnVal.put("reserveOrderEnumId",orderitemshipgrpinvres.getReserveOrderEnumId());
}

		if(orderitemshipgrpinvres.getQuantity() != null ){
			returnVal.put("quantity",orderitemshipgrpinvres.getQuantity());
}

		if(orderitemshipgrpinvres.getQuantityNotAvailable() != null ){
			returnVal.put("quantityNotAvailable",orderitemshipgrpinvres.getQuantityNotAvailable());
}

		if(orderitemshipgrpinvres.getReservedDatetime() != null ){
			returnVal.put("reservedDatetime",orderitemshipgrpinvres.getReservedDatetime());
}

		if(orderitemshipgrpinvres.getCreatedDatetime() != null ){
			returnVal.put("createdDatetime",orderitemshipgrpinvres.getCreatedDatetime());
}

		if(orderitemshipgrpinvres.getPromisedDatetime() != null ){
			returnVal.put("promisedDatetime",orderitemshipgrpinvres.getPromisedDatetime());
}

		if(orderitemshipgrpinvres.getCurrentPromisedDate() != null ){
			returnVal.put("currentPromisedDate",orderitemshipgrpinvres.getCurrentPromisedDate());
}

		if(orderitemshipgrpinvres.getPriority() != null ){
			returnVal.put("priority",orderitemshipgrpinvres.getPriority());
}

		if(orderitemshipgrpinvres.getSequenceId() != null ){
			returnVal.put("sequenceId",orderitemshipgrpinvres.getSequenceId());
}

		if(orderitemshipgrpinvres.getOldPickStartDate() != null ){
			returnVal.put("oldPickStartDate",orderitemshipgrpinvres.getOldPickStartDate());
}

		return returnVal;
}


	public static OrderItemShipGrpInvRes map(Map<String, Object> fields) {

		OrderItemShipGrpInvRes returnVal = new OrderItemShipGrpInvRes();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("reserveOrderEnumId") != null) {
			returnVal.setReserveOrderEnumId((String) fields.get("reserveOrderEnumId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("quantityNotAvailable") != null) {
			returnVal.setQuantityNotAvailable((BigDecimal) fields.get("quantityNotAvailable"));
}

		if(fields.get("reservedDatetime") != null) {
			returnVal.setReservedDatetime((Timestamp) fields.get("reservedDatetime"));
}

		if(fields.get("createdDatetime") != null) {
			returnVal.setCreatedDatetime((Timestamp) fields.get("createdDatetime"));
}

		if(fields.get("promisedDatetime") != null) {
			returnVal.setPromisedDatetime((Timestamp) fields.get("promisedDatetime"));
}

		if(fields.get("currentPromisedDate") != null) {
			returnVal.setCurrentPromisedDate((Timestamp) fields.get("currentPromisedDate"));
}

		if(fields.get("priority") != null) {
			returnVal.setPriority((boolean) fields.get("priority"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((long) fields.get("sequenceId"));
}

		if(fields.get("oldPickStartDate") != null) {
			returnVal.setOldPickStartDate((Timestamp) fields.get("oldPickStartDate"));
}


		return returnVal;
 } 
	public static OrderItemShipGrpInvRes mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemShipGrpInvRes returnVal = new OrderItemShipGrpInvRes();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("reserveOrderEnumId") != null) {
			returnVal.setReserveOrderEnumId((String) fields.get("reserveOrderEnumId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("quantityNotAvailable") != null) {
String buf;
buf = fields.get("quantityNotAvailable");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityNotAvailable(bd);
}

		if(fields.get("reservedDatetime") != null) {
String buf = fields.get("reservedDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReservedDatetime(ibuf);
}

		if(fields.get("createdDatetime") != null) {
String buf = fields.get("createdDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDatetime(ibuf);
}

		if(fields.get("promisedDatetime") != null) {
String buf = fields.get("promisedDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPromisedDatetime(ibuf);
}

		if(fields.get("currentPromisedDate") != null) {
String buf = fields.get("currentPromisedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCurrentPromisedDate(ibuf);
}

		if(fields.get("priority") != null) {
String buf;
buf = fields.get("priority");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPriority(ibuf);
}

		if(fields.get("sequenceId") != null) {
String buf;
buf = fields.get("sequenceId");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceId(ibuf);
}

		if(fields.get("oldPickStartDate") != null) {
String buf = fields.get("oldPickStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setOldPickStartDate(ibuf);
}


		return returnVal;
 } 
	public static OrderItemShipGrpInvRes map(GenericValue val) {

OrderItemShipGrpInvRes returnVal = new OrderItemShipGrpInvRes();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setReserveOrderEnumId(val.getString("reserveOrderEnumId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setQuantityNotAvailable(val.getBigDecimal("quantityNotAvailable"));
		returnVal.setReservedDatetime(val.getTimestamp("reservedDatetime"));
		returnVal.setCreatedDatetime(val.getTimestamp("createdDatetime"));
		returnVal.setPromisedDatetime(val.getTimestamp("promisedDatetime"));
		returnVal.setCurrentPromisedDate(val.getTimestamp("currentPromisedDate"));
		returnVal.setPriority(val.getBoolean("priority"));
		returnVal.setSequenceId(val.getLong("sequenceId"));
		returnVal.setOldPickStartDate(val.getTimestamp("oldPickStartDate"));


return returnVal;

}

public static OrderItemShipGrpInvRes map(HttpServletRequest request) throws Exception {

		OrderItemShipGrpInvRes returnVal = new OrderItemShipGrpInvRes();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("reserveOrderEnumId"))  {
returnVal.setReserveOrderEnumId(request.getParameter("reserveOrderEnumId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("quantityNotAvailable"))  {
String buf = request.getParameter("quantityNotAvailable");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityNotAvailable(bd);
}
		if(paramMap.containsKey("reservedDatetime"))  {
String buf = request.getParameter("reservedDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReservedDatetime(ibuf);
}
		if(paramMap.containsKey("createdDatetime"))  {
String buf = request.getParameter("createdDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDatetime(ibuf);
}
		if(paramMap.containsKey("promisedDatetime"))  {
String buf = request.getParameter("promisedDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPromisedDatetime(ibuf);
}
		if(paramMap.containsKey("currentPromisedDate"))  {
String buf = request.getParameter("currentPromisedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCurrentPromisedDate(ibuf);
}
		if(paramMap.containsKey("priority"))  {
String buf = request.getParameter("priority");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPriority(ibuf);
}
		if(paramMap.containsKey("sequenceId"))  {
String buf = request.getParameter("sequenceId");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceId(ibuf);
}
		if(paramMap.containsKey("oldPickStartDate"))  {
String buf = request.getParameter("oldPickStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setOldPickStartDate(ibuf);
}
return returnVal;

}
}
