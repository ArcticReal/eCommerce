package com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.attribute.PaymentAttributeMapper;

public class PaymentAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentId;
private String attrName;
private Long attrValue;
private String attrDescription;

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
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
return PaymentAttributeMapper.map(this);
}
}
