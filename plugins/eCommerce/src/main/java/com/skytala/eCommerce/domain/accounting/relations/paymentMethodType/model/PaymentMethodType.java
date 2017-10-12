package com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.mapper.PaymentMethodTypeMapper;

public class PaymentMethodType implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodTypeId;
private String description;
private String defaultGlAccountId;

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getDefaultGlAccountId() {
return defaultGlAccountId;
}

public void setDefaultGlAccountId(String  defaultGlAccountId) {
this.defaultGlAccountId = defaultGlAccountId;
}


public Map<String, Object> mapAttributeField() {
return PaymentMethodTypeMapper.map(this);
}
}
