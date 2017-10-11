package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.mapper.PartyContactMechPurposeMapper;

public class PartyContactMechPurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private String contactMechId;
private String contactMechPurposeTypeId;
private Timestamp fromDate;
private Timestamp thruDate;

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

public String getContactMechPurposeTypeId() {
return contactMechPurposeTypeId;
}

public void setContactMechPurposeTypeId(String  contactMechPurposeTypeId) {
this.contactMechPurposeTypeId = contactMechPurposeTypeId;
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


public Map<String, Object> mapAttributeField() {
return PartyContactMechPurposeMapper.map(this);
}
}
