package com.skytala.eCommerce.domain.invoiceTerm.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.invoiceTerm.mapper.InvoiceTermMapper;

public class InvoiceTerm implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceTermId;
private String termTypeId;
private String invoiceId;
private String invoiceItemSeqId;
private BigDecimal termValue;
private Long termDays;
private String textValue;
private String description;
private String uomId;

public String getInvoiceTermId() {
return invoiceTermId;
}

public void setInvoiceTermId(String  invoiceTermId) {
this.invoiceTermId = invoiceTermId;
}

public String getTermTypeId() {
return termTypeId;
}

public void setTermTypeId(String  termTypeId) {
this.termTypeId = termTypeId;
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

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getUomId() {
return uomId;
}

public void setUomId(String  uomId) {
this.uomId = uomId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceTermMapper.map(this);
}
}
