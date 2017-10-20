package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.detail;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.detail.InventoryItemDetail;

public class InventoryItemDetailMapper  {


	public static Map<String, Object> map(InventoryItemDetail inventoryitemdetail) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitemdetail.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitemdetail.getInventoryItemId());
}

		if(inventoryitemdetail.getInventoryItemDetailSeqId() != null ){
			returnVal.put("inventoryItemDetailSeqId",inventoryitemdetail.getInventoryItemDetailSeqId());
}

		if(inventoryitemdetail.getEffectiveDate() != null ){
			returnVal.put("effectiveDate",inventoryitemdetail.getEffectiveDate());
}

		if(inventoryitemdetail.getQuantityOnHandDiff() != null ){
			returnVal.put("quantityOnHandDiff",inventoryitemdetail.getQuantityOnHandDiff());
}

		if(inventoryitemdetail.getAvailableToPromiseDiff() != null ){
			returnVal.put("availableToPromiseDiff",inventoryitemdetail.getAvailableToPromiseDiff());
}

		if(inventoryitemdetail.getAccountingQuantityDiff() != null ){
			returnVal.put("accountingQuantityDiff",inventoryitemdetail.getAccountingQuantityDiff());
}

		if(inventoryitemdetail.getUnitCost() != null ){
			returnVal.put("unitCost",inventoryitemdetail.getUnitCost());
}

		if(inventoryitemdetail.getOrderId() != null ){
			returnVal.put("orderId",inventoryitemdetail.getOrderId());
}

		if(inventoryitemdetail.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",inventoryitemdetail.getOrderItemSeqId());
}

		if(inventoryitemdetail.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",inventoryitemdetail.getShipGroupSeqId());
}

		if(inventoryitemdetail.getShipmentId() != null ){
			returnVal.put("shipmentId",inventoryitemdetail.getShipmentId());
}

		if(inventoryitemdetail.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",inventoryitemdetail.getShipmentItemSeqId());
}

		if(inventoryitemdetail.getReturnId() != null ){
			returnVal.put("returnId",inventoryitemdetail.getReturnId());
}

		if(inventoryitemdetail.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",inventoryitemdetail.getReturnItemSeqId());
}

		if(inventoryitemdetail.getWorkEffortId() != null ){
			returnVal.put("workEffortId",inventoryitemdetail.getWorkEffortId());
}

		if(inventoryitemdetail.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",inventoryitemdetail.getFixedAssetId());
}

		if(inventoryitemdetail.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",inventoryitemdetail.getMaintHistSeqId());
}

		if(inventoryitemdetail.getItemIssuanceId() != null ){
			returnVal.put("itemIssuanceId",inventoryitemdetail.getItemIssuanceId());
}

		if(inventoryitemdetail.getReceiptId() != null ){
			returnVal.put("receiptId",inventoryitemdetail.getReceiptId());
}

		if(inventoryitemdetail.getPhysicalInventoryId() != null ){
			returnVal.put("physicalInventoryId",inventoryitemdetail.getPhysicalInventoryId());
}

		if(inventoryitemdetail.getReasonEnumId() != null ){
			returnVal.put("reasonEnumId",inventoryitemdetail.getReasonEnumId());
}

		if(inventoryitemdetail.getDescription() != null ){
			returnVal.put("description",inventoryitemdetail.getDescription());
}

		return returnVal;
}


	public static InventoryItemDetail map(Map<String, Object> fields) {

		InventoryItemDetail returnVal = new InventoryItemDetail();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemDetailSeqId") != null) {
			returnVal.setInventoryItemDetailSeqId((String) fields.get("inventoryItemDetailSeqId"));
}

		if(fields.get("effectiveDate") != null) {
			returnVal.setEffectiveDate((Timestamp) fields.get("effectiveDate"));
}

		if(fields.get("quantityOnHandDiff") != null) {
			returnVal.setQuantityOnHandDiff((BigDecimal) fields.get("quantityOnHandDiff"));
}

		if(fields.get("availableToPromiseDiff") != null) {
			returnVal.setAvailableToPromiseDiff((BigDecimal) fields.get("availableToPromiseDiff"));
}

		if(fields.get("accountingQuantityDiff") != null) {
			returnVal.setAccountingQuantityDiff((BigDecimal) fields.get("accountingQuantityDiff"));
}

		if(fields.get("unitCost") != null) {
			returnVal.setUnitCost((BigDecimal) fields.get("unitCost"));
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

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemDetail mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItemDetail returnVal = new InventoryItemDetail();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemDetailSeqId") != null) {
			returnVal.setInventoryItemDetailSeqId((String) fields.get("inventoryItemDetailSeqId"));
}

		if(fields.get("effectiveDate") != null) {
String buf = fields.get("effectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEffectiveDate(ibuf);
}

		if(fields.get("quantityOnHandDiff") != null) {
String buf;
buf = fields.get("quantityOnHandDiff");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandDiff(bd);
}

		if(fields.get("availableToPromiseDiff") != null) {
String buf;
buf = fields.get("availableToPromiseDiff");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseDiff(bd);
}

		if(fields.get("accountingQuantityDiff") != null) {
String buf;
buf = fields.get("accountingQuantityDiff");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountingQuantityDiff(bd);
}

		if(fields.get("unitCost") != null) {
String buf;
buf = fields.get("unitCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitCost(bd);
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

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("itemIssuanceId") != null) {
			returnVal.setItemIssuanceId((String) fields.get("itemIssuanceId"));
}

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("physicalInventoryId") != null) {
			returnVal.setPhysicalInventoryId((String) fields.get("physicalInventoryId"));
}

		if(fields.get("reasonEnumId") != null) {
			returnVal.setReasonEnumId((String) fields.get("reasonEnumId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static InventoryItemDetail map(GenericValue val) {

InventoryItemDetail returnVal = new InventoryItemDetail();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setInventoryItemDetailSeqId(val.getString("inventoryItemDetailSeqId"));
		returnVal.setEffectiveDate(val.getTimestamp("effectiveDate"));
		returnVal.setQuantityOnHandDiff(val.getBigDecimal("quantityOnHandDiff"));
		returnVal.setAvailableToPromiseDiff(val.getBigDecimal("availableToPromiseDiff"));
		returnVal.setAccountingQuantityDiff(val.getBigDecimal("accountingQuantityDiff"));
		returnVal.setUnitCost(val.getBigDecimal("unitCost"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setItemIssuanceId(val.getString("itemIssuanceId"));
		returnVal.setReceiptId(val.getString("receiptId"));
		returnVal.setPhysicalInventoryId(val.getString("physicalInventoryId"));
		returnVal.setReasonEnumId(val.getString("reasonEnumId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static InventoryItemDetail map(HttpServletRequest request) throws Exception {

		InventoryItemDetail returnVal = new InventoryItemDetail();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}

		if(paramMap.containsKey("inventoryItemDetailSeqId"))  {
returnVal.setInventoryItemDetailSeqId(request.getParameter("inventoryItemDetailSeqId"));
}
		if(paramMap.containsKey("effectiveDate"))  {
String buf = request.getParameter("effectiveDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEffectiveDate(ibuf);
}
		if(paramMap.containsKey("quantityOnHandDiff"))  {
String buf = request.getParameter("quantityOnHandDiff");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandDiff(bd);
}
		if(paramMap.containsKey("availableToPromiseDiff"))  {
String buf = request.getParameter("availableToPromiseDiff");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseDiff(bd);
}
		if(paramMap.containsKey("accountingQuantityDiff"))  {
String buf = request.getParameter("accountingQuantityDiff");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountingQuantityDiff(bd);
}
		if(paramMap.containsKey("unitCost"))  {
String buf = request.getParameter("unitCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitCost(bd);
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
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("returnId"))  {
returnVal.setReturnId(request.getParameter("returnId"));
}
		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("itemIssuanceId"))  {
returnVal.setItemIssuanceId(request.getParameter("itemIssuanceId"));
}
		if(paramMap.containsKey("receiptId"))  {
returnVal.setReceiptId(request.getParameter("receiptId"));
}
		if(paramMap.containsKey("physicalInventoryId"))  {
returnVal.setPhysicalInventoryId(request.getParameter("physicalInventoryId"));
}
		if(paramMap.containsKey("reasonEnumId"))  {
returnVal.setReasonEnumId(request.getParameter("reasonEnumId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
