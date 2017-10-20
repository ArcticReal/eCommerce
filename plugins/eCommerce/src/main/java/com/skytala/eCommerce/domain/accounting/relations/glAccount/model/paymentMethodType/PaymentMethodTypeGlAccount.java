package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentMethodType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentMethodType.PaymentMethodTypeGlAccountMapper;

public class PaymentMethodTypeGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodTypeId;
private String organizationPartyId;
private String glAccountId;

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}


public Map<String, Object> mapAttributeField() {
return PaymentMethodTypeGlAccountMapper.map(this);
}
}
