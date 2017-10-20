package com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contactMech.InvoiceContactMechMapper;

public class InvoiceContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String contactMechPurposeTypeId;
private String contactMechId;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getContactMechPurposeTypeId() {
return contactMechPurposeTypeId;
}

public void setContactMechPurposeTypeId(String  contactMechPurposeTypeId) {
this.contactMechPurposeTypeId = contactMechPurposeTypeId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceContactMechMapper.map(this);
}
}
