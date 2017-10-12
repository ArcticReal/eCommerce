package com.skytala.eCommerce.domain.accounting.relations.paymentMethod.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethod.mapper.PaymentMethodMapper;

public class PaymentMethod implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodId;
private String paymentMethodTypeId;
private String partyId;
private String glAccountId;
private String finAccountId;
private String description;
private Timestamp fromDate;
private Timestamp thruDate;

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}

public String getFinAccountId() {
return finAccountId;
}

public void setFinAccountId(String  finAccountId) {
this.finAccountId = finAccountId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return PaymentMethodMapper.map(this);
}
}
