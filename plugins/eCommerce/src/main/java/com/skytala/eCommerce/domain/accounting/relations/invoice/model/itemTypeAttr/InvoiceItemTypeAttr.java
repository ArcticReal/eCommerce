package com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeAttr;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeAttr.InvoiceItemTypeAttrMapper;

public class InvoiceItemTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceItemTypeId;
private String attrName;
private String description;

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
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
return InvoiceItemTypeAttrMapper.map(this);
}
}
