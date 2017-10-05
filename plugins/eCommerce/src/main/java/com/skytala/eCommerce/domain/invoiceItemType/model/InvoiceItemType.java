package com.skytala.eCommerce.domain.invoiceItemType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.invoiceItemType.mapper.InvoiceItemTypeMapper;

public class InvoiceItemType implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceItemTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;
private String defaultGlAccountId;

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
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

public String getDefaultGlAccountId() {
return defaultGlAccountId;
}

public void setDefaultGlAccountId(String  defaultGlAccountId) {
this.defaultGlAccountId = defaultGlAccountId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemTypeMapper.map(this);
}
}
