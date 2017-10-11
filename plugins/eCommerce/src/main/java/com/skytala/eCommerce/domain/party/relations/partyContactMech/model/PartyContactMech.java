package com.skytala.eCommerce.domain.party.relations.partyContactMech.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyContactMech.mapper.PartyContactMechMapper;

public class PartyContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String contactMechId;
private Timestamp fromDate;
private Timestamp thruDate;
private String roleTypeId;
private Boolean allowSolicitation;
private String extension;
private Boolean verified;
private String comments;
private Long yearsWithContactMech;
private Long monthsWithContactMech;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
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

public String getRoleTypeId() {
return roleTypeId;
}

public void setRoleTypeId(String  roleTypeId) {
this.roleTypeId = roleTypeId;
}

public Boolean getAllowSolicitation() {
return allowSolicitation;
}

public void setAllowSolicitation(Boolean  allowSolicitation) {
this.allowSolicitation = allowSolicitation;
}

public String getExtension() {
return extension;
}

public void setExtension(String  extension) {
this.extension = extension;
}

public Boolean getVerified() {
return verified;
}

public void setVerified(Boolean  verified) {
this.verified = verified;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public Long getYearsWithContactMech() {
return yearsWithContactMech;
}

public void setYearsWithContactMech(Long  yearsWithContactMech) {
this.yearsWithContactMech = yearsWithContactMech;
}

public Long getMonthsWithContactMech() {
return monthsWithContactMech;
}

public void setMonthsWithContactMech(Long  monthsWithContactMech) {
this.monthsWithContactMech = monthsWithContactMech;
}


public Map<String, Object> mapAttributeField() {
return PartyContactMechMapper.map(this);
}
}
