package com.skytala.eCommerce.domain.order.relations.returnItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;

public class ReturnItemMapper  {


	public static Map<String, Object> map(ReturnItem returnitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitem.getReturnId() != null ){
			returnVal.put("returnId",returnitem.getReturnId());
}

		if(returnitem.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",returnitem.getReturnItemSeqId());
}

		if(returnitem.getReturnReasonId() != null ){
			returnVal.put("returnReasonId",returnitem.getReturnReasonId());
}

		if(returnitem.getReturnTypeId() != null ){
			returnVal.put("returnTypeId",returnitem.getReturnTypeId());
}

		if(returnitem.getReturnItemTypeId() != null ){
			returnVal.put("returnItemTypeId",returnitem.getReturnItemTypeId());
}

		if(returnitem.getProductId() != null ){
			returnVal.put("productId",returnitem.getProductId());
}

		if(returnitem.getDescription() != null ){
			returnVal.put("description",returnitem.getDescription());
}

		if(returnitem.getOrderId() != null ){
			returnVal.put("orderId",returnitem.getOrderId());
}

		if(returnitem.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",returnitem.getOrderItemSeqId());
}

		if(returnitem.getStatusId() != null ){
			returnVal.put("statusId",returnitem.getStatusId());
}

		if(returnitem.getExpectedItemStatus() != null ){
			returnVal.put("expectedItemStatus",returnitem.getExpectedItemStatus());
}

		if(returnitem.getReturnQuantity() != null ){
			returnVal.put("returnQuantity",returnitem.getReturnQuantity());
}

		if(returnitem.getReceivedQuantity() != null ){
			returnVal.put("receivedQuantity",returnitem.getReceivedQuantity());
}

		if(returnitem.getReturnPrice() != null ){
			returnVal.put("returnPrice",returnitem.getReturnPrice());
}

		if(returnitem.getReturnItemResponseId() != null ){
			returnVal.put("returnItemResponseId",returnitem.getReturnItemResponseId());
}

		return returnVal;
}


	public static ReturnItem map(Map<String, Object> fields) {

		ReturnItem returnVal = new ReturnItem();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("returnReasonId") != null) {
			returnVal.setReturnReasonId((String) fields.get("returnReasonId"));
}

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("expectedItemStatus") != null) {
			returnVal.setExpectedItemStatus((String) fields.get("expectedItemStatus"));
}

		if(fields.get("returnQuantity") != null) {
			returnVal.setReturnQuantity((BigDecimal) fields.get("returnQuantity"));
}

		if(fields.get("receivedQuantity") != null) {
			returnVal.setReceivedQuantity((BigDecimal) fields.get("receivedQuantity"));
}

		if(fields.get("returnPrice") != null) {
			returnVal.setReturnPrice((BigDecimal) fields.get("returnPrice"));
}

		if(fields.get("returnItemResponseId") != null) {
			returnVal.setReturnItemResponseId((String) fields.get("returnItemResponseId"));
}


		return returnVal;
 } 
	public static ReturnItem mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItem returnVal = new ReturnItem();

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("returnReasonId") != null) {
			returnVal.setReturnReasonId((String) fields.get("returnReasonId"));
}

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("expectedItemStatus") != null) {
			returnVal.setExpectedItemStatus((String) fields.get("expectedItemStatus"));
}

		if(fields.get("returnQuantity") != null) {
String buf;
buf = fields.get("returnQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReturnQuantity(bd);
}

		if(fields.get("receivedQuantity") != null) {
String buf;
buf = fields.get("receivedQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReceivedQuantity(bd);
}

		if(fields.get("returnPrice") != null) {
String buf;
buf = fields.get("returnPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReturnPrice(bd);
}

		if(fields.get("returnItemResponseId") != null) {
			returnVal.setReturnItemResponseId((String) fields.get("returnItemResponseId"));
}


		return returnVal;
 } 
	public static ReturnItem map(GenericValue val) {

ReturnItem returnVal = new ReturnItem();
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setReturnReasonId(val.getString("returnReasonId"));
		returnVal.setReturnTypeId(val.getString("returnTypeId"));
		returnVal.setReturnItemTypeId(val.getString("returnItemTypeId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setExpectedItemStatus(val.getString("expectedItemStatus"));
		returnVal.setReturnQuantity(val.getBigDecimal("returnQuantity"));
		returnVal.setReceivedQuantity(val.getBigDecimal("receivedQuantity"));
		returnVal.setReturnPrice(val.getBigDecimal("returnPrice"));
		returnVal.setReturnItemResponseId(val.getString("returnItemResponseId"));


return returnVal;

}

public static ReturnItem map(HttpServletRequest request) throws Exception {

		ReturnItem returnVal = new ReturnItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnId")) {
returnVal.setReturnId(request.getParameter("returnId"));
}

		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("returnReasonId"))  {
returnVal.setReturnReasonId(request.getParameter("returnReasonId"));
}
		if(paramMap.containsKey("returnTypeId"))  {
returnVal.setReturnTypeId(request.getParameter("returnTypeId"));
}
		if(paramMap.containsKey("returnItemTypeId"))  {
returnVal.setReturnItemTypeId(request.getParameter("returnItemTypeId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("expectedItemStatus"))  {
returnVal.setExpectedItemStatus(request.getParameter("expectedItemStatus"));
}
		if(paramMap.containsKey("returnQuantity"))  {
String buf = request.getParameter("returnQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReturnQuantity(bd);
}
		if(paramMap.containsKey("receivedQuantity"))  {
String buf = request.getParameter("receivedQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReceivedQuantity(bd);
}
		if(paramMap.containsKey("returnPrice"))  {
String buf = request.getParameter("returnPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReturnPrice(bd);
}
		if(paramMap.containsKey("returnItemResponseId"))  {
returnVal.setReturnItemResponseId(request.getParameter("returnItemResponseId"));
}
return returnVal;

}
}
