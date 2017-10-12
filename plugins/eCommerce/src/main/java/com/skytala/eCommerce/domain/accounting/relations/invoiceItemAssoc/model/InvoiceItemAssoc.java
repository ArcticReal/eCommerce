package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssoc.mapper.InvoiceItemAssocMapper;

public class InvoiceItemAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceIdFrom;
private String invoiceItemSeqIdFrom;
private String invoiceIdTo;
private String invoiceItemSeqIdTo;
private String invoiceItemAssocTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private String partyIdFrom;
private String partyIdTo;
private BigDecimal quantity;
private BigDecimal amount;

public String getInvoiceIdFrom() {
return invoiceIdFrom;
}

public void setInvoiceIdFrom(String  invoiceIdFrom) {
this.invoiceIdFrom = invoiceIdFrom;
}

public String getInvoiceItemSeqIdFrom() {
return invoiceItemSeqIdFrom;
}

public void setInvoiceItemSeqIdFrom(String  invoiceItemSeqIdFrom) {
this.invoiceItemSeqIdFrom = invoiceItemSeqIdFrom;
}

public String getInvoiceIdTo() {
return invoiceIdTo;
}

public void setInvoiceIdTo(String  invoiceIdTo) {
this.invoiceIdTo = invoiceIdTo;
}

public String getInvoiceItemSeqIdTo() {
return invoiceItemSeqIdTo;
}

public void setInvoiceItemSeqIdTo(String  invoiceItemSeqIdTo) {
this.invoiceItemSeqIdTo = invoiceItemSeqIdTo;
}

public String getInvoiceItemAssocTypeId() {
return invoiceItemAssocTypeId;
}

public void setInvoiceItemAssocTypeId(String  invoiceItemAssocTypeId) {
this.invoiceItemAssocTypeId = invoiceItemAssocTypeId;
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

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public BigDecimal getQuantity() {
return quantity;
}

public void setQuantity(BigDecimal  quantity) {
this.quantity = quantity;
}

public BigDecimal getAmount() {
return amount;
}

public void setAmount(BigDecimal  amount) {
this.amount = amount;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemAssocMapper.map(this);
}
}
