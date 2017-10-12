package com.skytala.eCommerce.domain.shipment.relations.picklistItem.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.shipment.relations.picklistItem.mapper.PicklistItemMapper;

public class PicklistItem implements Serializable{

private static final long serialVersionUID = 1L;
private String picklistBinId;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private String inventoryItemId;
private String itemStatusId;
private BigDecimal quantity;

public String getPicklistBinId() {
return picklistBinId;
}

public void setPicklistBinId(String  picklistBinId) {
this.picklistBinId = picklistBinId;
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

public String getItemStatusId() {
return itemStatusId;
}

public void setItemStatusId(String  itemStatusId) {
this.itemStatusId = itemStatusId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}


public Map<String, Object> mapAttributeField() {
return PicklistItemMapper.map(this);
}
}
