package com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.role.BillingAccountRoleMapper;

public class BillingAccountRole implements Serializable{

private static final long serialVersionUID = 1L;
private String billingAccountId;
private String partyId;
private String roleTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

public String getBillingAccountId() {
return billingAccountId;
}

public void setBillingAccountId(String  billingAccountId) {
this.billingAccountId = billingAccountId;
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

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}


public Map<String, Object> mapAttributeField() {
return BillingAccountRoleMapper.map(this);
}
}