package com.skytala.eCommerce.domain.party.relations.communicationEvent.model.role;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.role.CommunicationEventRoleMapper;

public class CommunicationEventRole implements Serializable{

private static final long serialVersionUID = 1L;
private String communicationEventId;
private String partyId;
private String roleTypeId;
private String contactMechId;
private String statusId;

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
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

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventRoleMapper.map(this);
}
}
