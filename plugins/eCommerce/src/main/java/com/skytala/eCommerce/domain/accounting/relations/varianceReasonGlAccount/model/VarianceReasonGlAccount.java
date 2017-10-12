package com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.mapper.VarianceReasonGlAccountMapper;

public class VarianceReasonGlAccount implements Serializable{

private static final long serialVersionUID = 1L;
private String varianceReasonId;
private String organizationPartyId;
private String glAccountId;

public String getVarianceReasonId() {
return varianceReasonId;
}

public void setVarianceReasonId(String  varianceReasonId) {
this.varianceReasonId = varianceReasonId;
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
return VarianceReasonGlAccountMapper.map(this);
}
}
