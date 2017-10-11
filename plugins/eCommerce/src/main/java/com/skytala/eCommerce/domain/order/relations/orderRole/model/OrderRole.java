package com.skytala.eCommerce.domain.order.relations.orderRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderRole.mapper.OrderRoleMapper;

public class OrderRole implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String partyId;
private String roleTypeId;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return OrderRoleMapper.map(this);
}
}
