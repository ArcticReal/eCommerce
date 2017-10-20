package com.skytala.eCommerce.domain.order.relations.orderItem.model.groupOrder;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.groupOrder.OrderItemGroupOrderMapper;

public class OrderItemGroupOrder implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemSeqId;
private String groupOrderId;

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

public String getGroupOrderId() {
return groupOrderId;
}

public void setGroupOrderId(String  groupOrderId) {
this.groupOrderId = groupOrderId;
}


public Map<String, Object> mapAttributeField() {
return OrderItemGroupOrderMapper.map(this);
}
}
