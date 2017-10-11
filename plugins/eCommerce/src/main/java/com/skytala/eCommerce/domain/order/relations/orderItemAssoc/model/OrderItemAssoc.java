package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.mapper.OrderItemAssocMapper;

public class OrderItemAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String shipGroupSeqId;
private String toOrderId;
private String toOrderItemSeqId;
private String toShipGroupSeqId;
private String orderItemAssocTypeId;
private BigDecimal quantity;

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

public String getToOrderId() {
return toOrderId;
}

public void setToOrderId(String  toOrderId) {
this.toOrderId = toOrderId;
}

public String getToOrderItemSeqId() {
return toOrderItemSeqId;
}

public void setToOrderItemSeqId(String  toOrderItemSeqId) {
this.toOrderItemSeqId = toOrderItemSeqId;
}

public String getToShipGroupSeqId() {
return toShipGroupSeqId;
}

public void setToShipGroupSeqId(String  toShipGroupSeqId) {
this.toShipGroupSeqId = toShipGroupSeqId;
}

public String getOrderItemAssocTypeId() {
return orderItemAssocTypeId;
}

public void setOrderItemAssocTypeId(String  orderItemAssocTypeId) {
this.orderItemAssocTypeId = orderItemAssocTypeId;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}


public Map<String, Object> mapAttributeField() {
return OrderItemAssocMapper.map(this);
}
}
