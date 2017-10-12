package com.skytala.eCommerce.domain.humanres.relations.payrollPreference.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.payrollPreference.mapper.PayrollPreferenceMapper;

public class PayrollPreference implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;
private String payrollPreferenceSeqId;
private String deductionTypeId;
private String paymentMethodTypeId;
private String periodTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal percentage;
private BigDecimal flatAmount;
private String routingNumber;
private String accountNumber;
private String bankName;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public String getPayrollPreferenceSeqId() {
return payrollPreferenceSeqId;
}

public void setPayrollPreferenceSeqId(String  payrollPreferenceSeqId) {
this.payrollPreferenceSeqId = payrollPreferenceSeqId;
}

public String getDeductionTypeId() {
return deductionTypeId;
}

public void setDeductionTypeId(String  deductionTypeId) {
this.deductionTypeId = deductionTypeId;
}

public String getPaymentMethodTypeId() {
return paymentMethodTypeId;
}

public void setPaymentMethodTypeId(String  paymentMethodTypeId) {
this.paymentMethodTypeId = paymentMethodTypeId;
}

public String getPeriodTypeId() {
return periodTypeId;
}

public void setPeriodTypeId(String  periodTypeId) {
this.periodTypeId = periodTypeId;
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

public BigDecimal getPercentage() {
return percentage;
}

public void setPercentage(BigDecimal  percentage) {
this.percentage = percentage;
}

public BigDecimal getFlatAmount() {
return flatAmount;
}

public void setFlatAmount(BigDecimal  flatAmount) {
this.flatAmount = flatAmount;
}

public String getRoutingNumber() {
return routingNumber;
}

public void setRoutingNumber(String  routingNumber) {
this.routingNumber = routingNumber;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String  accountNumber) {
this.accountNumber = accountNumber;
}

public String getBankName() {
return bankName;
}

public void setBankName(String  bankName) {
this.bankName = bankName;
}


public Map<String, Object> mapAttributeField() {
return PayrollPreferenceMapper.map(this);
}
}
