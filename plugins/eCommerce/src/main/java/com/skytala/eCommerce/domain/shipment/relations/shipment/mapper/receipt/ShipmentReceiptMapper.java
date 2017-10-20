package com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receipt;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt.ShipmentReceipt;

public class ShipmentReceiptMapper  {


	public static Map<String, Object> map(ShipmentReceipt shipmentreceipt) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentreceipt.getReceiptId() != null ){
			returnVal.put("receiptId",shipmentreceipt.getReceiptId());
}

		if(shipmentreceipt.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",shipmentreceipt.getInventoryItemId());
}

		if(shipmentreceipt.getProductId() != null ){
			returnVal.put("productId",shipmentreceipt.getProductId());
}

		if(shipmentreceipt.getShipmentId() != null ){
			returnVal.put("shipmentId",shipmentreceipt.getShipmentId());
}

		if(shipmentreceipt.getShipmentItemSeqId() != null ){
			returnVal.put("shipmentItemSeqId",shipmentreceipt.getShipmentItemSeqId());
}

		if(shipmentreceipt.getShipmentPackageSeqId() != null ){
			returnVal.put("shipmentPackageSeqId",shipmentreceipt.getShipmentPackageSeqId());
}

		if(shipmentreceipt.getOrderId() != null ){
			returnVal.put("orderId",shipmentreceipt.getOrderId());
}

		if(shipmentreceipt.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",shipmentreceipt.getOrderItemSeqId());
}

		if(shipmentreceipt.getReturnId() != null ){
			returnVal.put("returnId",shipmentreceipt.getReturnId());
}

		if(shipmentreceipt.getReturnItemSeqId() != null ){
			returnVal.put("returnItemSeqId",shipmentreceipt.getReturnItemSeqId());
}

		if(shipmentreceipt.getRejectionId() != null ){
			returnVal.put("rejectionId",shipmentreceipt.getRejectionId());
}

		if(shipmentreceipt.getReceivedByUserLoginId() != null ){
			returnVal.put("receivedByUserLoginId",shipmentreceipt.getReceivedByUserLoginId());
}

		if(shipmentreceipt.getDatetimeReceived() != null ){
			returnVal.put("datetimeReceived",shipmentreceipt.getDatetimeReceived());
}

		if(shipmentreceipt.getItemDescription() != null ){
			returnVal.put("itemDescription",shipmentreceipt.getItemDescription());
}

		if(shipmentreceipt.getQuantityAccepted() != null ){
			returnVal.put("quantityAccepted",shipmentreceipt.getQuantityAccepted());
}

		if(shipmentreceipt.getQuantityRejected() != null ){
			returnVal.put("quantityRejected",shipmentreceipt.getQuantityRejected());
}

		return returnVal;
}


	public static ShipmentReceipt map(Map<String, Object> fields) {

		ShipmentReceipt returnVal = new ShipmentReceipt();

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("rejectionId") != null) {
			returnVal.setRejectionId((String) fields.get("rejectionId"));
}

		if(fields.get("receivedByUserLoginId") != null) {
			returnVal.setReceivedByUserLoginId((String) fields.get("receivedByUserLoginId"));
}

		if(fields.get("datetimeReceived") != null) {
			returnVal.setDatetimeReceived((Timestamp) fields.get("datetimeReceived"));
}

		if(fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
}

		if(fields.get("quantityAccepted") != null) {
			returnVal.setQuantityAccepted((BigDecimal) fields.get("quantityAccepted"));
}

		if(fields.get("quantityRejected") != null) {
			returnVal.setQuantityRejected((BigDecimal) fields.get("quantityRejected"));
}


		return returnVal;
 } 
	public static ShipmentReceipt mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentReceipt returnVal = new ShipmentReceipt();

		if(fields.get("receiptId") != null) {
			returnVal.setReceiptId((String) fields.get("receiptId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("shipmentId") != null) {
			returnVal.setShipmentId((String) fields.get("shipmentId"));
}

		if(fields.get("shipmentItemSeqId") != null) {
			returnVal.setShipmentItemSeqId((String) fields.get("shipmentItemSeqId"));
}

		if(fields.get("shipmentPackageSeqId") != null) {
			returnVal.setShipmentPackageSeqId((String) fields.get("shipmentPackageSeqId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("returnId") != null) {
			returnVal.setReturnId((String) fields.get("returnId"));
}

		if(fields.get("returnItemSeqId") != null) {
			returnVal.setReturnItemSeqId((String) fields.get("returnItemSeqId"));
}

		if(fields.get("rejectionId") != null) {
			returnVal.setRejectionId((String) fields.get("rejectionId"));
}

		if(fields.get("receivedByUserLoginId") != null) {
			returnVal.setReceivedByUserLoginId((String) fields.get("receivedByUserLoginId"));
}

		if(fields.get("datetimeReceived") != null) {
String buf = fields.get("datetimeReceived");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeReceived(ibuf);
}

		if(fields.get("itemDescription") != null) {
			returnVal.setItemDescription((String) fields.get("itemDescription"));
}

		if(fields.get("quantityAccepted") != null) {
String buf;
buf = fields.get("quantityAccepted");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityAccepted(bd);
}

		if(fields.get("quantityRejected") != null) {
String buf;
buf = fields.get("quantityRejected");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityRejected(bd);
}


		return returnVal;
 } 
	public static ShipmentReceipt map(GenericValue val) {

ShipmentReceipt returnVal = new ShipmentReceipt();
		returnVal.setReceiptId(val.getString("receiptId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setShipmentId(val.getString("shipmentId"));
		returnVal.setShipmentItemSeqId(val.getString("shipmentItemSeqId"));
		returnVal.setShipmentPackageSeqId(val.getString("shipmentPackageSeqId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setReturnId(val.getString("returnId"));
		returnVal.setReturnItemSeqId(val.getString("returnItemSeqId"));
		returnVal.setRejectionId(val.getString("rejectionId"));
		returnVal.setReceivedByUserLoginId(val.getString("receivedByUserLoginId"));
		returnVal.setDatetimeReceived(val.getTimestamp("datetimeReceived"));
		returnVal.setItemDescription(val.getString("itemDescription"));
		returnVal.setQuantityAccepted(val.getBigDecimal("quantityAccepted"));
		returnVal.setQuantityRejected(val.getBigDecimal("quantityRejected"));


return returnVal;

}

public static ShipmentReceipt map(HttpServletRequest request) throws Exception {

		ShipmentReceipt returnVal = new ShipmentReceipt();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("receiptId")) {
returnVal.setReceiptId(request.getParameter("receiptId"));
}

		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("shipmentId"))  {
returnVal.setShipmentId(request.getParameter("shipmentId"));
}
		if(paramMap.containsKey("shipmentItemSeqId"))  {
returnVal.setShipmentItemSeqId(request.getParameter("shipmentItemSeqId"));
}
		if(paramMap.containsKey("shipmentPackageSeqId"))  {
returnVal.setShipmentPackageSeqId(request.getParameter("shipmentPackageSeqId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("returnId"))  {
returnVal.setReturnId(request.getParameter("returnId"));
}
		if(paramMap.containsKey("returnItemSeqId"))  {
returnVal.setReturnItemSeqId(request.getParameter("returnItemSeqId"));
}
		if(paramMap.containsKey("rejectionId"))  {
returnVal.setRejectionId(request.getParameter("rejectionId"));
}
		if(paramMap.containsKey("receivedByUserLoginId"))  {
returnVal.setReceivedByUserLoginId(request.getParameter("receivedByUserLoginId"));
}
		if(paramMap.containsKey("datetimeReceived"))  {
String buf = request.getParameter("datetimeReceived");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeReceived(ibuf);
}
		if(paramMap.containsKey("itemDescription"))  {
returnVal.setItemDescription(request.getParameter("itemDescription"));
}
		if(paramMap.containsKey("quantityAccepted"))  {
String buf = request.getParameter("quantityAccepted");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityAccepted(bd);
}
		if(paramMap.containsKey("quantityRejected"))  {
String buf = request.getParameter("quantityRejected");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityRejected(bd);
}
return returnVal;

}
}
