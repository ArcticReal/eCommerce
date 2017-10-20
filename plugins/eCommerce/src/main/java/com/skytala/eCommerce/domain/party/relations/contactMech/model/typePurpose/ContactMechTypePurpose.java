package com.skytala.eCommerce.domain.party.relations.contactMech.model.typePurpose;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.typePurpose.ContactMechTypePurposeMapper;

public class ContactMechTypePurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechTypeId;
private String contactMechPurposeTypeId;

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getContactMechPurposeTypeId() {
return contactMechPurposeTypeId;
}

public void setContactMechPurposeTypeId(String  contactMechPurposeTypeId) {
this.contactMechPurposeTypeId = contactMechPurposeTypeId;
}


public Map<String, Object> mapAttributeField() {
return ContactMechTypePurposeMapper.map(this);
}
}
