package com.skytala.eCommerce.domain.product.relations.facilityGroupRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.relations.facilityGroupRole.mapper.FacilityGroupRoleMapper;

public class FacilityGroupRole implements Serializable{

private static final long serialVersionUID = 1L;
private String facilityGroupId;
private String partyId;
private String roleTypeId;

public String getFacilityGroupId() {
return facilityGroupId;
}

public void setFacilityGroupId(String  facilityGroupId) {
this.facilityGroupId = facilityGroupId;
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
return FacilityGroupRoleMapper.map(this);
}
}
