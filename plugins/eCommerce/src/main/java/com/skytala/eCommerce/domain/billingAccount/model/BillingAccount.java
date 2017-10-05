package com.skytala.eCommerce.domain.billingAccount.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.billingAccount.mapper.BillingAccountMapper;

public class BillingAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String billingAccountId;
private BigDecimal accountLimit;
private String accountCurrencyUomId;
private String contactMechId;
private Timestamp fromDate;
private Timestamp thruDate;
private String description;
private String externalAccountId;

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public BigDecimal getAccountLimit() {
return accountLimit;
}

public void setAccountLimit(BigDecimal  accountLimit) {
this.accountLimit = accountLimit;
}

public String getAccountCurrencyUomId() {
return accountCurrencyUomId;
}

public void setAccountCurrencyUomId(String  accountCurrencyUomId) {
this.accountCurrencyUomId = accountCurrencyUomId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getExternalAccountId() {
return externalAccountId;
}

public void setExternalAccountId(String  externalAccountId) {
this.externalAccountId = externalAccountId;
}


public Map<String, Object> mapAttributeField() {
return BillingAccountMapper.map(this);
}
}
