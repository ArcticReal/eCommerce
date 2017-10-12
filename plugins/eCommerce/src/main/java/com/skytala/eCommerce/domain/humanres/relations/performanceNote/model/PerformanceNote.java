package com.skytala.eCommerce.domain.humanres.relations.performanceNote.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.mapper.PerformanceNoteMapper;

public class PerformanceNote implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String roleTypeId;
private Timestamp fromDate;
private Timestamp thruDate;
private Timestamp communicationDate;
private String comments;

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

public Timestamp getFromDate() {
return fromDate;
}

public void setFromDate(Timestamp  fromDate) {
this.fromDate = fromDate;
}

public Timestamp getThruDate() {
return thruDate;
}

public void setThruDate(Timestamp  thruDate) {
this.thruDate = thruDate;
}

public Timestamp getCommunicationDate() {
return communicationDate;
}

public void setCommunicationDate(Timestamp  communicationDate) {
this.communicationDate = communicationDate;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PerformanceNoteMapper.map(this);
}
}
