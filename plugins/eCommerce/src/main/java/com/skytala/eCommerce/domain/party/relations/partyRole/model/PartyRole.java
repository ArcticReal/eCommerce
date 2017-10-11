package com.skytala.eCommerce.domain.party.relations.partyRole.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyRole.mapper.PartyRoleMapper;

public class PartyRole implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;

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
return PartyRoleMapper.map(this);
}
}
