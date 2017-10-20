package com.skytala.eCommerce.domain.party.relations.party.model.invitation;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.invitation.PartyInvitationMapper;

public class PartyInvitation implements Serializable{

private static final long serialVersionUID = 1L;
private String partyInvitationId;
private String partyIdFrom;
private String partyId;
private String toName;
private String emailAddress;
private String statusId;
private Timestamp lastInviteDate;

public String getPartyInvitationId() {
return partyInvitationId;
}

public void setPartyInvitationId(String  partyInvitationId) {
this.partyInvitationId = partyInvitationId;
}

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getToName() {
return toName;
}

public void setToName(String  toName) {
this.toName = toName;
}

public String getEmailAddress() {
return emailAddress;
}

public void setEmailAddress(String  emailAddress) {
this.emailAddress = emailAddress;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public Timestamp getLastInviteDate() {
return lastInviteDate;
}

public void setLastInviteDate(Timestamp  lastInviteDate) {
this.lastInviteDate = lastInviteDate;
}


public Map<String, Object> mapAttributeField() {
return PartyInvitationMapper.map(this);
}
}
