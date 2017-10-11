package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.mapper.CommunicationEventPurposeMapper;

public class CommunicationEventPurpose implements Serializable{

private static final long serialVersionUID = 1L;
private String communicationEventPrpTypId;
private String communicationEventId;
private String description;

public String getCommunicationEventPrpTypId() {
return communicationEventPrpTypId;
}

public void setCommunicationEventPrpTypId(String  communicationEventPrpTypId) {
this.communicationEventPrpTypId = communicationEventPrpTypId;
}

public String getCommunicationEventId() {
return communicationEventId;
}

public void setCommunicationEventId(String  communicationEventId) {
this.communicationEventId = communicationEventId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return CommunicationEventPurposeMapper.map(this);
}
}
