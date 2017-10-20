package com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitationGroupAssoc.PartyInvitationGroupAssocMapper;

public class PartyInvitationGroupAssoc implements Serializable{

private static final long serialVersionUID = 1L;
private String partyInvitationId;
private String partyIdTo;

public String getPartyInvitationId() {
return partyInvitationId;
}

public void setPartyInvitationId(String  partyInvitationId) {
this.partyInvitationId = partyInvitationId;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}


public Map<String, Object> mapAttributeField() {
return PartyInvitationGroupAssocMapper.map(this);
}
}
