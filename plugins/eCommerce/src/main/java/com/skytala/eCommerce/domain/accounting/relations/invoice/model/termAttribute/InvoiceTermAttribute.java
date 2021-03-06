package com.skytala.eCommerce.domain.accounting.relations.invoice.model.termAttribute;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.termAttribute.InvoiceTermAttributeMapper;

public class InvoiceTermAttribute implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceTermId;
private String attrName;
private String attrValue;
private String attrDescription;

public String getInvoiceTermId() {
return invoiceTermId;
}

public void setInvoiceTermId(String  invoiceTermId) {
this.invoiceTermId = invoiceTermId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getAttrValue() {
return attrValue;
}

public void setAttrValue(String  attrValue) {
this.attrValue = attrValue;
}

public String getAttrDescription() {
return attrDescription;
}

public void setAttrDescription(String  attrDescription) {
this.attrDescription = attrDescription;
}


public Map<String, Object> mapAttributeField() {
return InvoiceTermAttributeMapper.map(this);
}
}
