package com.skytala.eCommerce.domain.accounting.relations.glAccount.model.typeDefault;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.typeDefault.GlAccountTypeDefaultMapper;

public class GlAccountTypeDefault implements Serializable{

private static final long serialVersionUID = 1L;
private String glAccountTypeId;
private String organizationPartyId;
private String glAccountId;

public String getGlAccountTypeId() {
return glAccountTypeId;
}

public void setGlAccountTypeId(String  glAccountTypeId) {
this.glAccountTypeId = glAccountTypeId;
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
return GlAccountTypeDefaultMapper.map(this);
}
}
