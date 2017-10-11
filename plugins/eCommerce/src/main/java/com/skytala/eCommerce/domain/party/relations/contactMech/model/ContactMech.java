package com.skytala.eCommerce.domain.party.relations.contactMech.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.ContactMechMapper;

public class ContactMech implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechId;
private String contactMechTypeId;
private String infoString;

public String getContactMechId() {
return contactMechId;
}

public void setContactMechId(String  contactMechId) {
this.contactMechId = contactMechId;
}

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getInfoString() {
return infoString;
}

public void setInfoString(String  infoString) {
this.infoString = infoString;
}


public Map<String, Object> mapAttributeField() {
return ContactMechMapper.map(this);
}
}
