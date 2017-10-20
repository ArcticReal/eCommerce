package com.skytala.eCommerce.domain.accounting.relations.invoice.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.role.InvoiceRoleMapper;

public class InvoiceRole implements Serializable{

private static final long serialVersionUID = 1L;
private String invoiceId;
private String partyId;
private String roleTypeId;
private Timestamp datetimePerformed;
private BigDecimal percentage;

public String getInvoiceId() {
return invoiceId;
}

public void setInvoiceId(String  invoiceId) {
this.invoiceId = invoiceId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public Timestamp getDatetimePerformed() {
return datetimePerformed;
}

public void setDatetimePerformed(Timestamp  datetimePerformed) {
this.datetimePerformed = datetimePerformed;
}

public BigDecimal getPercentage() {
return percentage;
}

public void setPercentage(BigDecimal  percentage) {
this.percentage = percentage;
}


public Map<String, Object> mapAttributeField() {
return InvoiceRoleMapper.map(this);
}
}
