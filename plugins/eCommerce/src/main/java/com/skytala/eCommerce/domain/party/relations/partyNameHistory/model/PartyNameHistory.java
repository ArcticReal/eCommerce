package com.skytala.eCommerce.domain.party.relations.partyNameHistory.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.partyNameHistory.mapper.PartyNameHistoryMapper;

public class PartyNameHistory implements Serializable{

private static final long serialVersionUID = 1L;
private String partyId;
private Timestamp changeDate;
private String groupName;
private String firstName;
private String middleName;
private String lastName;
private String personalTitle;
private String suffix;

public String getPartyId() {
return partyId;
}

public void setPartyId(String  partyId) {
this.partyId = partyId;
}

public Timestamp getChangeDate() {
return changeDate;
}

public void setChangeDate(Timestamp  changeDate) {
this.changeDate = changeDate;
}

public String getGroupName() {
return groupName;
}

public void setGroupName(String  groupName) {
this.groupName = groupName;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String  firstName) {
this.firstName = firstName;
}

public String getMiddleName() {
return middleName;
}

public void setMiddleName(String  middleName) {
this.middleName = middleName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String  lastName) {
this.lastName = lastName;
}

public String getPersonalTitle() {
return personalTitle;
}

public void setPersonalTitle(String  personalTitle) {
this.personalTitle = personalTitle;
}

public String getSuffix() {
return suffix;
}

public void setSuffix(String  suffix) {
this.suffix = suffix;
}


public Map<String, Object> mapAttributeField() {
return PartyNameHistoryMapper.map(this);
}
}
