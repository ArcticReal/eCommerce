package com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderItemTypeAttr.mapper.OrderItemTypeAttrMapper;

public class OrderItemTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String orderItemTypeId;
private String attrName;
private String description;

public String getOrderItemTypeId() {
return orderItemTypeId;
}

public void setOrderItemTypeId(String  orderItemTypeId) {
this.orderItemTypeId = orderItemTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return OrderItemTypeAttrMapper.map(this);
}
}
