package com.skytala.eCommerce.domain.accounting.relations.invoice.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.type.InvoiceTypeMapper;

public class InvoiceType implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getInvoiceTypeId() {
return invoiceTypeId;
}

public void setInvoiceTypeId(String  invoiceTypeId) {
this.invoiceTypeId = invoiceTypeId;
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
return InvoiceTypeMapper.map(this);
}
}
