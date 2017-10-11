package com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGrpInvRes.mapper.OrderItemShipGrpInvResMapper;

public class OrderItemShipGrpInvRes implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String shipGroupSeqId;
private String orderItemSeqId;
private String inventoryItemId;
private String reserveOrderEnumId;
private BigDecimal quantity;
private BigDecimal quantityNotAvailable;
private Timestamp reservedDatetime;
private Timestamp createdDatetime;
private Timestamp promisedDatetime;
private Timestamp currentPromisedDate;
private Boolean priority;
private Long sequenceId;
private Timestamp oldPickStartDate;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getShipGroupSeqId() {
return shipGroupSeqId;
}

public void setShipGroupSeqId(String  shipGroupSeqId) {
this.shipGroupSeqId = shipGroupSeqId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public String getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(String  inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public String getReserveOrderEnumId() {
return reserveOrderEnumId;
}

public void setReserveOrderEnumId(String  reserveOrderEnumId) {
this.reserveOrderEnumId = reserveOrderEnumId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getQuantityNotAvailable() {
return quantityNotAvailable;
}

public void setQuantityNotAvailable(BigDecimal  quantityNotAvailable) {
this.quantityNotAvailable = quantityNotAvailable;
}

public Timestamp getReservedDatetime() {
return reservedDatetime;
}

public void setReservedDatetime(Timestamp  reservedDatetime) {
this.reservedDatetime = reservedDatetime;
}

public Timestamp getCreatedDatetime() {
return createdDatetime;
}

public void setCreatedDatetime(Timestamp  createdDatetime) {
this.createdDatetime = createdDatetime;
}

public Timestamp getPromisedDatetime() {
return promisedDatetime;
}

public void setPromisedDatetime(Timestamp  promisedDatetime) {
this.promisedDatetime = promisedDatetime;
}

public Timestamp getCurrentPromisedDate() {
return currentPromisedDate;
}

public void setCurrentPromisedDate(Timestamp  currentPromisedDate) {
this.currentPromisedDate = currentPromisedDate;
}

public Boolean getPriority() {
return priority;
}

public void setPriority(Boolean  priority) {
this.priority = priority;
}

public Long getSequenceId() {
return sequenceId;
}

public void setSequenceId(Long  sequenceId) {
this.sequenceId = sequenceId;
}

public Timestamp getOldPickStartDate() {
return oldPickStartDate;
}

public void setOldPickStartDate(Timestamp  oldPickStartDate) {
this.oldPickStartDate = oldPickStartDate;
}


public Map<String, Object> mapAttributeField() {
return OrderItemShipGrpInvResMapper.map(this);
}
}
