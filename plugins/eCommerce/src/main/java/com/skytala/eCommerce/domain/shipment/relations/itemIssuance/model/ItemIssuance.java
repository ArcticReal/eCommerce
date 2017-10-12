package com.skytala.eCommerce.domain.shipment.relations.itemIssuance.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.itemIssuance.mapper.ItemIssuanceMapper;

public class ItemIssuance implements Serializable{

private static final long serialVersionUID = 1L;
private String itemIssuanceId;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private String inventoryItemId;
private String shipmentId;
private String shipmentItemSeqId;
private String fixedAssetId;
private String maintHistSeqId;
private Timestamp issuedDateTime;
private String issuedByUserLoginId;
private BigDecimal quantity;
private BigDecimal cancelQuantity;

public String getItemIssuanceId() {
return itemIssuanceId;
}

public void setItemIssuanceId(String  itemIssuanceId) {
this.itemIssuanceId = itemIssuanceId;
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

public String getShipGroupSeqId() {
return shipGroupSeqId;
}

public void setShipGroupSeqId(String  shipGroupSeqId) {
this.shipGroupSeqId = shipGroupSeqId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
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

public String getFixedAssetId() {
return fixedAssetId;
}

public void setFixedAssetId(String  fixedAssetId) {
this.fixedAssetId = fixedAssetId;
}

public String getMaintHistSeqId() {
return maintHistSeqId;
}

public void setMaintHistSeqId(String  maintHistSeqId) {
this.maintHistSeqId = maintHistSeqId;
}

public Timestamp getIssuedDateTime() {
return issuedDateTime;
}

public void setIssuedDateTime(Timestamp  issuedDateTime) {
this.issuedDateTime = issuedDateTime;
}

public String getIssuedByUserLoginId() {
return issuedByUserLoginId;
}

public void setIssuedByUserLoginId(String  issuedByUserLoginId) {
this.issuedByUserLoginId = issuedByUserLoginId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getCancelQuantity() {
return cancelQuantity;
}

public void setCancelQuantity(BigDecimal  cancelQuantity) {
this.cancelQuantity = cancelQuantity;
}


public Map<String, Object> mapAttributeField() {
return ItemIssuanceMapper.map(this);
}
}
