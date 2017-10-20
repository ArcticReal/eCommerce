package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.paymentTypeMap;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.paymentTypeMap.PaymentGlAccountTypeMapMapper;

public class PaymentGlAccountTypeMap implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentTypeId;
private String organizationPartyId;
private String glAccountTypeId;

public String getPaymentTypeId() {
return paymentTypeId;
}

public void setPaymentTypeId(String  paymentTypeId) {
this.paymentTypeId = paymentTypeId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getGlAccountTypeId() {
return glAccountTypeId;
}

public void setGlAccountTypeId(String  glAccountTypeId) {
this.glAccountTypeId = glAccountTypeId;
}


public Map<String, Object> mapAttributeField() {
return PaymentGlAccountTypeMapMapper.map(this);
}
}
