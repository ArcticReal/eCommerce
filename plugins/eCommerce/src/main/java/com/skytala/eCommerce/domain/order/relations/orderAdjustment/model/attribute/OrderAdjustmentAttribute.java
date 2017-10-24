package com.skytala.eCommerce.domain.order.relations.orderAdjustment.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderAdjustment.mapper.attribute.OrderAdjustmentAttributeMapper;

public class OrderAdjustmentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String orderAdjustmentId;
private String attrName;
private String attrValue;
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

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
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
