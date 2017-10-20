package com.skytala.eCommerce.domain.party.relations.party.model.relationship;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.party.mapper.relationship.PartyRelationshipMapper;

public class PartyRelationship implements Serializable{

private static final long serialVersionUID = 1L;
private String partyIdFrom;
private String partyIdTo;
private String roleTypeIdFrom;
private String roleTypeIdTo;
private Timestamp fromDate;
private Timestamp thruDate;
private String statusId;
private String relationshipName;
private String securityGroupId;
private String priorityTypeId;
private String partyRelationshipTypeId;
private String permissionsEnumId;
private String positionTitle;
private String comments;

public String getPartyIdFrom() {
return partyIdFrom;
}

public void setPartyIdFrom(String  partyIdFrom) {
this.partyIdFrom = partyIdFrom;
}

public String getPartyIdTo() {
return partyIdTo;
}

public void setPartyIdTo(String  partyIdTo) {
this.partyIdTo = partyIdTo;
}

public String getRoleTypeIdFrom() {
return roleTypeIdFrom;
}

public void setRoleTypeIdFrom(String  roleTypeIdFrom) {
this.roleTypeIdFrom = roleTypeIdFrom;
}

public String getRoleTypeIdTo() {
return roleTypeIdTo;
}

public void setRoleTypeIdTo(String  roleTypeIdTo) {
this.roleTypeIdTo = roleTypeIdTo;
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

public String getStatusId() {
return statusId;
}

public void setStatusId(String  statusId) {
this.statusId = statusId;
}

public String getRelationshipName() {
return relationshipName;
}

public void setRelationshipName(String  relationshipName) {
this.relationshipName = relationshipName;
}

public String getSecurityGroupId() {
return securityGroupId;
}

public void setSecurityGroupId(String  securityGroupId) {
this.securityGroupId = securityGroupId;
}

public String getPriorityTypeId() {
return priorityTypeId;
}

public void setPriorityTypeId(String  priorityTypeId) {
this.priorityTypeId = priorityTypeId;
}

public String getPartyRelationshipTypeId() {
return partyRelationshipTypeId;
}

public void setPartyRelationshipTypeId(String  partyRelationshipTypeId) {
this.partyRelationshipTypeId = partyRelationshipTypeId;
}

public String getPermissionsEnumId() {
return permissionsEnumId;
}

public void setPermissionsEnumId(String  permissionsEnumId) {
this.permissionsEnumId = permissionsEnumId;
}

public String getPositionTitle() {
return positionTitle;
}

public void setPositionTitle(String  positionTitle) {
this.positionTitle = positionTitle;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}


public Map<String, Object> mapAttributeField() {
return PartyRelationshipMapper.map(this);
}
}
