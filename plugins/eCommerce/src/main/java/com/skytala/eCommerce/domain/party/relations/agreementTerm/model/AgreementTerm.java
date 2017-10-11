package com.skytala.eCommerce.domain.party.relations.agreementTerm.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.agreementTerm.mapper.AgreementTermMapper;

public class AgreementTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String agreementTermId;
private String termTypeId;
private String agreementId;
private String agreementItemSeqId;
private String invoiceItemTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private BigDecimal termValue;
private Long termDays;
private String textValue;
private BigDecimal minQuantity;
private BigDecimal maxQuantity;
private String description;

public String getAgreementTermId() {
return agreementTermId;
}

public void setAgreementTermId(String  agreementTermId) {
this.agreementTermId = agreementTermId;
}

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
}

public String getAgreementId() {
return agreementId;
}

public void setAgreementId(String  agreementId) {
this.agreementId = agreementId;
}

public String getAgreementItemSeqId() {
return agreementItemSeqId;
}

public void setAgreementItemSeqId(String  agreementItemSeqId) {
this.agreementItemSeqId = agreementItemSeqId;
}

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
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

public BigDecimal getTermValue() {
return termValue;
}

public void setTermValue(BigDecimal  termValue) {
this.termValue = termValue;
}

public Long getTermDays() {
return termDays;
}

public void setTermDays(Long  termDays) {
this.termDays = termDays;
}

public String getTextValue() {
return textValue;
}

public void setTextValue(String  textValue) {
this.textValue = textValue;
}

public BigDecimal getMinQuantity() {
return minQuantity;
}

public void setMinQuantity(BigDecimal  minQuantity) {
this.minQuantity = minQuantity;
}

public BigDecimal getMaxQuantity() {
return maxQuantity;
}

public void setMaxQuantity(BigDecimal  maxQuantity) {
this.maxQuantity = maxQuantity;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return AgreementTermMapper.map(this);
}
}
