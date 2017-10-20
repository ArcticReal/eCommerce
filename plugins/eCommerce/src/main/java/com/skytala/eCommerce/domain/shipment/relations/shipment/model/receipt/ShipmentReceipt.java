package com.skytala.eCommerce.domain.shipment.relations.shipment.model.receipt;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.receipt.ShipmentReceiptMapper;

public class ShipmentReceipt implements Serializable{

private static final long serialVersionUID = 1L;
private String receiptId;
private String inventoryItemId;
private String productId;
private String shipmentId;
private String shipmentItemSeqId;
private String shipmentPackageSeqId;
private String orderId;
private String orderItemSeqId;
private String returnId;
private String returnItemSeqId;
private String rejectionId;
private String receivedByUserLoginId;
private Timestamp datetimeReceived;
private String itemDescription;
private BigDecimal quantityAccepted;
private BigDecimal quantityRejected;

public String getReceiptId() {
return receiptId;
}

public void setReceiptId(String  receiptId) {
this.receiptId = receiptId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getShipmentId() {
return shipmentId;
}

public void setShipmentId(String  shipmentId) {
this.shipmentId = shipmentId;
}

public String getShipmentItemSeqId() {
return shipmentItemSeqId;
}

public void setShipmentItemSeqId(String  shipmentItemSeqId) {
this.shipmentItemSeqId = shipmentItemSeqId;
}

public String getShipmentPackageSeqId() {
return shipmentPackageSeqId;
}

public void setShipmentPackageSeqId(String  shipmentPackageSeqId) {
this.shipmentPackageSeqId = shipmentPackageSeqId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getReturnId() {
return returnId;
}

public void setReturnId(String  returnId) {
this.returnId = returnId;
}

public String getReturnItemSeqId() {
return returnItemSeqId;
}

public void setReturnItemSeqId(String  returnItemSeqId) {
this.returnItemSeqId = returnItemSeqId;
}

public String getRejectionId() {
return rejectionId;
}

public void setRejectionId(String  rejectionId) {
this.rejectionId = rejectionId;
}

public String getReceivedByUserLoginId() {
return receivedByUserLoginId;
}

public void setReceivedByUserLoginId(String  receivedByUserLoginId) {
this.receivedByUserLoginId = receivedByUserLoginId;
}

public Timestamp getDatetimeReceived() {
return datetimeReceived;
}

public void setDatetimeReceived(Timestamp  datetimeReceived) {
this.datetimeReceived = datetimeReceived;
}

public String getItemDescription() {
return itemDescription;
}

public void setItemDescription(String  itemDescription) {
this.itemDescription = itemDescription;
}

public BigDecimal getQuantityAccepted() {
return quantityAccepted;
}

public void setQuantityAccepted(BigDecimal  quantityAccepted) {
this.quantityAccepted = quantityAccepted;
}

public BigDecimal getQuantityRejected() {
return quantityRejected;
}

public void setQuantityRejected(BigDecimal  quantityRejected) {
this.quantityRejected = quantityRejected;
}


public Map<String, Object> mapAttributeField() {
return ShipmentReceiptMapper.map(this);
}
}
