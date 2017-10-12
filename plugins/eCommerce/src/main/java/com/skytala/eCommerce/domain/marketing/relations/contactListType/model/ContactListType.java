package com.skytala.eCommerce.domain.marketing.relations.contactListType.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.marketing.relations.contactListType.mapper.ContactListTypeMapper;

public class ContactListType implements Serializable{

private static final long serialVersionUID = 1L;
private String contactListTypeId;
private String description;

public String getContactListTypeId() {
return contactListTypeId;
}

public void setContactListTypeId(String  contactListTypeId) {
this.contactListTypeId = contactListTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContactListTypeMapper.map(this);
}
}
