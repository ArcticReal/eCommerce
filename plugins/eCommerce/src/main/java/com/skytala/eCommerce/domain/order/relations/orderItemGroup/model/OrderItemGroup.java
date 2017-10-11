package com.skytala.eCommerce.domain.order.relations.orderItemGroup.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemGroup.mapper.OrderItemGroupMapper;

public class OrderItemGroup implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String orderItemGroupSeqId;
private String parentGroupSeqId;
private String groupName;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemGroupSeqId() {
return orderItemGroupSeqId;
}

public void setOrderItemGroupSeqId(String  orderItemGroupSeqId) {
this.orderItemGroupSeqId = orderItemGroupSeqId;
}

public String getParentGroupSeqId() {
return parentGroupSeqId;
}

public void setParentGroupSeqId(String  parentGroupSeqId) {
this.parentGroupSeqId = parentGroupSeqId;
}

public String getGroupName() {
return groupName;
}

public void setGroupName(String  groupName) {
this.groupName = groupName;
}


public Map<String, Object> mapAttributeField() {
return OrderItemGroupMapper.map(this);
}
}
