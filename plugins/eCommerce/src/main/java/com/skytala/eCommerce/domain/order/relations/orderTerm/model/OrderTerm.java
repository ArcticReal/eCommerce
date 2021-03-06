package com.skytala.eCommerce.domain.order.relations.orderTerm.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.OrderTermMapper;

public class OrderTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String termTypeId;
private String orderId;
private String orderItemSeqId;
private BigDecimal termValue;
private Long termDays;
private String textValue;
private String description;
private String uomId;

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String  orderId) {
this.orderId = orderId;
}

public String getOrderItemSeqId() {
return orderItemSeqId;
}

public void setOrderItemSeqId(String  orderItemSeqId) {
this.orderItemSeqId = orderItemSeqId;
}

public BigDecimal getTermValue() {
return termValue;
}

public void setTermValue(BigDecimal  termValue) {
this.termValue = termValue;
}

public Long getTermDays() {
return termDays;
}

public void setTermDays(Long  termDays) {
this.termDays = termDays;
}

public String getTextValue() {
return textValue;
}

public void setTextValue(String  textValue) {
this.textValue = textValue;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}


public Map<String, Object> mapAttributeField() {
return OrderTermMapper.map(this);
}
}
