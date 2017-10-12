package com.skytala.eCommerce.domain.accounting.relations.eftAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.mapper.EftAccountMapper;

public class EftAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentMethodId;
private String bankName;
private String routingNumber;
private String accountType;
private String accountNumber;
private String nameOnAccount;
private String companyNameOnAccount;
private String contactMechId;
private Long yearsAtBank;

public String getPaymentMethodId() {
return paymentMethodId;
}

public void setPaymentMethodId(String  paymentMethodId) {
this.paymentMethodId = paymentMethodId;
}

public String getBankName() {
return bankName;
}

public void setBankName(String  bankName) {
this.bankName = bankName;
}

public String getRoutingNumber() {
return routingNumber;
}

public void setRoutingNumber(String  routingNumber) {
this.routingNumber = routingNumber;
}

public String getAccountType() {
return accountType;
}

public void setAccountType(String  accountType) {
this.accountType = accountType;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String  accountNumber) {
this.accountNumber = accountNumber;
}

public String getNameOnAccount() {
return nameOnAccount;
}

public void setNameOnAccount(String  nameOnAccount) {
this.nameOnAccount = nameOnAccount;
}

public String getCompanyNameOnAccount() {
return companyNameOnAccount;
}

public void setCompanyNameOnAccount(String  companyNameOnAccount) {
this.companyNameOnAccount = companyNameOnAccount;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public Long getYearsAtBank() {
return yearsAtBank;
}

public void setYearsAtBank(Long  yearsAtBank) {
this.yearsAtBank = yearsAtBank;
}


public Map<String, Object> mapAttributeField() {
return EftAccountMapper.map(this);
}
}
