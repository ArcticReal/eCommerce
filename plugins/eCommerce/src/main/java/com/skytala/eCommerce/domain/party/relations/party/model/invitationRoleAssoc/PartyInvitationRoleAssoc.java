package com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc.PartyInvitationRoleAssocMapper;

public class PartyInvitationRoleAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String partyInvitationId;
private String roleTypeId;

public String getPartyInvitationId() {
return partyInvitationId;
}

public void setPartyInvitationId(String  partyInvitationId) {
this.partyInvitationId = partyInvitationId;
}

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}


public Map<String, Object> mapAttributeField() {
return PartyInvitationRoleAssocMapper.map(this);
}
}
