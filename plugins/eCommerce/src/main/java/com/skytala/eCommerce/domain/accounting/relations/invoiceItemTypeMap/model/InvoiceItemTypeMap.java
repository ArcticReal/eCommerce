package com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemTypeMap.mapper.InvoiceItemTypeMapMapper;

public class InvoiceItemTypeMap implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceItemMapKey;
private String invoiceTypeId;
private String invoiceItemTypeId;

public String getInvoiceItemMapKey() {
return invoiceItemMapKey;
}

public void setInvoiceItemMapKey(String  invoiceItemMapKey) {
this.invoiceItemMapKey = invoiceItemMapKey;
}

public String getInvoiceTypeId() {
return invoiceTypeId;
}

public void setInvoiceTypeId(String  invoiceTypeId) {
this.invoiceTypeId = invoiceTypeId;
}

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemTypeMapMapper.map(this);
}
}
