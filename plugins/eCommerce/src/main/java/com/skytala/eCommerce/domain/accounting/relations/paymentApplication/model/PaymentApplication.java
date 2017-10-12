package com.skytala.eCommerce.domain.accounting.relations.paymentApplication.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.paymentApplication.mapper.PaymentApplicationMapper;

public class PaymentApplication implements Serializable{

private static final long serialVersionUID = 1L;
private String paymentApplicationId;
private String paymentId;
private String invoiceId;
private String invoiceItemSeqId;
private String billingAccountId;
private String overrideGlAccountId;
private String toPaymentId;
private String taxAuthGeoId;
private BigDecimal amountApplied;

public String getPaymentApplicationId() {
return paymentApplicationId;
}

public void setPaymentApplicationId(String  paymentApplicationId) {
this.paymentApplicationId = paymentApplicationId;
}

public String getPaymentId() {
return paymentId;
}

public void setPaymentId(String  paymentId) {
this.paymentId = paymentId;
}

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceItemSeqId() {
return invoiceItemSeqId;
}

public void setInvoiceItemSeqId(String  invoiceItemSeqId) {
this.invoiceItemSeqId = invoiceItemSeqId;
}

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public String getOverrideGlAccountId() {
return overrideGlAccountId;
}

public void setOverrideGlAccountId(String  overrideGlAccountId) {
this.overrideGlAccountId = overrideGlAccountId;
}

public String getToPaymentId() {
return toPaymentId;
}

public void setToPaymentId(String  toPaymentId) {
this.toPaymentId = toPaymentId;
}

public String getTaxAuthGeoId() {
return taxAuthGeoId;
}

public void setTaxAuthGeoId(String  taxAuthGeoId) {
this.taxAuthGeoId = taxAuthGeoId;
}

public BigDecimal getAmountApplied() {
return amountApplied;
}

public void setAmountApplied(BigDecimal  amountApplied) {
this.amountApplied = amountApplied;
}


public Map<String, Object> mapAttributeField() {
return PaymentApplicationMapper.map(this);
}
}
