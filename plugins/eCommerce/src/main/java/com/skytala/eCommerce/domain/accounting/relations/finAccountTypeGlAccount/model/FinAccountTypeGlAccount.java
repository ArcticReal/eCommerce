package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.mapper.FinAccountTypeGlAccountMapper;

public class FinAccountTypeGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String finAccountTypeId;
private String organizationPartyId;
private String glAccountId;

public String getFinAccountTypeId() {
return finAccountTypeId;
}

public void setFinAccountTypeId(String  finAccountTypeId) {
this.finAccountTypeId = finAccountTypeId;
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
return FinAccountTypeGlAccountMapper.map(this);
}
}
