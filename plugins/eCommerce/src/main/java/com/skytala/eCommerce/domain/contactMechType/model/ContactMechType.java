package com.skytala.eCommerce.domain.contactMechType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.contactMechType.mapper.ContactMechTypeMapper;

public class ContactMechType implements Serializable{

private static final long serialVersionUID = 1L;
private String contactMechTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public Boolean getHasTable() {
return hasTable;
}

public void setHasTable(Boolean  hasTable) {
this.hasTable = hasTable;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return ContactMechTypeMapper.map(this);
}
}
