package com.skytala.eCommerce.domain.humanres.relations.emplLeave.model.reasonType;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.humanres.relations.emplLeave.mapper.reasonType.EmplLeaveReasonTypeMapper;

public class EmplLeaveReasonType implements Serializable{

private static final long serialVersionUID = 1L;
private String emplLeaveReasonTypeId;
private String parentTypeId;
private Boolean hasTable;
private String description;

public String getEmplLeaveReasonTypeId() {
return emplLeaveReasonTypeId;
}

public void setEmplLeaveReasonTypeId(String  emplLeaveReasonTypeId) {
this.emplLeaveReasonTypeId = emplLeaveReasonTypeId;
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
return EmplLeaveReasonTypeMapper.map(this);
}
}
