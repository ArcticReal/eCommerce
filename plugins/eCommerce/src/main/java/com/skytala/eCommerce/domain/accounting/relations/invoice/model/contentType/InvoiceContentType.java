package com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contentType.InvoiceContentTypeMapper;

public class InvoiceContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceContentTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getInvoiceContentTypeId() {
return invoiceContentTypeId;
}

public void setInvoiceContentTypeId(String  invoiceContentTypeId) {
this.invoiceContentTypeId = invoiceContentTypeId;
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
return InvoiceContentTypeMapper.map(this);
}
}
