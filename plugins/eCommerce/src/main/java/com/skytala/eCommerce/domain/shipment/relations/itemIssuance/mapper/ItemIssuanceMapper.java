package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model.ItemIssuance;

public class ItemIssuanceMapper  {


	public static Map<String, Object> map(ItemIssuance itemissuance) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(itemissuance.getItemIssuanceId() != null ){
			returnVal.put("itemIssuanceId",itemissuance.getItemIssuanceId());
}

		if(itemissuance.getOrderId() != null ){
			returnVal.put("orderId",itemissuance.getOrderId());
}

		if(itemissuance.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",itemissuance.getOrderItemSeqId());
}

		if(itemissuance.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",itemissuance.getShipGroupSeqId());
}

		if(itemissuance.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",itemissuance.getInventoryItemId());
}

		if(itemissuance.getShipmentId() != null ){
			returnVal.put("shipmentId",itemissuance.getShipmentId());
}

		if(itemissuance.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",itemissuance.getShipmentItemSeqId());
}

		if(itemissuance.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",itemissuance.getFixedAssetId());
}

		if(itemissuance.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",itemissuance.getMaintHistSeqId());
}

		if(itemissuance.getIssuedDateTime() != null ){
			returnVal.put("issuedDateTime",itemissuance.getIssuedDateTime());
}

		if(itemissuance.getIssuedByUserLoginId() != null ){
			returnVal.put("issuedByUserLoginId",itemissuance.getIssuedByUserLoginId());
}

		if(itemissuance.getQuantity() != null ){
			returnVal.put("quantity",itemissuance.getQuantity());
}

		if(itemissuance.getCancelQuantity() != null ){
			returnVal.put("cancelQuantity",itemissuance.getCancelQuantity());
}

		return returnVal;
}


	public static ItemIssuance map(Map<String, Object> fields) {

		ItemIssuance returnVal = new ItemIssuance();

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("issuedDateTime") != null) {
			returnVal.setIssuedDateTime((Timestamp) fields.get("issuedDateTime"));
}

		if(fields.get("issuedByUserLoginId") != null) {
			returnVal.setIssuedByUserLoginId((String) fields.get("issuedByUserLoginId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("cancelQuantity") != null) {
			returnVal.setCancelQuantity((BigDecimal) fields.get("cancelQuantity"));
}


		return returnVal;
 } 
	public static ItemIssuance mapstrstr(Map<String, String> fields) throws Exception {

		ItemIssuance returnVal = new ItemIssuance();

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("issuedDateTime") != null) {
String buf = fields.get("issuedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setIssuedDateTime(ibuf);
}

		if(fields.get("issuedByUserLoginId") != null) {
			returnVal.setIssuedByUserLoginId((String) fields.get("issuedByUserLoginId"));
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
	public static ItemIssuance map(GenericValue val) {

ItemIssuance returnVal = new ItemIssuance();
		returnVal.setItemIssuanceId(val.getString("itemIssuanceId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setIssuedDateTime(val.getTimestamp("issuedDateTime"));
		returnVal.setIssuedByUserLoginId(val.getString("issuedByUserLoginId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setCancelQuantity(val.getBigDecimal("cancelQuantity"));


return returnVal;

}

public static ItemIssuance map(HttpServletRequest request) throws Exception {

		ItemIssuance returnVal = new ItemIssuance();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("itemIssuanceId")) {
returnVal.setItemIssuanceId(request.getParameter("itemIssuanceId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("issuedDateTime"))  {
String buf = request.getParameter("issuedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setIssuedDateTime(ibuf);
}
		if(paramMap.containsKey("issuedByUserLoginId"))  {
returnVal.setIssuedByUserLoginId(request.getParameter("issuedByUserLoginId"));
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
