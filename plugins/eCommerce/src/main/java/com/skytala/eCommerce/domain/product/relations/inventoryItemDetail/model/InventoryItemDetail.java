package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.mapper.InventoryItemDetailMapper;

public class InventoryItemDetail implements Serializable{

private static final long serialVersionUID = 1L;
private String inventoryItemId;
private String inventoryItemDetailSeqId;
private Timestamp effectiveDate;
private BigDecimal quantityOnHandDiff;
private BigDecimal availableToPromiseDiff;
private BigDecimal accountingQuantityDiff;
private BigDecimal unitCost;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private String shipmentId;
private String shipmentItemSeqId;
private String returnId;
private String returnItemSeqId;
private String workEffortId;
private String fixedAssetId;
private String maintHistSeqId;
private String itemIssuanceId;
private String receiptId;
private String physicalInventoryId;
private String reasonEnumId;
private String description;

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getInventoryItemDetailSeqId() {
return inventoryItemDetailSeqId;
}

public void setInventoryItemDetailSeqId(String  inventoryItemDetailSeqId) {
this.inventoryItemDetailSeqId = inventoryItemDetailSeqId;
}

public Timestamp getEffectiveDate() {
return effectiveDate;
}

public void setEffectiveDate(Timestamp  effectiveDate) {
this.effectiveDate = effectiveDate;
}

public BigDecimal getQuantityOnHandDiff() {
return quantityOnHandDiff;
}

public void setQuantityOnHandDiff(BigDecimal  quantityOnHandDiff) {
this.quantityOnHandDiff = quantityOnHandDiff;
}

public BigDecimal getAvailableToPromiseDiff() {
return availableToPromiseDiff;
}

public void setAvailableToPromiseDiff(BigDecimal  availableToPromiseDiff) {
this.availableToPromiseDiff = availableToPromiseDiff;
}

public BigDecimal getAccountingQuantityDiff() {
return accountingQuantityDiff;
}

public void setAccountingQuantityDiff(BigDecimal  accountingQuantityDiff) {
this.accountingQuantityDiff = accountingQuantityDiff;
}

public BigDecimal getUnitCost() {
return unitCost;
}

public void setUnitCost(BigDecimal  unitCost) {
this.unitCost = unitCost;
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

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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

public String getItemIssuanceId() {
return itemIssuanceId;
}

public void setItemIssuanceId(String  itemIssuanceId) {
this.itemIssuanceId = itemIssuanceId;
}

public String getReceiptId() {
return receiptId;
}

public void setReceiptId(String  receiptId) {
this.receiptId = receiptId;
}

public String getPhysicalInventoryId() {
return physicalInventoryId;
}

public void setPhysicalInventoryId(String  physicalInventoryId) {
this.physicalInventoryId = physicalInventoryId;
}

public String getReasonEnumId() {
return reasonEnumId;
}

public void setReasonEnumId(String  reasonEnumId) {
this.reasonEnumId = reasonEnumId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InventoryItemDetailMapper.map(this);
}
}
