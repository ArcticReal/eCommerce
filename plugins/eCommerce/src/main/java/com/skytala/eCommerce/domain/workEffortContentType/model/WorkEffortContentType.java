package com.skytala.eCommerce.domain.workEffortContentType.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.workEffortContentType.mapper.WorkEffortContentTypeMapper;

public class WorkEffortContentType implements Serializable{

private static final long serialVersionUID = 1L;
private String workEffortContentTypeId;
private String parentTypeId;
private String description;

public String getWorkEffortContentTypeId() {
return workEffortContentTypeId;
}

public void setWorkEffortContentTypeId(String  workEffortContentTypeId) {
this.workEffortContentTypeId = workEffortContentTypeId;
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
return WorkEffortContentTypeMapper.map(this);
}
}
