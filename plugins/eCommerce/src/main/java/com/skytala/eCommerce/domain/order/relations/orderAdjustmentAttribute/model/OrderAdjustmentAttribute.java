package com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentAttribute.mapper.OrderAdjustmentAttributeMapper;

public class OrderAdjustmentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String orderAdjustmentId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getOrderAdjustmentId() {
return orderAdjustmentId;
}

public void setOrderAdjustmentId(String  orderAdjustmentId) {
this.orderAdjustmentId = orderAdjustmentId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public Long getAttrValue() {
return attrValue;
}

public void setAttrValue(Long  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return OrderAdjustmentAttributeMapper.map(this);
}
}
