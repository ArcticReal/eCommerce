package com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTypeAttr.mapper.InvoiceTypeAttrMapper;

public class InvoiceTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceTypeId;
private String attrName;
private String description;

public String getInvoiceTypeId() {
return invoiceTypeId;
}

public void setInvoiceTypeId(String  invoiceTypeId) {
this.invoiceTypeId = invoiceTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InvoiceTypeAttrMapper.map(this);
}
}
