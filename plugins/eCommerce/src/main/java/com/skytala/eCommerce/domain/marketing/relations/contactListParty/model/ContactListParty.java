package com.skytala.eCommerce.domain.marketing.relations.contactListParty.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.mapper.ContactListPartyMapper;

public class ContactListParty implements Serializable{

private static final long serialVersionUID = 1L;
private String contactListId;
private String partyId;
private Timestamp fromDate;
private Timestamp thruDate;
private String statusId;
private String preferredContactMechId;

public String getContactListId() {
return contactListId;
}

public void setContactListId(String  contactListId) {
this.contactListId = contactListId;
}

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
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

public String getPreferredContactMechId() {
return preferredContactMechId;
}

public void setPreferredContactMechId(String  preferredContactMechId) {
this.preferredContactMechId = preferredContactMechId;
}


public Map<String, Object> mapAttributeField() {
return ContactListPartyMapper.map(this);
}
}
