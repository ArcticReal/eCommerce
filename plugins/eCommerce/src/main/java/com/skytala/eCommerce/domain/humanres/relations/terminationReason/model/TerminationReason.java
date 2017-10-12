package com.skytala.eCommerce.domain.humanres.relations.terminationReason.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.mapper.TerminationReasonMapper;

public class TerminationReason implements Serializable{

private static final long serialVersionUID = 1L;
private String terminationReasonId;
private String description;

public String getTerminationReasonId() {
return terminationReasonId;
}

public void setTerminationReasonId(String  terminationReasonId) {
this.terminationReasonId = terminationReasonId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return TerminationReasonMapper.map(this);
}
}
