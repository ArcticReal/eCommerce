package com.skytala.eCommerce.domain.order.relations.orderContactMech.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderContactMech.mapper.OrderContactMechMapper;

public class OrderContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String orderId;
private String contactMechPurposeTypeId;
private String contactMechId;

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getContactMechPurposeTypeId() {
return contactMechPurposeTypeId;
}

public void setContactMechPurposeTypeId(String  contactMechPurposeTypeId) {
this.contactMechPurposeTypeId = contactMechPurposeTypeId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}


public Map<String, Object> mapAttributeField() {
return OrderContactMechMapper.map(this);
}
}
