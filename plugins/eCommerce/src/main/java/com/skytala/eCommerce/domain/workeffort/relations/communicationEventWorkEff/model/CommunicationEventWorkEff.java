package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.mapper.CommunicationEventWorkEffMapper;

public class CommunicationEventWorkEff implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortId;
private String communicationEventId;
private String description;

public String getWorkEffortId() {
return workEffortId;
}

public void setWorkEffortId(String  workEffortId) {
this.workEffortId = workEffortId;
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
return CommunicationEventWorkEffMapper.map(this);
}
}
