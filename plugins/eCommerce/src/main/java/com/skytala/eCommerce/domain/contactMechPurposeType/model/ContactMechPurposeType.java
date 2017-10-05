package com.skytala.eCommerce.domain.contactMechPurposeType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.contactMechPurposeType.mapper.ContactMechPurposeTypeMapper;

public class ContactMechPurposeType implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechPurposeTypeId;
private String description;

public String getContactMechPurposeTypeId() {
return contactMechPurposeTypeId;
}

public void setContactMechPurposeTypeId(String  contactMechPurposeTypeId) {
this.contactMechPurposeTypeId = contactMechPurposeTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContactMechPurposeTypeMapper.map(this);
}
}
