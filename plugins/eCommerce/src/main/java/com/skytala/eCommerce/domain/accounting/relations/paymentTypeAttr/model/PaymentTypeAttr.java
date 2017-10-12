package com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentTypeAttr.mapper.PaymentTypeAttrMapper;

public class PaymentTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentTypeId;
private String attrName;
private String description;

public String getPaymentTypeId() {
return paymentTypeId;
}

public void setPaymentTypeId(String  paymentTypeId) {
this.paymentTypeId = paymentTypeId;
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
return PaymentTypeAttrMapper.map(this);
}
}
