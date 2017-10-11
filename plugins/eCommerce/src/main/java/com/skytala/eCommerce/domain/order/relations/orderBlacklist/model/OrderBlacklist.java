package com.skytala.eCommerce.domain.order.relations.orderBlacklist.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.OrderBlacklistMapper;

public class OrderBlacklist implements Serializable{

private static final long serialVersionUID = 1L;
private String blacklistString;
private String orderBlacklistTypeId;

public String getBlacklistString() {
return blacklistString;
}

public void setBlacklistString(String  blacklistString) {
this.blacklistString = blacklistString;
}

public String getOrderBlacklistTypeId() {
return orderBlacklistTypeId;
}

public void setOrderBlacklistTypeId(String  orderBlacklistTypeId) {
this.orderBlacklistTypeId = orderBlacklistTypeId;
}


public Map<String, Object> mapAttributeField() {
return OrderBlacklistMapper.map(this);
}
}
