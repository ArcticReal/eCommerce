package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.mapper.ContactMechTypeAttrMapper;

public class ContactMechTypeAttr implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechTypeId;
private String attrName;
private String description;

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getAttrName() {
return attrName;
}

public void setAttrName(String  attrName) {
this.attrName = attrName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContactMechTypeAttrMapper.map(this);
}
}
