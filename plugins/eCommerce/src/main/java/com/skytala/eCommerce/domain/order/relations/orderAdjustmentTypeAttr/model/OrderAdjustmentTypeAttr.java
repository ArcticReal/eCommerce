package com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderAdjustmentTypeAttr.mapper.OrderAdjustmentTypeAttrMapper;

public class OrderAdjustmentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String orderAdjustmentTypeId;
private String attrName;
private String description;

public String getOrderAdjustmentTypeId() {
return orderAdjustmentTypeId;
}

public void setOrderAdjustmentTypeId(String  orderAdjustmentTypeId) {
this.orderAdjustmentTypeId = orderAdjustmentTypeId;
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
return OrderAdjustmentTypeAttrMapper.map(this);
}
}
