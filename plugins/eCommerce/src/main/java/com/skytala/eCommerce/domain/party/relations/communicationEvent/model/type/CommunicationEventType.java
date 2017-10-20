package com.skytala.eCommerce.domain.party.relations.communicationEvent.model.type;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.communicationEvent.mapper.type.CommunicationEventTypeMapper;

public class CommunicationEventType implements Serializable{

private static final long serialVersionUID = 1L;
private String communicationEventTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;
private String contactMechTypeId;

public String getCommunicationEventTypeId() {
return communicationEventTypeId;
}

public void setCommunicationEventTypeId(String  communicationEventTypeId) {
this.communicationEventTypeId = communicationEventTypeId;
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

public String getContactMechTypeId() {
return contactMechTypeId;
}

public void setContactMechTypeId(String  contactMechTypeId) {
this.contactMechTypeId = contactMechTypeId;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventTypeMapper.map(this);
}
}
