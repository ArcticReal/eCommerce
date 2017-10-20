package com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssocType.InvoiceItemAssocTypeMapper;

public class InvoiceItemAssocType implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceItemAssocTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getInvoiceItemAssocTypeId() {
return invoiceItemAssocTypeId;
}

public void setInvoiceItemAssocTypeId(String  invoiceItemAssocTypeId) {
this.invoiceItemAssocTypeId = invoiceItemAssocTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemAssocTypeMapper.map(this);
}
}
