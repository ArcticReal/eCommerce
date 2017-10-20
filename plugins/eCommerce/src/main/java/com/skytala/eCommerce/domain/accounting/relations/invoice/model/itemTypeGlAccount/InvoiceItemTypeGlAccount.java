package com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeGlAccount;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeGlAccount.InvoiceItemTypeGlAccountMapper;

public class InvoiceItemTypeGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceItemTypeId;
private String organizationPartyId;
private String glAccountId;

public String getInvoiceItemTypeId() {
return invoiceItemTypeId;
}

public void setInvoiceItemTypeId(String  invoiceItemTypeId) {
this.invoiceItemTypeId = invoiceItemTypeId;
}

public String getOrganizationPartyId() {
return organizationPartyId;
}

public void setOrganizationPartyId(String  organizationPartyId) {
this.organizationPartyId = organizationPartyId;
}

public String getGlAccountId() {
return glAccountId;
}

public void setGlAccountId(String  glAccountId) {
this.glAccountId = glAccountId;
}


public Map<String, Object> mapAttributeField() {
return InvoiceItemTypeGlAccountMapper.map(this);
}
}
