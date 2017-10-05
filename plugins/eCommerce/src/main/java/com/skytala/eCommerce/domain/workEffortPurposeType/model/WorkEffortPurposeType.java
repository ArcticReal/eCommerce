package com.skytala.eCommerce.domain.workEffortPurposeType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workEffortPurposeType.mapper.WorkEffortPurposeTypeMapper;

public class WorkEffortPurposeType implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortPurposeTypeId;
private String parentTypeId;
private String description;

public String getWorkEffortPurposeTypeId() {
return workEffortPurposeTypeId;
}

public void setWorkEffortPurposeTypeId(String  workEffortPurposeTypeId) {
this.workEffortPurposeTypeId = workEffortPurposeTypeId;
}

public String getParentTypeId() {
return parentTypeId;
}

public void setParentTypeId(String  parentTypeId) {
this.parentTypeId = parentTypeId;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}


public Map<String, Object> mapAttributeField() {
return WorkEffortPurposeTypeMapper.map(this);
}
}
