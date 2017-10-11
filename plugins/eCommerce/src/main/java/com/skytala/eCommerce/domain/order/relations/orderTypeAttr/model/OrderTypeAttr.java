package com.skytala.eCommerce.domain.order.relations.orderTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderTypeAttr.mapper.OrderTypeAttrMapper;

public class OrderTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String orderTypeId;
private String attrName;
private String description;

public String getOrderTypeId() {
return orderTypeId;
}

public void setOrderTypeId(String  orderTypeId) {
this.orderTypeId = orderTypeId;
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
return OrderTypeAttrMapper.map(this);
}
}
