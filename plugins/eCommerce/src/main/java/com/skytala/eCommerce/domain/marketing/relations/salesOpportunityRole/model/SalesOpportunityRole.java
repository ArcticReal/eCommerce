package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.mapper.SalesOpportunityRoleMapper;

public class SalesOpportunityRole implements Serializable{

private static final long serialVersionUID = 1L;
private String salesOpportunityId;
private String partyId;
private String roleTypeId;

public String getSalesOpportunityId() {
return salesOpportunityId;
}

public void setSalesOpportunityId(String  salesOpportunityId) {
this.salesOpportunityId = salesOpportunityId;
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


public Map<String, Object> mapAttributeField() {
return SalesOpportunityRoleMapper.map(this);
}
}
