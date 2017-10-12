package com.skytala.eCommerce.domain.accounting.relations.invoice.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.InvoiceMapper;

public class Invoice implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String invoiceTypeId;
private String partyIdFrom;
private String partyId;
private String roleTypeId;
private String statusId;
private String billingAccountId;
private String contactMechId;
private Timestamp invoiceDate;
private Timestamp dueDate;
private Timestamp paidDate;
private String invoiceMessage;
private String referenceNumber;
private String description;
private String currencyUomId;
private String recurrenceInfoId;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getInvoiceTypeId() {
return invoiceTypeId;
}

public void setInvoiceTypeId(String  invoiceTypeId) {
this.invoiceTypeId = invoiceTypeId;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public Timestamp getInvoiceDate() {
return invoiceDate;
}

public void setInvoiceDate(Timestamp  invoiceDate) {
this.invoiceDate = invoiceDate;
}

public Timestamp getDueDate() {
return dueDate;
}

public void setDueDate(Timestamp  dueDate) {
this.dueDate = dueDate;
}

public Timestamp getPaidDate() {
return paidDate;
}

public void setPaidDate(Timestamp  paidDate) {
this.paidDate = paidDate;
}

public String getInvoiceMessage() {
return invoiceMessage;
}

public void setInvoiceMessage(String  invoiceMessage) {
this.invoiceMessage = invoiceMessage;
}

public String getReferenceNumber() {
return referenceNumber;
}

public void setReferenceNumber(String  referenceNumber) {
this.referenceNumber = referenceNumber;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getCurrencyUomId() {
return currencyUomId;
}

public void setCurrencyUomId(String  currencyUomId) {
this.currencyUomId = currencyUomId;
}

public String getRecurrenceInfoId() {
return recurrenceInfoId;
}

public void setRecurrenceInfoId(String  recurrenceInfoId) {
this.recurrenceInfoId = recurrenceInfoId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceMapper.map(this);
}
}
