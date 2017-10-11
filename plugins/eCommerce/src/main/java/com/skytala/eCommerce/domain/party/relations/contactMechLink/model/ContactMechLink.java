package com.skytala.eCommerce.domain.party.relations.contactMechLink.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.contactMechLink.mapper.ContactMechLinkMapper;

public class ContactMechLink implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechIdFrom;
private String contactMechIdTo;

public String getContactMechIdFrom() {
return contactMechIdFrom;
}

public void setContactMechIdFrom(String  contactMechIdFrom) {
this.contactMechIdFrom = contactMechIdFrom;
}

public String getContactMechIdTo() {
return contactMechIdTo;
}

public void setContactMechIdTo(String  contactMechIdTo) {
this.contactMechIdTo = contactMechIdTo;
}


public Map<String, Object> mapAttributeField() {
return ContactMechLinkMapper.map(this);
}
}
