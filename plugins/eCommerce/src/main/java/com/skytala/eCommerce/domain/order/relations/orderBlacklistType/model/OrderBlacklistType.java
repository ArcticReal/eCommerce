package com.skytala.eCommerce.domain.order.relations.orderBlacklistType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderBlacklistType.mapper.OrderBlacklistTypeMapper;

public class OrderBlacklistType implements Serializable{

private static final long serialVersionUID = 1L;
private String orderBlacklistTypeId;
private String description;

public String getOrderBlacklistTypeId() {
return orderBlacklistTypeId;
}

public void setOrderBlacklistTypeId(String  orderBlacklistTypeId) {
this.orderBlacklistTypeId = orderBlacklistTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return OrderBlacklistTypeMapper.map(this);
}
}
