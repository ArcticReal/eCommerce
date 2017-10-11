package com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.mapper.OrderItemShipGroupAssocMapper;

public class OrderItemShipGroupAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private BigDecimal quantity;
private BigDecimal cancelQuantity;

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
return OrderItemShipGroupAssocMapper.map(this);
}
}
